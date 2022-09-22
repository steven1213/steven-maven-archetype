package com.steven.maven.archetype.infra.general.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Md5Crypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Slf4j
@Getter
public final class EncryptUtils {

    private EncryptUtils() {
    }

    public static String sha256(String str) {
        MessageDigest messageDigest;
        String encryptStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            encryptStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("sha256 encrypt error.", e);
        }
        return encryptStr;
    }

    public static String md5(String str) {
        return Md5Crypt.md5Crypt(str.getBytes(StandardCharsets.UTF_8));
    }
}
