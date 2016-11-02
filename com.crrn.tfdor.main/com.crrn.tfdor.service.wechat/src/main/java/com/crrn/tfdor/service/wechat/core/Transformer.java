package com.crrn.tfdor.service.wechat.core;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crrn.tfdor.utils.common.MapEntryConverter;
import com.thoughtworks.xstream.XStream;

@Service
public class Transformer {


    private static Logger logger = LoggerFactory.getLogger(Transformer.class);

    /**
     * 解析xml报文
     *
     * @param request
     * @return
     */
    @Transactional
    public Map<String, Object> parse(HttpServletRequest request) {
        logger.debug("WeChat request message start");
        // 处理接收消息
        Map<String, Object> map = new HashMap<String, Object>();
        SAXReader reader = new SAXReader();
        try {
            ServletInputStream in = request.getInputStream();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            logger.debug("WeChat request message:===>\r\n" + document.asXML());
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 将解析结果存储在HashMap中
            // 遍历所有子节点
            xmlToMap(map, elementList);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("WeChat request message map:===>" + map);
        logger.debug("WeChat request message end");
        return map;
    }

    /**
     * 递归解析xml报文
     *
     * @param map
     * @param elementList
     */
    private static void xmlToMap(Map<String, Object> map, List<Element> elementList) {
        for (Element e : elementList) {
            List<Element> element = e.elements();
            if (element.size() == 0) {
                map.put(e.getName(), e.getText());
                logger.debug("WeChat request message param ===>" + e.getName() + ":" + e.getText());
            } else {
                logger.debug("WeChat request message param ===>" + e.getName());
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> map1 = new HashMap<>();
                for (Element e1 : element) {
                    List<Element> element1 = e1.elements();
                    if (element1.size() == 0) {
                        logger.debug("WeChat request message param ===>" + e1.getName() + ":" + e1.getText());
                        map1.put(e1.getName(), e1.getText());
                    } else {
                        Map<String, Object> map2 = new HashMap<>();
                        xmlToMap(map2, element1);
                        list.add(map2);
                        map1.put(e1.getName(), list);
                    }
                }
                map.put(e.getName(), map1);
            }
        }
    }

    /**
     * 组装返回xml
     *
     * @param map
     * @return
     */
    @Transactional
    public String former(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        logger.debug("WeChat response message start");
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXml(sb, map);
        sb.append("</xml>");
//        XStream xstream = new XStream();
//        xstream.alias("xml", Map.class);
//        xstream.registerConverter(new MapEntryConverter());
//        String respXml = xstream.toXML(map);
        logger.debug("WeChat response message :==>\r\n" + sb.toString());
        logger.debug("WeChat response message end");
        return sb.toString();
    }

    private void mapToXml(StringBuffer sb,Map<String, Object> map){
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object keyValue = map.get(key);
            if (null == keyValue)
                keyValue = "";
            if (keyValue instanceof ArrayList) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<").append(key).append(">").append("\n");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXml(sb, map);
                }
                sb.append("</").append(key).append(">").append("\n");
            } else {
                if (keyValue instanceof HashMap) {
                    sb.append("<").append(key).append(">").append("\n");
                    mapToXml(sb, (HashMap) keyValue);
                    sb.append("</").append(key).append(">").append("\n");
                } else {
                    sb.append("<").append(key).append(">");
                    Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
                    Pattern patternFloat = Pattern.compile("[0-9]+");
                    if (!patternInt.matcher(keyValue.toString()).matches() || !patternFloat.matcher(keyValue.toString()).matches()) {
                        sb.append("<![CDATA[");
                    }
                    sb.append(keyValue.toString());
                    if (!patternInt.matcher(keyValue.toString()).matches() || !patternFloat.matcher(keyValue.toString()).matches()) {
                        sb.append("]]>");
                    }
                    sb.append("</").append(key).append(">").append("\n");
                }
            }
        }
    }

}
