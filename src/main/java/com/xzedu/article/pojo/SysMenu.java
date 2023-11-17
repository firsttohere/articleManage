package com.xzedu.article.pojo;

import lombok.Data;

/**
 * @ClassName : SysMenu
 * @Description : 系统菜单
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:42
 */
@Data
public class SysMenu {
    private Integer menuNo;
    private String menuName;
    private Integer parent;
    private Integer menuLev;
    private String routePath;
    private String routeName;
}
