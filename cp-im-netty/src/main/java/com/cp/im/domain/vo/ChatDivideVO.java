package com.cp.im.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDivideVO {
    /** ID. */
    private Integer id;

    /** 分组名称. */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChatDivideVO{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
