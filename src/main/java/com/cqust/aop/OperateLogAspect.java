package com.cqust.aop;

import com.cqust.mapper.OperateLogMapper;
import com.cqust.pojo.OperateLog;
import com.cqust.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    // 属性注入操作日志Mapper
    @Autowired
    private OperateLogMapper operateLogMapper;

    // 定义切点，匹配所有在com.cqust.controller包下的方法

    @Around("@annotation(com.cqust.anno.Log)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 获取操作人ID，这里假设通过某种方式获取，例如从SecurityContext中获取
        Integer operateEmpId = getOperateEmpId();

        // 获取目标类的全类名
        String className = joinPoint.getSignature().getDeclaringTypeName();

        // 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取方法运行时参数
        String methodParams = Arrays.toString(joinPoint.getArgs());

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 获取返回值
        String returnValue = result == null ? "void" : result.toString();

        // 计算方法执行时长
        long costTime = System.currentTimeMillis() - startTime;

        // 创建操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId); // 设置操作人ID
        operateLog.setOperateTime(LocalDateTime.now()); // 设置操作时间
        operateLog.setClassName(className); // 设置目标类的全类名
        operateLog.setMethodName(methodName); // 设置目标方法的方法名
        operateLog.setMethodParams(methodParams); // 设置方法运行时参数
        operateLog.setReturnValue(returnValue); // 设置返回值
        operateLog.setCostTime(costTime); // 设置方法执行时长

        // 记录操作日志
        log.info("操作日志：{}", operateLog);

        // 保存操作日志
        operateLogMapper.insert(operateLog);

        return result;
    }

    private Integer getOperateEmpId() {
        // 获取请求头中的jwt令牌 并解析
        return CurrentHolder.getCurrentId();
    }
}
