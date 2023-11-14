package com.xzedu.article.exception;

import com.xzedu.article.common.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName : GlobalExceptionHandler
 * @Description : 全局异常处理器
 * @Author : 向州
 * @Date: 2023-11-14 15:31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handlerException(Exception e) {
        // 抛异常了
        String errorMsg = StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败";
        return Result.failure(errorMsg);
    }
}
