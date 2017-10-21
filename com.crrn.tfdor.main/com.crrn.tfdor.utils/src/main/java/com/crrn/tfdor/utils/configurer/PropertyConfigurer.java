package com.crrn.tfdor.utils.configurer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: 加载properties
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年6月27日 下午4:57:51 by chepeiqing (chepeiqing@icloud.com)
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
  private static Map<String, String> myPropertiesMap;
  private static String defaultEncoding = "UTF-8";

  @Override
  protected void processProperties(ConfigurableListableBeanFactory factory, Properties props) throws BeansException {
    super.processProperties(factory, props);
    myPropertiesMap = new HashMap<String, String>();
    for (Object key : props.keySet()) {
      String keyStr = key.toString();
      String value = props.getProperty(keyStr);
      try {
        myPropertiesMap.put(keyStr, new String(value.getBytes("ISO-8859-1"), defaultEncoding));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
  }

  public static String getMessage(String name) {
    return myPropertiesMap.get(name);
  }

  public void setDefaultEncoding(String defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }
}
