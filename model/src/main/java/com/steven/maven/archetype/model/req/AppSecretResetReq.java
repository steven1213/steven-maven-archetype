package com.steven.maven.archetype.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppSecretResetReq implements Serializable {

    @NotNull(message = "appId不能为空")
    private String appId;
}
