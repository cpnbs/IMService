package com.cp.im.domain.entity;

import java.io.Serializable;

/**
 * 聊天文件信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/5/4 15:36
 */

public class ChatSingleFile implements Serializable {
  private static final long serialVersionUID = -1032865944786877938L;
  /** 主键ID. */
  private Long id;
  /** 记录ID. */
  private Long recordId;
  /** 文件类型 [0 图片 1 语音 2 视频]. */
  private Integer fileType;
  /** 文件时长 [秒]. */
  private Integer fileDuration;
  /** 文件宽度 [像素]. */
  private Integer fileWidth;
  /** 文件高度 [像素]. */
  private Integer fileHeight;
  /** 访问地址. */
  private String fileUrl;
  /** 封面访问地址 [视频封面]. */
  private String fileCoverUrl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Integer getFileType() {
    return fileType;
  }

  public void setFileType(Integer fileType) {
    this.fileType = fileType;
  }

  public Integer getFileDuration() {
    return fileDuration;
  }

  public void setFileDuration(Integer fileDuration) {
    this.fileDuration = fileDuration;
  }

  public Integer getFileWidth() {
    return fileWidth;
  }

  public void setFileWidth(Integer fileWidth) {
    this.fileWidth = fileWidth;
  }

  public Integer getFileHeight() {
    return fileHeight;
  }

  public void setFileHeight(Integer fileHeight) {
    this.fileHeight = fileHeight;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getFileCoverUrl() {
    return fileCoverUrl;
  }

  public void setFileCoverUrl(String fileCoverUrl) {
    this.fileCoverUrl = fileCoverUrl;
  }

  public ChatSingleFile() {
  }

  @Override
  public String toString() {
    return "ChatSingleFile{"
        + "id=" + id
        + ", recordId=" + recordId
        + ", fileType=" + fileType
        + ", fileDuration=" + fileDuration
        + ", fileWidth=" + fileWidth
        + ", fileHeight=" + fileHeight
        + ", fileUrl='" + fileUrl + '\''
        + ", fileCoverUrl='" + fileCoverUrl + '\''
        + '}';
  }
}
