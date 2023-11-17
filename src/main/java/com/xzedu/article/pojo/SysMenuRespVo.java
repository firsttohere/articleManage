package com.xzedu.article.pojo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : SysMenuRespVo
 * @Description : SysMenuRespVo
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:48
 */
@Data
public class SysMenuRespVo {
    // 菜单编号
    private Integer menuNo;
    // 菜单名字
    private String menuName;
    // 菜单路由
    private String routePath;
    // 菜单路由的name
    private String routeName;
    // 所有子菜单
    private List<SysMenuRespVo> children;
}
