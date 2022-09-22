package com.steven.maven.archetype.infra.general.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: steven.cao
 * @date: 2022/8/19.
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntityModel<T> implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String creator;

    private String updater;

    private Date crtTime;

    private Date uptTime;

    @TableLogic
    private Short delFlag;
}
