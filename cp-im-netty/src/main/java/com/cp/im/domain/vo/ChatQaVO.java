package com.cp.im.domain.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatQaVO {
    private Integer id;

    private String qa;

    /** ID. */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 问答. */
    public Object getQa() {
        return JSONObject.parse(qa);
    }

    public void setQa(String qa) {
        this.qa = qa;
    }

    @Override
    public String toString() {
        return "ChatQaVO{"
                + "id=" + id
                + ", qa='" + qa + '\''
                + '}';
    }
}
