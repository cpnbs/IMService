package com.cp.im.interfaces.facade;

import com.cp.im.application.service.ChatService;
import com.cp.im.error.ErrorCode;
import com.cp.im.infrastructure.annotation.Resubmit;
import com.cp.im.infrastructure.file.FileService;
import com.cp.im.interfaces.dto.*;
import com.cp.im.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


@RestController
public class ChatController {

    @Resource
    private ChatService chatService;

    @Autowired
    private FileService fileService;

    /**
     * 客服登录.
     *
     * @param queryInfo 查询参数
     * @param bindingResult 绑定结果
     * @return 查询结果
     */
    @PostMapping(
            value = "/chat/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> login(@Validated @RequestBody LoginDTO queryInfo,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return this.chatService.login(queryInfo);
    }

    /**
     * 获得聊天消息.
     *
     * @param queryInfo     查询参数
     * @param bindingResult 绑定结果
     */
    @PostMapping(
            value = "/chat/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> getChatList(@Validated @RequestBody ChatListFilterDto queryInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (Objects.isNull(queryInfo.getPageNum())) {
            queryInfo.setPageNum(1);
        }
        if (Objects.isNull(queryInfo.getPageSize())) {
            queryInfo.setPageSize(30);
        }
        return this.chatService.getChatList(queryInfo);
    }

    /**
     * 获得聊天用户.
     *
     * @param queryInfo     查询参数
     * @param bindingResult 绑定结果
     */
    @PostMapping(
            value = "/chat/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> getChatUser(@Validated @RequestBody ChatListFilterDto queryInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (Objects.isNull(queryInfo.getPageNum())) {
            queryInfo.setPageNum(1);
        }
        if (Objects.isNull(queryInfo.getPageSize())) {
            queryInfo.setPageSize(30);
        }
        return this.chatService.getChatUser(queryInfo);
    }

    /**
     * 更新聊天设置.
     *
     * @param updateInfo    更新信息
     * @param bindingResult 绑定结果
     * @return 更新结果
     */
    @PostMapping(
            value = "/chat/updateChatSetting",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> updateNoticeSetting(@Validated @RequestBody UpdateChatSettingDTO updateInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return this.chatService.updateChatSetting(updateInfo);
    }

    /**
     * 获得聊天记录.
     *
     * @param queryInfo     查询参数
     * @param bindingResult 绑定结果
     */
    @PostMapping(
            value = "/chat/getChatMessage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> getChatMessageList(@Validated @RequestBody QueryChatDTO queryInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (Objects.isNull(queryInfo.getPageNum())) {
            queryInfo.setPageNum(1);
        }
        if (Objects.isNull(queryInfo.getPageSize())) {
            queryInfo.setPageSize(15);
        }
        return this.chatService.getChatMessageList(queryInfo);
    }

    /**
     * 消息屏蔽.
     *
     * @param recordId 记录ID
     */
    @GetMapping(
            value = "/chat/shieldChatMessage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> shieldChatMessage(@RequestParam(name="id") Long recordId) {
        return this.chatService.updateMessageShield(recordId);
    }

    /**
     * 清空聊天记录.
     *
     * @param deleteInfo    删除信息
     * @param bindingResult 绑定结果
     * @return 清空结果
     */
    @PostMapping(
            value = "/chat/emptyChatMessage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> emptyChatMessage(@Validated @RequestBody DeleteChatMsgDTO deleteInfo,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return this.chatService.updateDeleteFlag(deleteInfo);
    }

    /**
     * 翻译文本.
     *
     * @param translateInfo 翻译信息
     * @param bindingResult 绑定结果
     * @return 清空结果
     */
    @Resubmit(time = 1)
    @PostMapping(
            value = "/chat/translateText",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> translateText(@Validated @RequestBody TranslateDTO translateInfo,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ErrorCode.EXCEPTION_CODE,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return this.chatService.translateText(translateInfo);
    }

    /**
     * 获得表情.
     * @return 查询结果
     */
    @GetMapping(
            value = "/emoticon/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> getEmoticon() {
        return this.chatService.getEmoticon();
    }

    /**
     * 获得分组.
     * @return 查询结果
     */
    @GetMapping(
            value = "/divide/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> getDivide() {
        return this.chatService.getDivide();
    }

    /**
     * 获得问答.
     * @return 查询结果
     */
    @GetMapping(
            value = "/qa/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> getQa(@RequestParam(name="x", required=false) String qa) {
        return this.chatService.getQa(qa);
    }

    /**
     * 新增问答.
     */
    @Resubmit(time = 3)
    @PostMapping(
            value = "/qa/insert",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> insertQa(@RequestBody Map<String, Object> map) {
        return this.chatService.insertQa(map);
    }

    /**
     * 删除问答.
     */
    @Resubmit(time = 1)
    @GetMapping(
            value = "/qa/delete",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> deleteQa(@RequestParam(name="id") Long id) {
        return this.chatService.deleteQa(id);
    }

    /**
     * 修改问答.
     */
    @Resubmit(time = 1)
    @PostMapping(
            value = "/qa/update",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> updateQa(@RequestParam(name="id") Long id, @RequestBody Map<String, Object> map) {
        return this.chatService.updateQa(id, map);
    }

    /**
     * 上传文件.
     *
     * @param file    文件信息
     * @param h       高度
     * @param w       宽度
     * @param request 请求信息
     * @return 请求结果
     * @throws Exception 异常信息
     * @author xubin
     * @since 2020/6/9 16:14
     */
    @PostMapping(
            value = "/files/uploadFile",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "*")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file,
                                @RequestParam(value = "h", required = false) String h,
                                @RequestParam(value = "w", required = false) String w,
                                HttpServletRequest request) throws Exception {
        return this.fileService.asyncUploadOssFile(file, h, w, "cp/", request);
    }

    /**
     * 上传网络流到阿里云服务.
     *
     * @param fileUrl 文件地址
     * @param ext     后缀
     * @param request 请求信息
     * @return 请求结果
     */
    @GetMapping(
            value = "/files/uploadFileUrl",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<?> uploadOssFile(@RequestParam("fileUrl") String fileUrl,
                                   String ext,
                                   HttpServletRequest request) {
        return this.fileService.uploadOssFile(fileUrl, ext, "cp/", request);
    }
}
