package com.steven.maven.archetype.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResp implements Serializable {

    private String appId;

    private String appSecret;
}
