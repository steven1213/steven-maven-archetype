CREATE TABLE `t_user_info`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name` varchar(64) NOT NULL COMMENT '用户名',
    `email`     varchar(64) NOT NULL COMMENT '邮箱',
    `password`  varchar(64) NOT NULL COMMENT '密码',
    `status`    tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用 0-未启用 1-已启用',
    `crt_time`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `upt_time`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`   varchar(64) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `updater`   varchar(64) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `del_flag`  tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标识 0-未删除 1-删除',
    PRIMARY KEY (`id`),
    KEY         `idx_upt_time` (`upt_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE `t_app`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `app_id`     varchar(64) NOT NULL COMMENT 'appId',
    `app_secret` varchar(64) NOT NULL COMMENT 'appSecret',
    `app_name`   varchar(64) NOT NULL COMMENT 'app name',
    `desc`       varchar(256) COMMENT '描述',
    `crt_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `upt_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`    varchar(64) NOT NULL DEFAULT 'system' COMMENT '创建人',
    `updater`    varchar(64) NOT NULL DEFAULT 'system' COMMENT '修改人',
    `del_flag`   tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标识 0-未删除 1-删除',
    PRIMARY KEY (`id`),
    KEY          `idx_app_id` (`app_id`) USING BTREE,
    KEY          `idx_upt_time` (`upt_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';