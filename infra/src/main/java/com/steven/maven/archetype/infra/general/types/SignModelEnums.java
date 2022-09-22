package com.steven.maven.archetype.infra.general.types;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Getter
public enum SignModelEnums {

    /**
     * sha256加密方式
     */
    SHA256("SHA256", "sha256加密"),

    ;

    private String code;

    private String desc;

    SignModelEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SignModelEnums getByCode(String code) {
        return Arrays.stream(SignModelEnums.values()).filter(type -> type.code.equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}
