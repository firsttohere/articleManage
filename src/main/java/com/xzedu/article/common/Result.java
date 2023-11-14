package com.xzedu.article.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName : Result
 * @Description : 报文响应结果
 * @Author : Xxxxx
 * @Date: 2023-11-14 13:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private String code;// 响应码值 200代表成功 500代表失败
    private String message;// 响应消息
    private T data;// 响应数据

    public static <K> Result<K> success(K data) {
        return new Result<K>("200", "处理成功", data);
    }

    public static <K> Result<K> success() {
        return new Result<K>("200", "处理成功", null);
    }

    public static <K> Result<K> failure(K data) {
        return new Result<K>("500", "处理失败", data);
    }
}
