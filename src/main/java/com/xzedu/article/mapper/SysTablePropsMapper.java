package com.xzedu.article.mapper;

import com.xzedu.article.pojo.SysTableProps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName : SysTablePropsMapper
 * @Description : SysTablePropsMapper
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:48
 */
@Mapper
public interface SysTablePropsMapper {
    SysTableProps getTableProps(@Param("propsNo") String propsNo);
}
