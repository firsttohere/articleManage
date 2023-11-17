package com.xzedu.article.service;

import com.xzedu.article.pojo.SysTable;

/**
 * @ClassName : SysTableService
 * @Description : SysTableService
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:28
 */
public interface SysTableService {
    SysTable getTableConfig(String tableNo);
}
