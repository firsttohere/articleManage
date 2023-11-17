package com.xzedu.article.mapper;

import com.xzedu.article.pojo.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName : SysMenuMapper
 * @Description : SysMenuMapper
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:43
 */
@Mapper
public interface SysMenuMapper {
    // 查询所有的菜单
    @Select("select * from sys_menu order by menu_lev asc")
    List<SysMenu> selectAllMenu();
}
