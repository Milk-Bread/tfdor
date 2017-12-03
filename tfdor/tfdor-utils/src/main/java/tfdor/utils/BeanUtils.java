package tfdor.utils;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;


/**
 * @author chepeiqing
 * @date 2016年8月25日 下午3:06:07
 * @description 实体bean跟map互转
 * @modifyBy 修改人
 * @modifyDate 修改时间
 * @modifyNote 修改说明
 */
public class BeanUtils {
    static ThreadLocal<Set> recurseBeanSet = new ThreadLocal() {
        protected synchronized Set initialValue() {
            return new HashSet();
        }
    };

    public static <T> T map2Bean(Map map, T obj) {
        BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();
            if ((!(bw.isWritableProperty(name))) || (!(bw.isReadableProperty(name)))) continue;
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
//                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(name);
                if (value == null) continue;
                if (value.getClass() == class0) {
                    bw.setPropertyValue(name, value);
                } else {
                    String enumValue = String.valueOf(value);
                    if (enumValue.length() <= 0) continue;
                    Enum v = Enum.valueOf(class0, enumValue);
                    bw.setPropertyValue(name, v);
                }
            } else {
//                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(name);
                if (value == null) continue;
                bw.setPropertyValue(name, value);
            }
        }
        return (T) bw.getWrappedInstance();
    }

    public static <T> T map2Bean(Map map, Class<T> clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();
            if ((!(bw.isWritableProperty(name))) || (!(bw.isReadableProperty(name)))) continue;
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
//                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(name);
                if (value == null) continue;
                if (value.getClass() == class0) {
                    bw.setPropertyValue(name, value);
                } else {
                    String enumValue = String.valueOf(value);
                    if (enumValue.length() <= 0) continue;
                    Enum v = Enum.valueOf(class0, String.valueOf(value));
                    bw.setPropertyValue(name, v);
                }
            } else {
//                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(name);
                if (value == null) continue;
                bw.setPropertyValue(name, value);
            }

        }

        return (T) bw.getWrappedInstance();
    }

    public static Map bean2Map(Object beanObject) {
        BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
        PropertyDescriptor[] desc = bean.getPropertyDescriptors();
        Map dataMap = new HashMap(desc.length);
        try {
            for (int i = 0; i < desc.length; ++i) {
                String name = desc[i].getName();
                if ((!(bean.isWritableProperty(name))) || (!(bean.isReadableProperty(name)))) continue;
                Object object = bean.getPropertyValue(name);
                if (object == null) {
                    continue;
                }
//                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                dataMap.put(name, object);
            }
            return dataMap;
        } catch (Exception e1) {
            throw new RuntimeException("tfdor.util.bean2map_fail", e1);
        }
    }

    public static List<Map> listBean2ListMap(List list) {
        List result = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map tmp = bean2Map(it.next());
            result.add(tmp);
        }
        return result;
    }

    public static <T> List<T> listMap2ListBean(List list, Class<T> class0) {
        List result = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object t = map2Bean((Map) it.next(), class0);
            result.add(t);
        }
        return result;
    }

    public static Map bean2MapRecurse(Object beanObject) {
        Set set = (Set) recurseBeanSet.get();

        if (set.contains(beanObject)) {
            return null;
        }
        set.add(beanObject);
        try {
            BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
            PropertyDescriptor[] desc = bean.getPropertyDescriptors();
            Map dataMap = new HashMap();
            try {
                for (int i = 0; i < desc.length; ++i) {
                    String name = desc[i].getName();
                    if ((!(bean.isWritableProperty(name))) || (!(bean.isReadableProperty(name)))) continue;
                    Object object = bean.getPropertyValue(name);
                    if (object == null) {
                        continue;
                    }
//                    String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                    Class class0 = object.getClass();
                    if ((class0.getName().startsWith("java")) || (Enum.class.isAssignableFrom(class0))) {
                        dataMap.put(name, object);
                    } else {
                        Map subMap = bean2MapRecurse(object);
                        if (subMap == null) continue;
                        for (Iterator it = subMap.entrySet().iterator(); it.hasNext();) {
                            Map.Entry entry = (Map.Entry) it.next();
                            dataMap.put(name + "_" + entry.getKey(), entry.getValue());
                        }
                    }
                }
                Map localMap1 = dataMap;
                set.remove(beanObject);
                return localMap1;
            } catch (Exception e1) {
                throw new RuntimeException("tfdor.util.bean2map_fail", e1);
            }
        } finally {
            set.remove(beanObject);
        }
    }

    public static void list2Bean(List<?> srcBeanObject, Object destBeanObject, String listPropName) {
        BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
        destBean.setPropertyValue(listPropName, srcBeanObject);
    }

    public static <T> T bean2Bean(Object srcBeanObject, Class<T> class0) {
        try {
            Object t = class0.newInstance();
            if (srcBeanObject instanceof List) {
                list2Bean((List) srcBeanObject, t, "list");
            } else {
                bean2Bean(srcBeanObject, t);
            }
            return (T) t;
        } catch (Exception e) {
            throw new RuntimeException("tfdor.util.bean2bean_fail", e);
        }
    }

    public static void bean2Bean(Object srcBeanObject, Object destBeanObject) {
        BeanWrapperImpl srcBean = new BeanWrapperImpl(srcBeanObject);
        BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);

        PropertyDescriptor[] destDesc = destBean.getPropertyDescriptors();
        try {
            for (int i = 0; i < destDesc.length; ++i) {
                String name = destDesc[i].getName();

                if ((!(destBean.isWritableProperty(name))) || (!(srcBean.isReadableProperty(name)))) continue;
                Object srcValue = srcBean.getPropertyValue(name);
                if (srcValue != null) {
                    destBean.setPropertyValue(name, srcValue);
                }

            }

        } catch (Exception e1) {
            throw new RuntimeException("tfdor.util.bean2bean_fail", e1);
        }
    }

    public static <T> T map2BeanStrict(Map map, T obj) {
        BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!(bw.isWritableProperty(name))) || (!(bw.isReadableProperty(name)))) continue;
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                Object value = map.get(name);

                if (value == null) continue;
                if (value.getClass() == class0) {
                    bw.setPropertyValue(name, value);
                } else {
                    Enum v = Enum.valueOf(class0, String.valueOf(value));
                    bw.setPropertyValue(name, v);
                }

            } else {
                Object value = map.get(name);

                if (value == null) continue;
                bw.setPropertyValue(name, value);
            }

        }

        return (T) bw.getWrappedInstance();
    }

    public static <T> T map2BeanStrict(Map map, Class<T> clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!(bw.isWritableProperty(name))) || (!(bw.isReadableProperty(name)))) continue;
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                Object value = map.get(name);
                if (value == null) continue;
                if (value.getClass() == class0) {
                    bw.setPropertyValue(name, value);
                } else {
                    Enum v = Enum.valueOf(class0, String.valueOf(value));
                    bw.setPropertyValue(name, v);
                }

            } else {
                Object value = map.get(name);
                if (value == null) continue;
                bw.setPropertyValue(name, value);
            }
        }
        return (T) bw.getWrappedInstance();
    }
}

