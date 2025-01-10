package com.cp.im.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 分页信息
 * @Author wangyingjie
 * @Date 2020/5/12
 * @Modified
 */
@Data
public class Page extends Base {

    /** 每页条数 */
    @NotNull(message = "页数不能为空")
    public Integer pageSize;

    /** 页码 */
    @NotNull(message = "页码不能为空")
    public Integer pageNum;

}
