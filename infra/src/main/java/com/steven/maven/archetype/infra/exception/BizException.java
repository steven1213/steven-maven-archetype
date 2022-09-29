package com.steven.maven.archetype.infra.exception;

import com.steven.maven.archetype.infra.general.types.BaseEnums;
import lombok.Data;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Data
public class BizException extends RuntimeException {
    private Integer code;

    private String msg;

    public BizException() {
        super();
    }

    public BizException(BaseEnums baseEnums) {
        this.code = baseEnums.getKey();
        this.msg = baseEnums.getValue();
    }

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
