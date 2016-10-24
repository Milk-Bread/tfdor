package com.crrn.tfdor.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * Description: AES加密 AES加密为base 64 code<br>
 * AES解密 将base 64 code AES解密
 * Copyright (c) CSII.
 * All Rights Reserved.
 *
 * @version 1.0 2016年3月18日 下午2:11:54 by chepeiqing (chepeiqing@icloud.com)
 */
@SuppressWarnings("restriction")
public class EncodeUtil {

    /**
     * 加解密秘钥
     */
    private static String encodeKey = "xTPktTlqJQvGdWoYyfP7PmDPbdm1nGfOeoY8bNyOFLCnfYKz7MJ2KDlwL0MI94gE";

    /**
     * Description: base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @param bytes
     * @return
     */
    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        return EncodeUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * AES加密
     *
     * @param content 待加密的内容
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encodeKey.getBytes());
        kgen.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("UTF-8"));
    }

    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        try {
            return base64Encode(aesEncryptToBytes(content));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encodeKey.getBytes());
        kgen.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr) {
        try {
            return EncodeUtil.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        } else if ("".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(aesEncrypt("admin288888888"));
    }
}
