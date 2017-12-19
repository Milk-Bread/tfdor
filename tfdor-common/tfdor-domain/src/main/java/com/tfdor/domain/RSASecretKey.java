package com.tfdor.domain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

/**
 * 公私钥实体Bean
 *
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2017/12/17
 * @Time 上午10:06
 */
public class RSASecretKey {
  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;
  private Integer random;

  public Integer getRandom() {
    return random;
  }

  public void setRandom() {
    this.random = (new Random(25).nextInt(9999999));
  }

  public RSAPrivateKey getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(RSAPrivateKey privateKey) {
    this.privateKey = privateKey;
  }

  public RSAPublicKey getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(RSAPublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public String getPrivateKeyModulus() {
    if (getPrivateKey() != null)
      return getPrivateKey().getModulus().toString(16);
    return null;

  }

  /**
   * 获取公钥
   *
   * @return String
   */
  public String getPublicKeyModulus() {
    if (getPublicKey() != null)
      return getPublicKey().getModulus().toString(16);
    return null;

  }

  public String getPrivateKeyExponent() {
    if (getPrivateKey() != null)
      return getPrivateKey().getPrivateExponent().toString(16);
    return null;
  }

  public String getPublicKeyExponent() {
    if (getPublicKey() != null)
      return getPublicKey().getPublicExponent().toString(16);
    return null;
  }

  @Override
  public String toString() {
    return "RSASecretKey{" +
        "privateKey=" + getPrivateKeyModulus() +
        ", publicKey=" + getPublicKeyModulus() +
        ", random=" + random +
        '}';
  }
}
