package com.crrn.tfdor.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.json.JSONObject;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.aspectj.weaver.ast.And;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.*;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;

/**
 * Description: 工具类
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年6月27日 下午5:30:08 by chepeiqing (chepeiqing@icloud.com)
 */
public class Util {
    private static Logger logger = LoggerFactory.getLogger(Util.class);

    private static final String ALGORITHM = "MD5";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * encode string
     *
     * @param algorithm
     * @param str
     * @return String
     */
    public static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes("UTF-8"));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String encodeByMD5(String str) {
        return encode(ALGORITHM, str);
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }


    /**
     * 获取系统流水号
     *
     * @return 长度为20的全数字
     * 若欲指定返回值的长度or是否全由数字组成等
     */
    public static String getSysJournalNo() {
        return getSysJournalNo(20, true);
    }


    /**
     * 获取系统流水号
     *
     * @param length   指定流水号长度
     * @param isNumber 指定流水号是否全由数字组成
     */
    public static String getSysJournalNo(int length, boolean isNumber) {
        // replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (uuid.length() > length) {
            uuid = uuid.substring(0, length);
        } else if (uuid.length() < length) {
            int j = uuid.length();
            for (int i = 0; i < length - j; i++) {
                uuid = uuid + Math.round(Math.random() * 9);
            }
        }
        if (isNumber) {
            return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
        } else {
            return uuid;
        }
    }

    /**
     * 生成订单号
     *
     * @param mchId
     * @param length
     * @return
     */
    public static String getOrderId(String mchId, int length) {
        String orderid = mchId + getCurrentDate();
        int j = orderid.length();
        for (int i = 0; i < length - j; i++) {
            orderid = orderid + Math.round(Math.random() * 9);
        }
        return orderid;
    }

    /**
     * 判断输入的字符串参数是否为空
     *
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(String input) {
        return null == input || 0 == input.length() || 0 == input.replaceAll("\\s", "").length();
    }


    /**
     * 判断输入的字节数组是否为空
     *
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(byte[] bytes) {
        return null == bytes || 0 == bytes.length;
    }

    public static String trim(String str) {
        return null != str ? str.trim() : str;
    }


    /**
     * 金额元转分
     *
     * @param amount 金额的元进制字符串
     * @return String 金额的分进制字符串
     * 该方法会将金额中小数点后面的数值,四舍五入后只保留两位....如12.345-->12.35
     * 注意:该方法可处理贰仟万以内的金额
     * 注意:如果你的金额达到了贰仟万以上,则非常不建议使用该方法,否则计算出来的结果会令人大吃一惊
     */
    public static String moneyYuanToFenByRound(String amount) {
        if (isEmpty(amount)) {
            return amount;
        }
        if (-1 == amount.indexOf(".")) {
            return Integer.parseInt(amount) * 100 + "";
        }
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        if (pointBehind.length() == 1) {
            return money_fen + Integer.parseInt(pointBehind) * 10 + "";
        }
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        // 下面这种方式用于处理pointBehind=245,286,295,298,995,998等需要四舍五入的情况
        if (pointBehind.length() > 2) {
            int pointString_3 = Integer.parseInt(pointBehind.substring(2, 3));
            if (pointString_3 >= 5) {
                if (pointString_2 == 9) {
                    if (pointString_1 == 9) {
                        money_fen = money_fen + 100;
                        pointString_1 = 0;
                        pointString_2 = 0;
                    } else {
                        pointString_1 = pointString_1 + 1;
                        pointString_2 = 0;
                    }
                } else {
                    pointString_2 = pointString_2 + 1;
                }
            }
        }
        if (pointString_1 == 0) {
            return money_fen + pointString_2 + "";
        } else {
            return money_fen + pointString_1 * 10 + pointString_2 + "";
        }
    }


    /**
     * 金额分转元
     *
     * @param amount 金额的分进制字符串
     * @return String 金额的元进制字符串
     * 注意:如果传入的参数中含小数点,则直接原样返回
     * 该方法返回的金额字符串格式为<code>00.00</code>,其整数位有且至少有一个,小数位有且长度固定为2
     */
    public static String moneyFenToYuan(String amount) {
        if (isEmpty(amount)) {
            return amount;
        }
        if (amount.indexOf(".") > -1) {
            return amount;
        }
        if (amount.length() == 1) {
            return "0.0" + amount;
        } else if (amount.length() == 2) {
            return "0." + amount;
        } else {
            return amount.substring(0, amount.length() - 2) + "." + amount.substring(amount.length() - 2);
        }
    }


    /**
     * 字节数组转为字符串
     * <p>
     * 该方法默认以ISO-8859-1转码
     * 若想自己指定字符集,可以使用<code>getString(byte[] data, String charset)</code>方法
     */
    public static String getString(byte[] data) {
        return getString(data, "ISO-8859-1");
    }


    /**
     * 字节数组转为字符串
     * <p>
     * 如果系统不支持所传入的<code>charset</code>字符集,则按照系统默认字符集进行转换
     */
    public static String getString(byte[] data, String charset) {
        if (isEmpty(data)) {
            return "";
        }
        if (isEmpty(charset)) {
            return new String(data);
        }
        try {
            return new String(data, charset);
        } catch (UnsupportedEncodingException e) {
            logger.error("将byte数组[" + data + "]转为String时发生异常:系统不支持该字符集[" + charset + "]");
            return new String(data);
        }
    }


    /**
     * 获取前一天日期yyyyMMdd
     *
     * @return 返回的日期格式为yyyyMMdd
     * 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * calendar.set(Calendar.YEAR, 2013);
     * calendar.set(Calendar.MONTH, 0);
     * calendar.set(Calendar.DATE, 1);
     * 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     */
    public static String getYestoday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }


    /**
     * 获取当前的日期yyyyMMdd
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }


    /**
     * 获取当前的时间yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }


    /**
     * 将返回报文解析成Map
     *
     * @param message
     * @return
     */
    public static Object getResponseParam(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("http message:===>" + message);
            Map<String, Object> responseMap = mapper.readValue(message, HashMap.class);
            if (responseMap.get("errcode") != null && !"".equals(responseMap.get("errcode")) && !"0".equals(responseMap.get("errcode").toString())) {
                throw new RuntimeException("validation.wachat.error." + responseMap.get("errcode").toString());
            }
            logger.debug("http response:===>" + responseMap);
            return responseMap;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * get请求参数组装
     *
     * @param param
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> param) {
        SortedMap<String, String> sort = new TreeMap<String, String>((HashMap) param);
        if (param != null && !param.isEmpty()) {
            Set es = sort.entrySet();//所有参与传参的参数按照accsii排序(升序)
            StringBuffer sb = new StringBuffer();
            Iterator it = es.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String k = (String) entry.getKey();
                Object v = entry.getValue();
                if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                    sb.append(k + "=" + v + "&");
                }
            }
            String strParam = sb.toString();
            if (strParam.endsWith("&")) {
                strParam = strParam.substring(0, strParam.lastIndexOf("&"));
            }
            logger.debug("http request:===>" + strParam);
            return strParam;
        }
        return null;
    }

    /**
     * 请求参数Map转Json
     *
     * @param map
     * @return
     */
    public static JSONObject mapToJson(Map<String, Object> map) {
        JSONObject json = JSONObject.fromObject(map);
        logger.debug("JSON:===>" + json);
        return json;
    }

    /**
     * 写入材料流
     *
     * @param out
     * @param filePath
     */
    public static void writeMaterial(DataOutputStream out, String filePath) {
        File file = new File(filePath);
        DataInputStream in = null;
        try {
            out.writeBytes("--" + Constants.BOUNDARY + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name='media'; filename='" + file.getName() + "';filelength=" + file.length() + "\r\n");
            out.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
            in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.writeBytes(("\r\n--" + Constants.BOUNDARY + "\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 微信请求签名
     *
     * @param param
     * @return
     */
    public static String getSignature(String key, Map<String, Object> param) {
        String strParam = getUrlParamsByMap(param);
        if (strParam == null) {
            return null;
        }
        strParam = strParam + "&key=" + key;
        // SHA1加密
        String digest = encodeByMD5(strParam).toUpperCase();
        logger.debug("微信签名：" + digest);
        return digest;
    }

    /**
     * 获取服务器IP地址
     *
     * @return
     */
    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    /**
     * 解析xml报文
     *
     * @param xmlString
     * @return
     */
    public static Map<String, Object> parse(String xmlString) throws DocumentException {
        logger.debug("WeChat request message start");

        Document document = DocumentHelper.parseText(xmlString);
        Element rootElement = document.getRootElement();
        // 处理接收消息
        Map<String, Object> map = new HashMap<String, Object>();
        Element root = document.getRootElement();
        logger.debug("WeChat request message:===>\r\n" + document.asXML());
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 将解析结果存储在HashMap中
        // 遍历所有子节点
        xmlToMap(map, elementList);
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
    public static void xmlToMap(Map<String, Object> map, List<Element> elementList) {
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

    public static String zipCompressorByAnt(String srcPathName) {
        File srcdir = new File(srcPathName);
        if (!srcdir.exists()){
            throw new RuntimeException(srcPathName + "不存在！");
        }
        logger.debug("压缩文件／目录："+srcPathName);
        File zipFile = new File(srcPathName+".zip");
        if(zipFile.exists()){
            return srcPathName+".zip";
        }
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
        //fileSet.setExcludes(...); //排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
        logger.debug("压缩文件名："+srcPathName+".zip");
        return srcPathName+".zip";
    }

    public static void main(String [] argo) throws DocumentException {
        zipCompressorByAnt("/Users/chepeiqing/Desktop/WeChat/images/1402828602/20161109234019");
//        System.out.println(parse("<xml><ToUserName><![CDATA[gh_716331599724]]></ToUserName><FromUserName><![CDATA[oDPnjwxXE6QhsTr7AmlBzPS4Xul8]]></FromUserName><CreateTime>1478054845</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_25432]]></EventKey><Ticket><![CDATA[gQG_7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3l6aGFvTERscldUVTF4Tk1pQlRJAAIE51IZWAMEAAAAAA==]]></Ticket></xml>"));
//        System.out.println(getOrderId("1402828602",28));
    }
}
