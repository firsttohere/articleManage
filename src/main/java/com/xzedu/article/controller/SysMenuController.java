package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.SysMenuRespVo;
import com.xzedu.article.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : SysMenuController
 * @Description : SysMenuController
 * @Author : Xxxxx
 * @Date: 2023-11-16 19:46
 */
@RestController
@CrossOrigin
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/all")
    public Result<List<SysMenuRespVo>> allSysMenu() {
        return Result.success(sysMenuService.allSysMenu());
    }
}
