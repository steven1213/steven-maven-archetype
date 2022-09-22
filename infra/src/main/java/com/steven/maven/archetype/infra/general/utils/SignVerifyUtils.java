package com.steven.maven.archetype.infra.general.utils;

import com.steven.maven.archetype.infra.general.types.SignModelEnums;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Slf4j
public final class SignVerifyUtils {
    private SignVerifyUtils() {
    }

    public static boolean verify(String appId, String appSecret, String timestamp,
                                 String apiVersion, String nonce, String sign, SignModelEnums signModelEnums) {
        // 加密顺序appId-appSecret-timestampStr-nonce-apiVersion
        StringBuilder beforeEncryptStr = new StringBuilder();
        beforeEncryptStr.append(appId).append("-").append(appSecret).append("-").append(timestamp).append("-").append(nonce)
                .append("-").append(apiVersion);
        String afterEncryptStr = null;
        if (SignModelEnums.SHA256.equals(signModelEnums)) {
            afterEncryptStr = EncryptUtils.sha256(beforeEncryptStr.toString());
            log.info("sha256 encrypt sign:{}", afterEncryptStr);
        }
        return sign.equals(afterEncryptStr);
    }
}
