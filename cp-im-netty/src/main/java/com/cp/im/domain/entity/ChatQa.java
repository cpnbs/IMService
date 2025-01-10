package com.cp.im.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ChatQa implements Serializable {
    private static final long serialVersionUID = -5505982337053354594L;

    /** 主键. */
    private String id;

    /** 问答. */
    private String qa;

    /** 排序ID. */
    private Integer sort;

    /** 创建时间. */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 更新时间. */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
