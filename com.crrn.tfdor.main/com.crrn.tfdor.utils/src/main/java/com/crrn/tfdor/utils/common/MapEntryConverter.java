package com.crrn.tfdor.utils.common;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by chepeiqing on 16/10/9.
 */
public class MapEntryConverter implements Converter {
    @SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }


    @SuppressWarnings("rawtypes")
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        AbstractMap map = (AbstractMap) value;
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object keyValue = map.get(key);
            if (null == keyValue)
                keyValue = "";
            if (keyValue instanceof ArrayList) {
                ArrayList list = (ArrayList) map.get(key);
                writer.startNode(key);
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    marshal(hm, writer, context);
                }
                writer.endNode();
            } else {
                if (keyValue instanceof HashMap) {
                    writer.startNode(key);
                    marshal((HashMap) keyValue, writer, context);
                    writer.endNode();
                } else {
                    writer.startNode(key);
//                    Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
//                    Pattern patternFloat = Pattern.compile("[0-9]+");
//                    if(!patternInt.matcher(keyValue.toString()).matches() || !patternFloat.matcher(keyValue.toString()).matches()) {
//                        writer.setValue("<<![CDATA[");
//                    }
                    writer.setValue(keyValue.toString());
//                    if(!patternInt.matcher(keyValue.toString()).matches() || !patternFloat.matcher(keyValue.toString()).matches()) {
//                        writer.setValue("]]>>");
//                    }
                    writer.endNode();
                }
            }
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, String> map = new HashMap<String, String>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            map.put(reader.getNodeName(), reader.getValue());
            reader.moveUp();
        }
        return map;
    }
}
