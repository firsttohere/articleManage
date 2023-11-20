package com.xzedu.article.pojo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : PageBean
 * @Description : PageBean
 * @Author : Xxxxx
 * @Date: 2023-11-19 19:34
 */
@Data
public class PageBean<T> {
    private Integer total;
    private List<T> items;
}
