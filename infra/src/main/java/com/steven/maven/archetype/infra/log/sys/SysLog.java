package com.steven.maven.archetype.infra.log.sys;

import java.lang.annotation.*;

/**
 * @author: dr.panda.
 * @date: 2022/10/10.
 * @version: 1.8.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 是否打印返回结果 默认打印，当返回分页，列表时建议 设置为false
     *
     * @return
     */
    boolean printReturn() default true;
}
