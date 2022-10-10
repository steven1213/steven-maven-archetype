package com.steven.maven.archetype.infra.log.opt;

import com.google.gson.Gson;
import com.steven.maven.archetype.infra.general.model.OperationLogDomain;
import com.steven.maven.archetype.infra.general.types.YesNoEnums;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @date: 2022/10/10.
 * @version: 1.8.
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {


    @SneakyThrows
    @Around("@annotation(operationLog)")
    public Object around(ProceedingJoinPoint point, OperationLog operationLog) {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        String desc = operationLog.desc();

        OperationLogDomain domain = new OperationLogDomain();
        domain.setClassName(className);
        domain.setMethodName(methodName);
        domain.setOperationDesc(desc);
        log.debug("操作日志采集,类名:[{}],方法名:[{}]", className, methodName);
        Object obj;
        try {
            obj = point.proceed();
            domain.setOperationStatus(YesNoEnums.YES.getKey().shortValue());
        } catch (Exception ex) {
            domain.setOperationStatus(YesNoEnums.NO.getKey().shortValue());
            domain.setErrorMessage(ex.getMessage());
            throw ex;
        } finally {
            log.info("操作日志记录:{}", new Gson().toJson(domain));
            // TODO 若要将操作日志记录到数据库中，可在后续添加实现
        }
        return obj;
    }
}
