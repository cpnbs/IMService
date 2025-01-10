package com.cp.im.application.service;

import com.cp.im.interfaces.dto.*;
import com.cp.im.result.Result;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ChatService {

    /**
     * 客服登录.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    Result<?> login(LoginDTO queryInfo);

    /**
     * 聊天列表.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    Result<?> getChatList(ChatListFilterDto queryInfo);

    /**
     * 聊天用户.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    Result<?> getChatUser(ChatListFilterDto queryInfo);

    /**
     * 更新聊天设置.
     *
     * @param updateInfo 删除信息
     * @return 更新结果
     */
    Result<?> updateChatSetting(UpdateChatSettingDTO updateInfo);

    /**
     * 聊天记录.
     *
     * @param queryInfo 查询参数
     * @return 查询结果
     */
    Result<?> getChatMessageList(QueryChatDTO queryInfo);

    /**
     * 消息屏蔽.
     *
     * @param recordId 记录ID
     */
    Result<?> updateMessageShield(Long recordId);

    /**
     * 更新移除标记.
     *
     * @param deleteInfo 删除信息
     * @return 更新结果
     */
    Result<?> updateDeleteFlag(DeleteChatMsgDTO deleteInfo);

    /**
     * 标记时间.
     *
     * @param userId 用户ID
     * @param targetId 目标ID
     * @return 标记时间
     */
    String queryMessageTime(Long userId, Long targetId);

    /**
     * 翻译文本.
     */
    Result<?> translateText(TranslateDTO translateInfo);

    /**
     * 获得表情.
     */
    Result<?> getEmoticon();

    /**
     * 获得分组.
     */
    Result<?> getDivide();

    /**
     * 获得问答.
     */
    Result<?> getQa(String qa);

    /**
     * 新增问答.
     */
    Result<?> insertQa(Map<String, Object> map);

    /**
     * 删除问答.
     */
    Result<?> deleteQa(Long id);

    /**
     * 修改问答.
     */
    Result<?> updateQa(Long id, @RequestBody Map<String, Object> map);
}
