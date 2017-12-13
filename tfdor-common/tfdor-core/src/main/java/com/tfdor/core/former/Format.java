package com.tfdor.core.former;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chepeiqing 
 * @date 2017年12月13日 上午1:00:27
 * @description 报文解析
 * @Version1.0
 */
public interface Format {
  /**
   * @author chepeiqing
   * @date 2017年12月13日 上午1:00:06
   * @description 解析xml报文
   * @param HttpServletRequest request
   * @return Map
   */
  public Map<String, Object> parse(HttpServletRequest request);
  /**
   * @author chepeiqing
   * @date 2017年12月13日 上午1:00:58
   * @description 组装返回xml
   * @param Map
   * @return String
   */
  public String former(Map<String, Object> map);
}
