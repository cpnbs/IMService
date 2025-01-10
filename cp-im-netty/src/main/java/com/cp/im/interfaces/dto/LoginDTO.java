package com.cp.im.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoginDTO {
    /** 账号. */
    @NotNull(message = "账号不能为空")
    private String account;
    /** 密码. */
    @NotNull(message = "密码不能为空")
    private String password;
}
