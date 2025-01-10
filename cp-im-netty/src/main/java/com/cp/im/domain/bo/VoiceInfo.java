package com.cp.im.domain.bo;

/**
 * 语音信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 6:20
 */

public class VoiceInfo {

  /** 持续时间. */
  private Integer duration;
  /** 语音访问地址. */
  private String voiceURL;

  public VoiceInfo() {
  }

  public VoiceInfo(Integer duration, String voiceURL) {
    this.duration = duration;
    this.voiceURL = voiceURL;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public String getVoiceURL() {
    return voiceURL;
  }

  public void setVoiceURL(String voiceURL) {
    this.voiceURL = voiceURL;
  }

  @Override
  public String toString() {
    return "VoiceInfoDTO{"
        + "duration=" + duration
        + ", voiceURL='" + voiceURL + '\''
        + '}';
  }
}
