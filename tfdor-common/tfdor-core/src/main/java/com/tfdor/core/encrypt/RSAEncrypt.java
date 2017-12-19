package com.tfdor.core.encrypt;

import com.tfdor.domain.RSASecretKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2017/12/16
 * @Time 下午1:16
 */
public class RSAEncrypt {

  /**
   * 字节数据转字符串专用集合
   */
  private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  /**
   * 随机生成密钥对
   */
  public static RSASecretKey genKeyPair(){
    KeyPairGenerator keyPairGen= null;
    try {
      keyPairGen= KeyPairGenerator.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    keyPairGen.initialize(1024, new SecureRandom());
    KeyPair keyPair= keyPairGen.generateKeyPair();
    RSASecretKey secretKey = new RSASecretKey();
    secretKey.setPrivateKey((RSAPrivateKey) keyPair.getPrivate());
    secretKey.setPublicKey((RSAPublicKey) keyPair.getPublic());
    return secretKey;
  }

  /**
   * 加密过程
   * @param publicKey 公钥
   * @param plainTextData 明文数据
   * @return
   * @throws Exception 加密过程中的异常信息
   */
  public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{
    if(publicKey== null){
      throw new Exception("加密公钥为空, 请设置");
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      return cipher.doFinal(plainTextData);
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("无此加密算法");
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      return null;
    }catch (InvalidKeyException e) {
      throw new Exception("加密公钥非法,请检查");
    } catch (IllegalBlockSizeException e) {
      throw new Exception("明文长度非法");
    } catch (BadPaddingException e) {
      throw new Exception("明文数据已损坏");
    }
  }

  /**
   * 解密过程
   * @param privateKey 私钥
   * @param cipherData 密文数据
   * @return 明文
   * @throws Exception 解密过程中的异常信息
   */
  public static String decrypt(RSAPrivateKey privateKey, String cipherData) throws Exception{
    if (privateKey== null){
      throw new Exception("解密私钥为空, 请设置");
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] byteDate = cipher.doFinal(Hex.decode(cipherData));
      return new StringBuilder(new String(byteDate)).reverse().toString();
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("无此解密算法");
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      return null;
    }catch (InvalidKeyException e) {
      throw new Exception("解密私钥非法,请检查");
    } catch (IllegalBlockSizeException e) {
      throw new Exception("密文长度非法");
    } catch (BadPaddingException e) {
      throw new Exception("密文数据已损坏");
    }
  }


  /**
   * 字节数据转十六进制字符串
   * @param data 输入数据
   * @return 十六进制内容
   */
  private static String byteArrayToString(byte[] data){
    StringBuilder stringBuilder= new StringBuilder();
    for (int i=0; i<data.length; i++){
      //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
      stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);
      //取出字节的低四位 作为索引得到相应的十六进制标识符
      stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
//      if (i<data.length-1){
//        stringBuilder.append(' ');
//      }
    }
    return stringBuilder.toString();
  }


  public static void main(String[] args){
    RSAEncrypt rsaEncrypt= new RSAEncrypt();
    rsaEncrypt.genKeyPair();
//    //测试字符串
    String encryptStr= "你好啊";
//
    try {
      //加密
//      byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), encryptStr.getBytes());
//      System.out.println("PublicKey "+rsaEncrypt.getPublicKey().getModulus().toString(16));
//      System.out.println("getPrivateKey "+rsaEncrypt.getPrivateKey().getModulus().toString(16));
//
//      //解密
//      byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(), cipher);
//      System.out.println("密文长度:"+ cipher.length);
//
//      System.out.println(RSAEncrypt.byteArrayToString(cipher));
//      System.out.println("明文长度:"+ plainText.length);
//      System.out.println(RSAEncrypt.byteArrayToString(plainText));
//      System.out.println(new String(plainText));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
