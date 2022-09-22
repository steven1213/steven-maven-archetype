package com.steven.maven.archetype.infra.general.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author: steven.cao.
 * @date: 2022/9/22.
 * @version: 1.8.
 */
@Slf4j
public final class AppIdSecretUtils {

    /**
     * 服务器名称
     */
    private final static String SERVER_NAME = "steven.cao1213";
    /**
     * 可利用字符
     */
    private final static String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private AppIdSecretUtils() {
    }

    /**
     * AppId 生成
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
     * 这样重复率大大降低。
     * 经测试，在生成一千万个数据也没有出现重复，完全满足大部分需求。
     *
     * @return String
     */
    public static String appIdGenerator() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * AppSecret 生成
     * 通过appId和内置关键词生成APP Secret
     *
     * @return String
     */
    public static String appSecretGenerator(String appId) {
        try {
            String nonce = UUID.randomUUID().toString().replace("-", "");
            String[] array = new String[]{appId, SERVER_NAME, nonce};
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (String s : array) {
                sb.append(s);
            }
            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder hexStr = new StringBuilder();
            String shaHex = "";
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("generator app secret error.", e);
            throw new RuntimeException();
        }
    }


    public static void main(String[] args) {
        String appId = "YHwDJYwU";
        String appSecret = appSecretGenerator(appId);
        System.out.println("appId: " + appId);
        System.out.println("appSecret: " + appSecret);

        //appId: YHwDJYwU
        //appSecret: 3f588b31e8f032b4ba6528a1628a91f5e6d3fe51
        //appSecret: a216af3be2a1d5ba2bd7b8c5f30edf9c6e009a86

    }
}
