package com.cp.im.domain.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.cp.im.application.service.ChatService;
import com.cp.im.constants.AliOssConstants;
import com.cp.im.domain.base.BasePageInfo;
import com.cp.im.domain.bo.EmojiInfo;
import com.cp.im.domain.bo.ImageInfo;
import com.cp.im.domain.bo.VideoInfo;
import com.cp.im.domain.bo.VoiceInfo;
import com.cp.im.domain.dto.ChatSortDTO;
import com.cp.im.domain.dto.SingleChatMsgDTO;
import com.cp.im.domain.entity.ChatSingle;
import com.cp.im.domain.entity.ChatSingleFile;
import com.cp.im.domain.entity.TokenInfo;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.domain.vo.ChatDivideVO;
import com.cp.im.domain.vo.ChatQaVO;
import com.cp.im.domain.vo.ChatEmoticonVO;
import com.cp.im.domain.vo.ChatNoticeVO;
import com.cp.im.error.ErrorCode;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.ChitchatCmd;
import com.cp.im.infrastructure.cmd.PopupNoticeCmd;
import com.cp.im.infrastructure.mapper.*;
import com.cp.im.infrastructure.utils.JsonUtils;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.interfaces.dto.*;
import com.cp.im.manager.ZoneEnum;
import com.cp.im.proto.c10002msg.C10002;
import com.cp.im.result.Result;
import com.cp.im.utils.ServletUtils;
import com.cp.im.utils.StringUtils;
import com.cp.im.utils.Translate;
import com.cp.im.utils.encrypt.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ChatSingleMapper chatSingleMapper;

    @Resource
    private ChatSingleMessageMapper chatSingleMessageMapper;

    @Resource
    private ChatSingleFileMapper chatSingleFileMapper;

    @Resource
    private ChatSingleDeleteMapper chatSingleDeleteMapper;

    @Resource
    private ChatEmoticonMapper chatEmoticonMapper;

    /**
     * 客服登录.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    @Override
    public Result<?> login(LoginDTO queryInfo) {

        Map<String, Object> userMap = this.chatSingleMapper.getUser(queryInfo.getAccount());
        if (Objects.isNull(userMap)){
            return Result.error(1, "account error");
        }
        if (!Objects.equals(MapUtils.getString(userMap, "password"), queryInfo.getPassword())){
            return Result.error(2, "password error");
        }
        Long userId = MapUtils.getLong(userMap, "id");
        Integer userType = MapUtils.getInteger(userMap, "usertype");
        String token = this.chatSingleMapper.getToken(userId);
        if (StringUtils.isEmpty(token)){

            token = Token.GetGUID();
            LocalDateTime nowTime = LocalDateTime.now();

            TokenInfo insertInfo = new TokenInfo();
            insertInfo.setUserId(userId);
            insertInfo.setToken(token);
            insertInfo.setStatus(1);
            insertInfo.setATime(nowTime);
            insertInfo.setETime(nowTime.plusDays(7));
            this.chatSingleMapper.insertToken(insertInfo);
        }
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("userId", userId);
        resultMap.put("userToken", token);
        resultMap.put("userType", userType < 0 ? 1 : 0);
        resultMap.put("iconUrl", MapUtils.getString(userMap, "headurl"));
        resultMap.put("alias", MapUtils.getString(userMap, "nickname"));
        return Result.success(resultMap);
    }

    /**
     * 信息通知列表.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    @Override
    public Result<?> getChatList(ChatListFilterDto queryInfo) {

        List<Long> filterIds = Lists.newArrayList();
        //todo 增加查询用户筛选的名称
        if(!ObjectUtils.isEmpty(queryInfo.getFilterName()) ){
            filterIds = this.chatSingleMapper.filterChatList(queryInfo.getUserId(), queryInfo.getFilterName());
            if(filterIds.size() == 0){
                return Result.success();
            }
        }
//        LocalDate ldt = LocalDate.now().minusMonths(1L).withDayOfMonth(1);
        LocalDate ldt = LocalDate.now().plusDays(-3);
        String pastTime = ldt + " 00:00:00";

        List<Long> finalFilterIds = filterIds;
        PageInfo<ChatSortDTO> pageInfo = PageHelper
                .startPage(queryInfo.getPageNum(), queryInfo.getPageSize())
                .doSelectPageInfo(() -> this.chatSingleMapper.selectChatInfoList(queryInfo.getUserId(), pastTime, finalFilterIds));
        if (CollectionUtils.isNotEmpty(pageInfo.getList()) && pageInfo.getList().size() > 0) {
            pageInfo.getList().stream().filter(dto -> dto.getSort() == 1).forEach(this::accept1);
            List<Long> userIds = pageInfo.getList().stream().filter(dto -> dto.getSort() == 1).map(ChatSortDTO::getTargetId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(userIds) && userIds.size() > 0) {
                List<Map<String, Object>> userBaseList = this.chatSingleMapper.getUserBaseList(userIds);
                if (CollectionUtils.isNotEmpty(userBaseList) && userBaseList.size() > 0) {
                    userBaseList.forEach(userBase -> {
                        Long targetId = MapUtils.getLong(userBase, "userId");
                        pageInfo.getList().stream().filter(sortDTO -> sortDTO.getSort() == 1).forEach(sortDTO -> {
                            if (Objects.equals(targetId, sortDTO.getTargetId())) {
                                sortDTO.setMobile(String.valueOf(userBase.get("mobile")));
                                String iconUrl = StringUtils.nvl(MapUtils.getString(userBase, "iconUrl"));
                                sortDTO.setThumbIconURL(AliOssConstants.iconDefaultResize(iconUrl));
                            }
                        });
                    });
                }
            }
        }
        ChatNoticeVO messageNotice = new ChatNoticeVO();
        messageNotice.setTotal(pageInfo.getTotal());
        messageNotice.setPageNum(pageInfo.getPageNum());
        messageNotice.setPageSize(pageInfo.getPageSize());
        messageNotice.setPages(pageInfo.getPages());
        messageNotice.setChatList(pageInfo.getList());
        Integer singleChatUnread = this.chatSingleMapper.getUserUnreadNum(queryInfo.getUserId());
        messageNotice.setUnRead(singleChatUnread);
        return Result.success(messageNotice);
    }

    @Override
    public Result<?> getChatUser(ChatListFilterDto queryInfo) {

        List<Long> filterIds = Lists.newArrayList();
        //todo 增加查询用户筛选的名称
        if(!ObjectUtils.isEmpty(queryInfo.getFilterName()) ){
            filterIds = this.chatSingleMapper.filterChatList(queryInfo.getUserId(), queryInfo.getFilterName());
            if(filterIds.size() == 0){
                return Result.success();
            }
        }
        List<Long> finalFilterIds = filterIds;
        PageInfo<ChatSortDTO> pageInfo = PageHelper
                .startPage(queryInfo.getPageNum(), queryInfo.getPageSize())
                .doSelectPageInfo(() -> this.chatSingleMapper.selectChatUserList(queryInfo.getUserId(), finalFilterIds));
        if (CollectionUtils.isNotEmpty(pageInfo.getList()) && pageInfo.getList().size() > 0) {
            List<Long> userIds = pageInfo.getList().stream().filter(dto -> dto.getSort() == 1).map(ChatSortDTO::getTargetId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(userIds) && userIds.size() > 0) {
                List<Map<String, Object>> userBaseList = this.chatSingleMapper.getUserBaseList(userIds);
                if (CollectionUtils.isNotEmpty(userBaseList) && userBaseList.size() > 0) {
                    userBaseList.forEach(userBase -> {
                        Long targetId = MapUtils.getLong(userBase, "userId");
                        pageInfo.getList().stream().filter(sortDTO -> sortDTO.getSort() == 1).forEach(sortDTO -> {
                            if (Objects.equals(targetId, sortDTO.getTargetId())) {
                                sortDTO.setMobile(String.valueOf(userBase.get("mobile")));
                                String iconUrl = StringUtils.nvl(MapUtils.getString(userBase, "iconUrl"));
                                sortDTO.setThumbIconURL(AliOssConstants.iconDefaultResize(iconUrl));
                            }
                        });
                    });
                }
            }
        }
        ChatNoticeVO messageNotice = new ChatNoticeVO();
        messageNotice.setTotal(pageInfo.getTotal());
        messageNotice.setPageNum(pageInfo.getPageNum());
        messageNotice.setPageSize(pageInfo.getPageSize());
        messageNotice.setPages(pageInfo.getPages());

        List<Map<String, Object>> list = new ArrayList<>();
        List<ChatDivideVO> divideList = this.chatEmoticonMapper.selectDivide();
        divideList.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            map.put("groupList", pageInfo.getList().stream().filter(p -> p.getDivideId() == item.getId()));
            list.add(map);
        });
        messageNotice.setGroupList(list);
        return Result.success(messageNotice);
    }

    private void accept1(ChatSortDTO dto) {
        dto.setLastTimestamp(dto.getLastTime().toEpochSecond(ZoneOffset.of(ZoneEnum.E8.getZone())));
        // 消息类型 0 文本 1 Emoji 2 图片 3 语音 4 视频
        Integer type = dto.getMessageType();
        switch (type) {
            case 1:
                dto.setMessage("[Emoji]");
                break;
            case 2:
                dto.setMessage("[Picture]");
                break;
            case 3:
                dto.setMessage("[Voice]");
                break;
            case 4:
                dto.setMessage("[Video]");
                break;
            default:
                dto.setMessageType(0);
                break;
        }
    }

    /**
     * 更新聊天设置.
     *
     * @param updateInfo 删除信息
     * @return 更新结果
     */
    @Override
    public Result<?> updateChatSetting(UpdateChatSettingDTO updateInfo) {
        if (Objects.isNull(updateInfo.getTargetId())) {
            return Result.error(ErrorCode.CHAT_PARAM_ERROR);
        }
        ChatSingle chat = new ChatSingle();
        chat.setUserId(updateInfo.getUserId());
        chat.setTargetId(updateInfo.getTargetId());
        if (Objects.nonNull(updateInfo.getDivideId())) {
            // 好友分组
            chat.setDivideId(updateInfo.getDivideId());
        }
        if (Objects.nonNull(updateInfo.getFlagStar())) {
            // 标记加星 0=否 1=是
            chat.setFlagStar(updateInfo.getFlagStar());
        }
        if (Objects.nonNull(updateInfo.getFlagTop())) {
            // 标记置顶 0=否 1=是
            chat.setFlagTop(updateInfo.getFlagTop());
        }
        if (Objects.nonNull(updateInfo.getFlagDelete())) {
            // 标记移除 0=否 1=是
            chat.setFlagDelete(updateInfo.getFlagDelete());
            if (chat.getFlagDelete() == 1) {
                chat.setMessageId(0l);
                // 更新标记
                this.chatSingleDeleteMapper.updateDeleteFlag(chat.getUserId(), chat.getTargetId());
            }
        }
        if (Objects.nonNull(updateInfo.getNotDisturb())) {
            // 免打扰 0=关闭 1=开启
            chat.setNotDisturb(updateInfo.getNotDisturb());
        }
        if (StringUtils.isNotEmpty(updateInfo.getAlias())){
            chat.setAlias(updateInfo.getAlias());
        }
        this.chatSingleMapper.updateChatSetting(chat);
        return Result.success();
    }

    /**
     * 聊天记录.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    @Override
    public Result<?> getChatMessageList(QueryChatDTO queryInfo) {
        String messageTime = queryMessageTime(queryInfo.getUserId(), queryInfo.getTargetId());
        if (StringUtils.isNotEmpty(messageTime)) {
            LocalDateTime dateTime =
                    LocalDateTime.parse(messageTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDate idt = dateTime.toLocalDate();
            LocalDate ldt = LocalDate.now().minusMonths(1L).withDayOfMonth(1);
            if (idt.equals(ldt) || idt.isAfter(ldt)) {
                queryInfo.setIndexTime(messageTime);
            } else {
                String pastTime = ldt + " 00:00:00";
                queryInfo.setIndexTime(pastTime);
            }
        } else {
            LocalDate ldt = LocalDate.now().minusMonths(1L).withDayOfMonth(1);
            String pastTime = ldt + " 00:00:00";
            queryInfo.setIndexTime(pastTime);
        }
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<SingleChatMsgDTO> messageList = this.chatSingleMessageMapper.getChatMessageList(queryInfo);
        PageInfo<SingleChatMsgDTO> pageInfo = new PageInfo<>(messageHandle(messageList));
        BasePageInfo<SingleChatMsgDTO> pageInfoVO = new BasePageInfo<>();
        pageInfoVO.setBaseInfoByPageInfo(pageInfo);
        return Result.success(pageInfoVO);
    }

    /**
     * 消息屏蔽.
     *
     * @param recordId 记录ID
     */
    @Override
    public Result<?> updateMessageShield(Long recordId) {
        Map<String, Object> map = this.chatSingleMessageMapper.selectMessageByRecordId(recordId);
        if (MapUtils.isEmpty(map)){
            Result.error();
        }
        Long userId = MapUtils.getLong(map, "userId");
        Long targetId = MapUtils.getLong(map, "targetId");
        C10002.C100025s2c.Builder builder = C10002.C100025s2c.newBuilder();
        builder.setSort(1);
        builder.setGroupId(0);
        builder.setTargetId(userId);
        builder.setRecordId(recordId);
        ChannelRepository.sendPacketToUserId(
                new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.SHIELD_MESSAGE,
                        builder.build().toByteArray()), targetId);
        log.warn(">>>屏蔽消息 [{}] and [{}] 撤回 [{}]", userId, targetId, recordId);
        this.chatSingleMessageMapper.updateMessageShield(recordId);
        return Result.success();
    }

    /**
     * 更新移除标记.
     *
     * @param deleteInfo 删除信息
     */
    @Override
    public Result<?> updateDeleteFlag(DeleteChatMsgDTO deleteInfo) {
        this.chatSingleMapper.updateMessageId(deleteInfo);
        this.chatSingleDeleteMapper.updateDeleteFlag(deleteInfo.getUserId(), deleteInfo.getTargetId());
        return Result.success();
    }

    /**
     * 标记时间.
     *
     * @param userId 用户ID
     * @param targetId 目标ID
     * @return 标记时间
     */
    @Override
    public String queryMessageTime(Long userId, Long targetId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String messageTime = this.chatSingleDeleteMapper.queryMessageTime(userId, targetId);
        if (StringUtils.isNotEmpty(messageTime)) {
            Timestamp time = Timestamp.valueOf(messageTime);
            return dtf.format(time.toLocalDateTime());
        }
        return null;
    }


    /**
     * 翻译文本.
     */
    @Override
    public Result<?> translateText(TranslateDTO translateInfo){
        try {
            String retStr = Translate.getInstance().translateText(translateInfo.getText(), translateInfo.getSourceLang(), translateInfo.getTargetLang());
            return Result.success(retStr);
        } catch (Exception e){
            return Result.error();
        }

    }

    /**
     * 获得表情.
     */
    @Override
    public Result<?> getEmoticon() {
        String key = "CHAT_EMOJI";
        Object object = this.redisUtils.get(key);
        List<ChatEmoticonVO> emoticonList = JsonUtils.toListType(object, ChatEmoticonVO.class);
        if (CollectionUtils.isEmpty(emoticonList)) {
            emoticonList = this.chatEmoticonMapper.selectEmoticon(1);
            if (ObjectUtil.isNotEmpty(emoticonList)) {
                // 缓存三天 60*60*24*3 = 259200L
                this.redisUtils.set(key, emoticonList, 259200L);
            }
        }
        return Result.success(CollectionUtils.isNotEmpty(emoticonList) ? emoticonList : Collections.emptyList());
    }

    /**
     * 获得分组.
     */
    @Override
    public Result<?> getDivide() {
        List<ChatDivideVO> divideList = this.chatEmoticonMapper.selectDivide();
        return Result.success(CollectionUtils.isNotEmpty(divideList) ? divideList : Collections.emptyList());
    }

    /**
     * 获得问答.
     */
    @Override
    public Result<?> getQa(String qa) {
        Long userId = Long.parseLong(ServletUtils.getHeader("userId","0"));
        List<ChatQaVO> qaList = this.chatEmoticonMapper.selectQa(qa, userId);
        return Result.success(CollectionUtils.isNotEmpty(qaList) ? qaList : Collections.emptyList());
    }

    /**
     * 新增问答.
     */
    @Override
    public Result<?> insertQa(Map<String, Object> map) {
        Long userId = Long.parseLong(ServletUtils.getHeader("userId","0"));
        String qa = JSONObject.toJSONString(map);
        this.chatEmoticonMapper.insertQa(qa, userId);
        return Result.success();
    }

    /**
     * 删除问答.
     */
    @Override
    public Result<?> deleteQa(Long id) {
        this.chatEmoticonMapper.deleteQa(id);
        return Result.success();
    }

    /**
     * 修改问答.
     */
    @Override
    public Result<?> updateQa(Long id, @RequestBody Map<String, Object> map) {
        String qa = JSONObject.toJSONString(map);
        this.chatEmoticonMapper.updateQa(id, qa);
        return Result.success();
    }

    /**
     * 消息处理.
     *
     * @param messageList 聊天记录
     * @return 聊天记录
     */
    private List<SingleChatMsgDTO> messageHandle(List<SingleChatMsgDTO> messageList) {
        if (CollectionUtils.isNotEmpty(messageList)) {
            messageList.forEach(messageInfo -> {
                messageInfo.setCreateTimestamp(messageInfo.getCreateTime().toEpochSecond(ZoneOffset.of(ZoneEnum.E8.getZone())));
                Integer type = messageInfo.getMessageType();
                switch (type) {
                    case 1:
                        EmojiInfo emojiInfo = new EmojiInfo();
                        emojiInfo.setEmName(messageInfo.getEmojiName());
                        emojiInfo.setAnimURL(messageInfo.getEmojiUrl());
                        messageInfo.setEmojiInfo(emojiInfo);
                        break;
                    case 2:
                        ChatSingleFile imageFileInfo = this.chatSingleFileMapper
                                .getFileInfo(messageInfo.getRecordId());
                        if (Objects.nonNull(imageFileInfo)) {
                            messageInfo.setImageInfo(typeByImage(imageFileInfo));
                        }
                        break;
                    case 3:
                        ChatSingleFile voiceFileInfo = this.chatSingleFileMapper
                                .getFileInfo(messageInfo.getRecordId());
                        if (Objects.nonNull(voiceFileInfo)) {
                            messageInfo.setVoiceInfo(typeByVoice(voiceFileInfo));
                        }
                        break;
                    case 4:
                        ChatSingleFile videoFileInfo = this.chatSingleFileMapper
                                .getFileInfo(messageInfo.getRecordId());
                        if (Objects.nonNull(videoFileInfo)) {
                            messageInfo.setVideoInfo(typeByVideo(videoFileInfo));
                        }
                        break;
                    default:
                        break;
                }
                messageInfo.setEmojiName(null);
                messageInfo.setEmojiUrl(null);
            });
        }
        return messageList;
    }

    /**
     * 组合图片信息.
     *
     * @param imageFileDO 文件信息
     * @return 图片信息
     */
    private ImageInfo typeByImage(ChatSingleFile imageFileDO) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setWidth(imageFileDO.getFileWidth());
        imageInfo.setHeight(imageFileDO.getFileHeight());
        imageInfo.setImageURL(imageFileDO.getFileUrl() + "?p=0");
        imageInfo.setLitimg(imageFileDO.getFileUrl() + "?w=400");
        return imageInfo;
    }

    /**
     * 组合语音信息.
     *
     * @param voiceFileDO 文件信息
     * @return 语音信息
     */
    private VoiceInfo typeByVoice(ChatSingleFile voiceFileDO) {
        VoiceInfo voiceInfo = new VoiceInfo();
        voiceInfo.setDuration(voiceFileDO.getFileDuration());
        voiceInfo.setVoiceURL(voiceFileDO.getFileUrl());
        return voiceInfo;
    }

    /**
     * 组合视频信息.
     *
     * @param videoFileDO 文件信息
     * @return 视频信息
     */
    private VideoInfo typeByVideo(ChatSingleFile videoFileDO) {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setWidth(videoFileDO.getFileWidth());
        videoInfo.setHeight(videoFileDO.getFileHeight());
        videoInfo.setDuration(videoFileDO.getFileDuration());
        videoInfo.setVideoURL(videoFileDO.getFileUrl());
        videoInfo.setCoverURL(videoFileDO.getFileCoverUrl() + "?w=400");
        return videoInfo;
    }
}
