package com.cp.im.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TranslateDTO {
    /** 文本内容. */
    @NotNull(message = "文本内容不能为空")
    private String text;
    /** 文本所属语言。如果不知道，可以使用auto. */
    @NotNull(message = "所属语言不能为空")
    private String sourceLang;
    /** 目标语言。必须是明确的有效的目标语言. */
    @NotNull(message = "目标语言不能为空")
    private String targetLang;
}
