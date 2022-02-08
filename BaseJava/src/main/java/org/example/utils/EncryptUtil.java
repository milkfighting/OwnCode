package org.example.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Security;

/**
 * 加密解密工具类
 *
 * @author 王泓桥
 * @date 2022.01.18
 */
public class EncryptUtil {

    private static final String encryptKeyStr = "*&^%3402uykdhkshkdf a idk 122010";

    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        encrypt("000000", "10464");
    }

    /**
     * 加密
     */
    private static void encrypt(String data, String key){
        String returnStr = null;
        try {
            //处理原始数据
            byte[] initData = data.getBytes();

            String tempKey = encryptKeyStr;
            if (key != null && !key.equals(""))
                tempKey += key;

            //产生密钥
            byte[] encryptKey = tempKey.getBytes();
            SecretKey theKey = new SecretKeySpec(encryptKey, "RC4");

            //加密
            Cipher cipher = Cipher.getInstance("RC4", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, theKey);
            //密文
            byte[] encryptedData = cipher.doFinal(initData);
            //进行Base64编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            returnStr = base64Encoder.encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnStr);
    }

    /**
     * 解密
     */
    private static void decrypt(String data, String key) {
        String returnStr = null;
        try {
            //原始密文，，进行base64解码
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] outputData = base64Decoder.decodeBuffer(data);

            String tempKey = encryptKeyStr;
            if (key != null && !key.equals(""))
                tempKey += key;

            //产生密钥
            byte[] encryptKey = tempKey.getBytes();
            SecretKey theKey = new SecretKeySpec(encryptKey, "RC4");

            //解密
            Cipher cipher = Cipher.getInstance("RC4", "BC");
            cipher.init(Cipher.DECRYPT_MODE, theKey);
            //明文
            byte[] output = cipher.doFinal(outputData);

            //处理明文
            returnStr = new String(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnStr);
    }
}
