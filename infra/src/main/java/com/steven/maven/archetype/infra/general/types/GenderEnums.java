package com.steven.maven.archetype.infra.general.types;

import lombok.Getter;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Getter
public enum GenderEnums implements BaseEnums {

    /**
     * 0-女性.
     */
    FEMALE(0, "女性"),
    /**
     * 1-男性.
     */
    MALE(1, "男性"),

    /**
     * 2-其他.
     */
    OTHER(2, "其他");

    private Integer key;

    private String value;

    GenderEnums(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
