package com.steven.maven.archetype.infra.log.opt;

import java.lang.annotation.*;

/**
 * @author dr.panda
 * @date: 2022/10/10.
 * @version: 1.8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    String desc();
}
