package com.cp.im.domain.bo;

/**
 * 视频信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 6:21
 */

public class VideoInfo {

  /** 视频宽度. */
  private Integer width;
  /** 视频高度. */
  private Integer height;
  /** 持续时间. */
  private Integer duration;
  /** 访问地址. */
  private String videoURL;
  /** 封面访问地址. */
  private String coverURL;

  public VideoInfo() {
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public void setVideoURL(String videoURL) {
    this.videoURL = videoURL;
  }

  public String getCoverURL() {
    return coverURL;
  }

  public void setCoverURL(String coverURL) {
    this.coverURL = coverURL;
  }

  @Override
  public String toString() {
    return "VideoInfoDTO{"
        + "width=" + width
        + ", height=" + height
        + ", duration=" + duration
        + ", videoURL='" + videoURL + '\''
        + ", coverURL='" + coverURL + '\''
        + '}';
  }
}
