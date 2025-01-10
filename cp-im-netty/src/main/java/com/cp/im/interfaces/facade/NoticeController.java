package com.cp.im.interfaces.facade;

import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.PopupNoticeReqCmd;
import com.cp.im.infrastructure.factory.HandlerContext;
import com.cp.im.result.Result;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知内部API.
 */

@RestController
public class NoticeController {

  @Resource
  private HandlerContext handlerContext;

  /**
   * 软件通知.
   *
   * @param params 通知信息
   * @return 调用结果
   */
  @PostMapping(
      value = "/notice/newSystemNotice",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Result<?> newSystemNotice(@RequestBody Map<String, Object> params) {
    return this.handlerContext.getInstance(BasisOptCmd.CMD_NOTICE)
        .request(PopupNoticeReqCmd.SYS_SOFTWARE_NOTICE, params);
  }

  @GetMapping(
          value = "/notice/testRabbitMq",
          produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Result<?> testRabbitMq() {
    ChannelRepository.testRabbitMq("testRabbitMq");
    return Result.success();
  }

}
