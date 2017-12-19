package com.tfdor.service.impl;

import com.tfdor.domain.RSASecretKey;
import com.tfdor.service.BaseService;
import com.tfdor.tools.dicts.CheckMsg;
import com.tfdor.tools.dicts.Dict;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2017/12/17
 * @Time 上午9:46
 */
@Service
public class BaseServiceImpl implements BaseService {
  /**
   * 获取公钥
   *
   * @param session
   * @return
   */
  @Override
  public Map<String, Object> getPublicKey(HttpSession session) {
    RSASecretKey secretKey = (RSASecretKey) session.getAttribute(Dict.SECRETKEY);
    if(secretKey == null){
      throw new ValidationException(CheckMsg.GAIN_PUBLIC_KEY_FAILURE);
    }
    secretKey.setRandom();
    Map<String, Object> secretMap = new HashMap<String, Object>();
    secretMap.put("modulus",secretKey.getPublicKeyModulus());
    secretMap.put("exponent",secretKey.getPublicKeyExponent());
    secretMap.put("random",secretKey.getRandom());
    return secretMap;
  }
}
