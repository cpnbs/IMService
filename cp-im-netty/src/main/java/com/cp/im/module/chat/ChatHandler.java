package com.cp.im.module.chat;

import com.cp.im.domain.mq.Packet;
import com.cp.im.infrastructure.OptionalHandler;
import com.cp.im.infrastructure.annotation.ModuleTag;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.ChitchatCmd;
import com.cp.im.infrastructure.constants.ChatKeyConstants;
import com.cp.im.infrastructure.factory.HandlerContext;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.proto.c10002msg.C10002;
import com.cp.im.utils.ExceptionUtil;
import com.cp.im.utils.StringUtils;
import io.netty.channel.Channel;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 聊天处理.
 *
 * @author xxxxxxxxxx
 * @version v1.0.0
 * @since 2021/9/7 14:28
 */
@Slf4j
@Component
@ModuleTag(tag = BasisOptCmd.CMD_CHAT)
public class ChatHandler extends OptionalHandler {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private HandlerContext handlerContext;

    /**
     * 操作处理.
     *
     * @param channel 通信管道
     * @param packet  数据包
     * @author wangcaiwen
     * @since 2020/5/15 21:09
     */
    @Override
    public void handle(Channel channel, Packet packet) {
        switch (packet.child) {
            // 进入聊天
            case ChitchatCmd.ENTER_CHAT_ROOM:
                optTargetChat(channel, packet);
                break;
            // 聊天信息
            case ChitchatCmd.SEND_MESSAGE:
                sendChatMessage(channel, packet);
                break;
            // 离开聊天
            case ChitchatCmd.LEAVE_CHAT_ROOM:
                leaveChatRoom(channel, packet);
                break;
            // 聆听语音
            case ChitchatCmd.LISTEN_VOICE:
                listenVoice(channel, packet);
                break;
            default:
                log.error("[ERROR] Unknown. Packet: Cmd=[10002], Child=[{}].", packet.child);
                break;
        }
    }

    /**
     * 进入聊天.
     *
     * @param channel 通信管道
     * @param packet  数据包
     * @author xxxxxxxxxx
     * @since 2021/9/7 14:44
     */
    private void optTargetChat(Channel channel, Packet packet) {
        try {
            C10002.C100020c2s request = C10002.C100020c2s.parseFrom(packet.bytes);
            Integer tag = ChatTypeTag.getChatOrderTagByIndex(request.getSort());
            if (tag > 0) {
                this.handlerContext.getInstance(tag).handle(channel, packet);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 发送消息.
     *
     * @param channel 通信管道
     * @param packet  数据包
     * @author xxxxxxxxxx
     * @since 2021/9/7 15:26
     */
    private void sendChatMessage(Channel channel, Packet packet) {
        try {
            C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
            Integer tag = ChatTypeTag.getChatOrderTagByIndex(request.getSort());
            if (tag > 0) {
                this.handlerContext.getInstance(tag).handle(channel, packet);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 离开聊天.
     *
     * @param channel 管道信息
     * @param packet  数据包
     * @author wangcaiwen
     * @since 2020/7/28 7:10
     */
    public void leaveChatRoom(Channel channel, Packet packet) {
        try {
            C10002.C100023c2s request = C10002.C100023c2s.parseFrom(packet.bytes);
            Integer tag = ChatTypeTag.getChatOrderTagByIndex(request.getSort());
            if (tag > 0) {
                this.handlerContext.getInstance(tag).handle(channel, packet);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 聆听语音.
     *
     * @param channel 管道信息
     * @param packet  数据包
     */
    public void listenVoice(Channel channel, Packet packet) {
        try {
            C10002.C100024c2s request = C10002.C100024c2s.parseFrom(packet.bytes);
            Integer tag = ChatTypeTag.getChatOrderTagByIndex(request.getSort());
            if (tag > 0) {
                this.handlerContext.getInstance(tag).handle(channel, packet);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 异常处理.
     *
     * @param channel 通信管道
     * @param userId  用户ID
     * @author xxxxxxxxxx
     * @since 2021/9/7 13:47
     */
    @Override
    public void exception(Channel channel, Long userId) {
        try {
            if (this.redisUtils.hHasKey(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(userId))) {
                log.warn("[WARN] Exception_Exit_Chat_Single. Uid=[{}].", userId);
                this.redisUtils.hdel(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(userId));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

}
