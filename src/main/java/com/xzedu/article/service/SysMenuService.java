package com.xzedu.article.service;

import com.xzedu.article.common.Result;
import com.xzedu.article.exception.ServiceException;
import com.xzedu.article.pojo.SysMenuRespVo;

import java.util.List;

/**
 * @ClassName : SysMenuService
 * @Description : SysMenuService
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:45
 */
public interface SysMenuService {

    List<SysMenuRespVo> allSysMenu() throws ServiceException;
}
