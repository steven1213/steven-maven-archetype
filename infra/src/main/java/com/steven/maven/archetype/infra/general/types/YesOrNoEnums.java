package com.steven.maven.archetype.infra.general.types;

import lombok.Getter;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Getter
public enum YesOrNoEnums implements BaseEnums {

    /**
     * 1-yes.
     */
    YES(1, "YES"),
    /**
     * 1-no.
     */
    NO(0, "NO"),

    ;


    private Integer key;

    private String value;

    YesOrNoEnums(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
