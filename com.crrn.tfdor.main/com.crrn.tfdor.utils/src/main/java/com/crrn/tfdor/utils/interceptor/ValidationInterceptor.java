package com.crrn.tfdor.utils.interceptor;

import com.crrn.tfdor.utils.CHECKMSG;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Description: 防止SQL注入
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年6月27日 上午11:01:02 by chepeiqing (chepeiqing@icloud.com)
 */
public class ValidationInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String[] values = request.getParameterValues(name);
      for (String value : values) {
        if (!clearXss(value)) {
          throw new RuntimeException(CHECKMSG.PARAMETER_ERROR_PLEASE_CHECK);
        }
      }
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

  private boolean clearXss(String value) {
    if (value == null || "".equals(value)) {
      return true;
    }
    if (value.indexOf("<") >= 0) {
      return false;
    } else if (value.indexOf(">") >= 0) {
      return false;
    } else if (value.indexOf("(") >= 0) {
      return false;
    } else if (value.indexOf(")") >= 0) {
      return false;
    } else if (value.indexOf("'") >= 0) {
      return false;
    } else if (value.indexOf("eval((") >= 0) {
      return false;
    } else if (value.indexOf("javascript") >= 0) {
      return false;
    } else if (value.indexOf("script") >= 0) {
      return false;
    }
    return true;
  }
}
