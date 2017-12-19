package com.tfdor.tools.utils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chepeiqing
 * @date 2016年8月25日 下午3:06:07
 * @description 实体bean跟map互转
 * @modifyBy 修改人
 * @modifyDate 修改时间
 * @modifyNote 修改说明
 */
public class BeanUtils {

  public static Map<String, Object> bean2Map(Object javaBean) {
    Map map = new HashMap();
    try {
      // 获取javaBean属性
      BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      if (propertyDescriptors != null && propertyDescriptors.length > 0) {
        String propertyName = null; // javaBean属性名
        Object propertyValue = null; // javaBean属性值
        for (PropertyDescriptor pd : propertyDescriptors) {
          propertyName = pd.getName();
          if (!propertyName.equals("class")) {
            Method readMethod = pd.getReadMethod();
            propertyValue = readMethod.invoke(javaBean, new Object[0]);
            map.put(propertyName, propertyValue);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  public static <T> T map2Bean(Map map,Class<T> javaBean){
    try {
      // 获取javaBean属性
      BeanInfo beanInfo = Introspector.getBeanInfo(javaBean);
      // 创建 JavaBean 对象
      Object obj = javaBean.newInstance();
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      if (propertyDescriptors != null && propertyDescriptors.length > 0) {
        String propertyName = null; // javaBean属性名
        Object propertyValue = null; // javaBean属性值
        for (PropertyDescriptor pd : propertyDescriptors) {
          propertyName = pd.getName();
          if (map.containsKey(propertyName)) {
            propertyValue = map.get(propertyName);
            Method setMethod = pd.getWriteMethod();
            setMethod.invoke(obj, new Object[] { propertyValue });
          }
        }
        return (T) obj;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
