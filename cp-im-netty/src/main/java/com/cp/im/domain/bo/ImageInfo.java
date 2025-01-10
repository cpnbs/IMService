package com.cp.im.domain.bo;

/**
 * 图片信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 6:20
 */
public class ImageInfo {

  /** 图片宽度. */
  private Integer width;
  /** 图片高度. */
  private Integer height;
  /** 图片原图访问地址. */
  private String imageURL;
  /** 图片缩略图访问地址. */
  private String litimg;

  public ImageInfo() {
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

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getLitimg() {
    return litimg;
  }

  public void setLitimg(String litimg) {
    this.litimg = litimg;
  }

  @Override
  public String toString() {
    return "ImageInfoDTO{"
        + "width=" + width
        + ", height=" + height
        + ", imageURL='" + imageURL + '\''
        + ", litimg='" + litimg + '\''
        + '}';
  }
}
