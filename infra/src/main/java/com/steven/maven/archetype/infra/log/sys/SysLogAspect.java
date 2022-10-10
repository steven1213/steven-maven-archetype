package com.steven.maven.archetype.infra.log.sys;

import com.google.gson.Gson;
import com.steven.maven.archetype.infra.general.model.OperationLogDomain;
import com.steven.maven.archetype.infra.general.types.YesNoEnums;
import com.steven.maven.archetype.infra.log.opt.OperationLog;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: dr.panda.
 * @date: 2022/10/10.
 * @description: com.steven.maven.archetype.infra.log.sys.
 * @version: 1.8.
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {

        //获取请求信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        String requestURI = request.getRequestURI();
        String methodName = point.getSignature().getName();
        String clazzName = point.getTarget().getClass().getSimpleName();

        boolean printReturn = sysLog.printReturn();

        //获取请求参数：
        MethodSignature ms = (MethodSignature) point.getSignature();
        //获取请求参数类型
        String[] parameterNames = ms.getParameterNames();
        //获取请求参数值
        Object[] parameterValues = point.getArgs();
        StringBuilder sb = new StringBuilder();

        //组合请求参数，进行日志打印
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=")
                            .append(new Gson().toJson(parameterValues[i]))
                            .append("]");
                }
            }
        }

        long begin = System.currentTimeMillis();
        Object obj;
        try {
            obj = point.proceed();
            long end = System.currentTimeMillis();
            if (printReturn) {
                log.info("系统日志|请求成功|URI信息:【{}】,请求映射控制类:【{}】,请求方法:【{}】,请求参数列表:【{}】,返回结果:【{}】,用时【{}】毫秒", requestURI, clazzName, methodName,
                        sb.toString(), new Gson().toJson(obj), (end - begin));
            } else {
                log.info("系统日志|请求成功|URI信息:【{}】,请求映射控制类:【{}】,请求方法:【{}】,请求参数列表:【{}】,用时【{}】毫秒", requestURI, clazzName, methodName,
                        sb.toString(), (end - begin));
            }

        } catch (Exception ex) {
            log.error("系统日志|请求出错|URI信息:【{}】,请求映射控制类:【{}】,请求方法:【{}】,请求参数列表:【{}】,出错信息:【{}】", requestURI, clazzName, methodName,
                    sb.toString(), ex.getMessage());
            throw ex;
        }
        return obj;
    }
}
