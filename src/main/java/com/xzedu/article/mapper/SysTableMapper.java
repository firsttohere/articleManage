package com.xzedu.article.mapper;

import com.xzedu.article.pojo.SysTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName : SysTableMapper
 * @Description : SysTableMapper
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:29
 */
@Mapper
public interface SysTableMapper {
    SysTable getById(@Param("tableNo") String tableNo);
}
