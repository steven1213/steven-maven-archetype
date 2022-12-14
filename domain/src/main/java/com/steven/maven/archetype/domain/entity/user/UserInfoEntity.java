package com.steven.maven.archetype.domain.entity.user;

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
@TableName("t_user_info")
public class UserInfoEntity extends BaseEntityModel<UserInfoEntity> implements Serializable {

    private String userName;

    private String email;

    private String password;

    private Short status;
}
