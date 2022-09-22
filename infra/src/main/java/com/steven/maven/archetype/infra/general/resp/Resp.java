package com.steven.maven.archetype.infra.general.resp;

import com.steven.maven.archetype.infra.general.types.ResultCode;
import com.steven.maven.archetype.infra.general.utils.ThreadMdcUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Resp<T> implements Serializable {

    private Integer code;

    private String msg;

    private String traceId;

    private Boolean success;

    private T data;

    public static <T> Resp<T> success() {
        return success(null);
    }

    public static <T> Resp<T> success(T data) {
        return success(ResultCode.SUCCESS.getKey(), ResultCode.SUCCESS.getValue(), data);
    }

    public static <T> Resp<T> success(Integer code, String msg, T data) {
        return getResp(code, msg, data);
    }

    public static <T> Resp<T> failure(Integer code, String msg) {
        return failure(code, msg, null);
    }

    public static <T> Resp<T> failure(Integer code, String msg, T data) {
        return getResp(code, msg, data);
    }

    private static <T> Resp<T> getResp(Integer code, String msg, T data) {
        return Resp.<T>builder()
                .code(code)
                .success(ResultCode.SUCCESS.getKey().equals(code))
                .msg(msg)
                .data(data)
                .traceId(ThreadMdcUtils.getTraceId())
                .build();
    }
}
