package com.cqust.exception;

import com.cqust.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错了...",e);
        return Result.error("服务器出错 请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错了...",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errorMsg = message.substring(i);
        String[] arr = errorMsg.split(" ");
        return Result.error("您输入的" + arr[5] + "在数据库中已存在 " + arr[2] + " ,请勿重复添加");
    }
}
