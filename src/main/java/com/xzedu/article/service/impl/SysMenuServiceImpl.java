package com.xzedu.article.service.impl;

import com.xzedu.article.common.Result;
import com.xzedu.article.exception.ServiceException;
import com.xzedu.article.mapper.SysMenuMapper;
import com.xzedu.article.pojo.SysMenu;
import com.xzedu.article.pojo.SysMenuRespVo;
import com.xzedu.article.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : SysMenuServiceImpl
 * @Description : SysMenuServiceImpl
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:45
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    // 查询并组装所有的菜单
    @Override
    public List<SysMenuRespVo> allSysMenu() throws ServiceException {
        List<SysMenu> sysMenus = sysMenuMapper.selectAllMenu();

        int size = sysMenus.size(), maxLev = sysMenus.get(size - 1).getMenuLev();

        Map<Integer, List<SysMenu>> levAndMenus =
                sysMenus.stream().collect(Collectors.groupingBy(SysMenu::getMenuLev));

        List<SysMenuRespVo> result = new ArrayList<>();
        Map<Integer, SysMenuRespVo> resultMap = new HashMap<>();// 存放顶级菜单

        Map<Integer, SysMenuRespVo> lastLoopMenu = new HashMap<>();// 存放上一次循环遍历的所有菜单
        for (int j = 0; j <= maxLev; j++) {
            List<SysMenu> jLevMenus = levAndMenus.get(j);
            SysMenuRespVo sysMenuRespVo = null;
            if (j == 0) {
                for (SysMenu jLevMenu : jLevMenus) {
                    sysMenuRespVo = newSysMenuRespVo(jLevMenu);
                    result.add(sysMenuRespVo);
                    resultMap.put(jLevMenu.getMenuNo(), sysMenuRespVo);
                }
                lastLoopMenu = resultMap;
            } else {
                Map<Integer, SysMenuRespVo> currentLoopMenu = new HashMap<>();
                SysMenuRespVo sysMenuRespVoChild = null;
                for (SysMenu jLevMenu : jLevMenus) {// 看上一级菜单中是否存在其父级
                    sysMenuRespVo = lastLoopMenu.get(jLevMenu.getParent());
                    if (sysMenuRespVo == null) {
                        throw new ServiceException("系统菜单配置出错，" + jLevMenu.getMenuName() + "菜单的其父级菜单的menuLev相差不是1");
                    }
                    sysMenuRespVoChild = newSysMenuRespVo(jLevMenu);
                    sysMenuRespVo.getChildren().add(sysMenuRespVoChild);
                    currentLoopMenu.put(jLevMenu.getMenuNo(), sysMenuRespVoChild);
                }
                lastLoopMenu = currentLoopMenu;
            }
        }

        return result;
    }

    private SysMenuRespVo newSysMenuRespVo(SysMenu sysMenu) {
        SysMenuRespVo sysMenuRespVo = new SysMenuRespVo();
        sysMenuRespVo.setMenuNo(sysMenu.getMenuNo());
        sysMenuRespVo.setMenuName(sysMenu.getMenuName());
        sysMenuRespVo.setRouteName(sysMenu.getRouteName());
        sysMenuRespVo.setRoutePath(sysMenu.getRoutePath());
        sysMenuRespVo.setChildren(new ArrayList<>());
        return sysMenuRespVo;
    }
}
