package com.xzedu.article.service.impl;

import com.xzedu.article.mapper.SysTableMapper;
import com.xzedu.article.pojo.SysTable;
import com.xzedu.article.service.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : SysTableServiceImpl
 * @Description : SysTableServiceImpl
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:29
 */
@Service
public class SysTableServiceImpl implements SysTableService {
    @Autowired
    private SysTableMapper sysTableMapper;
    @Override
    public SysTable getTableConfig(String tableNo) {
        SysTable sysTable = sysTableMapper.getById(tableNo);
        return sysTable;
    }
}
