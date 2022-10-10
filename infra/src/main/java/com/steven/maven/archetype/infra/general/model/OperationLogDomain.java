package com.steven.maven.archetype.infra.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author: dr.panda.
 * @date: 2022/10/10.
 * @description: com.steven.maven.archetype.infra.general.model.
 * @version: 1.8.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogDomain extends BaseEntityModel<OperationLogDomain> implements Serializable {

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 操作状态 0-操作失败 1-操作成功
     */
    private Short operationStatus;

    /**
     * 错误信息，当operationStatus=0时，此字段应该有值
     */
    private String errorMessage;
}
