package com.tfdor.service;

import com.tfdor.core.service.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2017/12/17
 * @Time 上午9:42
 */
public interface BaseService extends Service {
  /**
   * 获取公钥
   * @param session
   * @return
   */
  public Map<String, Object> getPublicKey(HttpSession session);
}
