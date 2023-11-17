package com.xzedu.article.mapper;

import com.xzedu.article.pojo.SysTableColumns;
import com.xzedu.article.pojo.SysTableProps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName : SysTablePropsMapper
 * @Description : SysTablePropsMapper
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:48
 */
@Mapper
public interface SysTableColumnsMapper {
    List<SysTableColumns> getTableColumns(@Param("tableNo") String tableNo);
}
