package com.steven.maven.archetype.infra.general.types;

import lombok.Getter;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Getter
public enum ResultCode implements BaseEnums {

    /**
     * success.
     */
    SUCCESS(0, "success"),

    /**
     * failure
     */
    FAILURE(-1, "failure"),

    /**
     * server error.
     */
    SERVER_ERROR(500, "Server error."),

    ;

    private Integer key;

    private String value;

    ResultCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}


