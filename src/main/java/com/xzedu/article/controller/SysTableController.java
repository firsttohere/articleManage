package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.SysTable;
import com.xzedu.article.service.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : SysTableController
 * @Description : SysTableController
 * @Author : Xxxxx
 * @Date: 2023-11-17 12:24
 */
@RestController
@RequestMapping("/sysTable")
@CrossOrigin
public class SysTableController {

    @Autowired
    private SysTableService sysTableService;

    @GetMapping("/queryConf/{tableNo}")
    public Result<SysTable> getTableConfig(@PathVariable("tableNo") String tableNo) {
        return Result.success(sysTableService.getTableConfig(tableNo));
    }
}
