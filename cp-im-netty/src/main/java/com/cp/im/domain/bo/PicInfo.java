package com.cp.im.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 图片信息实体类.
 *
 * @author xubin
 * @version v1.0.0
 * @since 2020/5/8 6:52
 */

@Data
public class PicInfo implements Serializable {
  private static final long serialVersionUID = -542529748081132750L;
  /** 商品图片ID. */
  private Long id;
  /** 图片URL. */
  private String picUrl;
  /** 图片原名. */
  private String picName;
  /** 保存后图片名. */
  private String picNewName;
  /** 文件类型. */
  private String picType;
  /** 最后修改时间. */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifiedTime;
}