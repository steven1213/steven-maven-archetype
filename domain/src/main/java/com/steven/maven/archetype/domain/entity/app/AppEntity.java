package com.steven.maven.archetype.domain.entity.app;

import com.baomidou.mybatisplus.annotation.TableName;
import com.steven.maven.archetype.infra.general.model.BaseEntityModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName("t_app")
public class AppEntity extends BaseEntityModel<AppEntity> implements Serializable {

    private String appId;

    private String appName;

    private String appSecret;

}
