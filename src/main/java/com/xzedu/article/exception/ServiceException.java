package com.xzedu.article.exception;

/**
 * @ClassName : ServiceException
 * @Description : ServiceException
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:12
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
