package com.cp.im.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.cp.im.domain.base.BaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ChatListFilterDto extends BaseInfo {
    /**
     * 筛选
     *
     */
    private String filterName;
}
