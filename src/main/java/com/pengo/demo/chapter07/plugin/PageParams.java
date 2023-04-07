package com.pengo.demo.chapter07.plugin;

import lombok.Data;

/**
 * @author Benpeng
 * @since 2023/4/4 18:04
 */
@Data
public class PageParams {

    //当前页码
    private Integer page;
    //每页条数
    private Integer pageSize;
    //是否启用插件
    private Boolean useFlag;
    //是否检测当前页码的有效性
    private Boolean checkFlag;
    //当前SQL返回总数，插件回填
    private Integer total;
    //SQL以当前分页的总页数，插件回填
    private Integer totalPage;
}
