package com.crrn.tfdor.utils.httpclient;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.crrn.tfdor.utils.Constants;
import com.crrn.tfdor.utils.Dict;
import com.crrn.tfdor.utils.Util;
import com.crrn.tfdor.utils.common.Transport;

import javax.net.ssl.SSLContext;

public class HttpClientTransport implements Transport {
    private static Logger logger = LoggerFactory.getLogger(HttpClientTransport.class);
    /**
     * 读取超时时间
     **/
    private Integer readTimeout;
    /**
     * 连接超时时间
     **/
    private Integer connectTimeout;


    /**
     * Description: 发送http Get请求
     *
     * @param sendParam
     * @return
     * @throws Exception
     */
    @Override
    public Object sendGet(Map<String, Object> sendParam) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            logger.debug("Get请求");
            String urlNameString = sendParam.get(Dict.TRANS_NAME).toString() + "?" + Util.getUrlParamsByMap(sendParam);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setConnectTimeout(connectTimeout);// 连接超时30秒
            connection.setReadTimeout(readTimeout);// 读取超时30秒
            // connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                logger.debug(key + "===>" + map.get(key));
            }
            if ("image/jpg".equals(connection.getContentType())) {
                InputStream inputStream = connection.getInputStream();
                try {
                    File file = new File(Constants.PATH_QRCODE_IMAGE);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    byte[] data = new byte[1024];
                    int len = 0;
                    FileOutputStream fileOutputStream = null;
                    fileOutputStream = new FileOutputStream(sendParam.get("preservation") + "/" + sendParam.get("qrcodeName") + ".jpg");
                    while ((len = inputStream.read(data)) != -1) {
                        fileOutputStream.write(data, 0, len);
                    }
                } catch (IOException e) {
                    logger.debug("图片写入失败", e);
                } finally {
                    if (inputStream != null) inputStream.close();
                }
                return null;
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.debug("发送GET请求出现异常！", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return Util.getResponseParam(result);
    }

    /**
     * Description: 发送http Post请求
     *
     * @param sendParam
     * @return
     * @throws Exception
     */
    @Override
    public Object sendPost(Map<String, Object> sendParam) throws Exception {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            logger.debug("Post请求");
            URL realUrl = new URL(sendParam.get(Dict.TRANS_NAME).toString() + "?" + Dict.ACCESS_TOKEN + "=" + sendParam.get(Dict.ACCESS_TOKEN));
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setConnectTimeout(connectTimeout);// 连接超时30秒
            connection.setReadTimeout(readTimeout);// 读取超时30秒
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(connection.getOutputStream());
            // 发送请求参数
            out.writeBytes(Util.mapToJson(sendParam).toString());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.debug("发送 POST 请求出现异常！", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeIo(out, in);
        }
        return Util.getResponseParam(result);
    }

    /**
     * Description: 上传素材
     *
     * @param sendParam
     * @param type      媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     * @throws Exception
     */
    @Override
    public Object addMaterial(Map<String, Object> sendParam, String type) throws Exception {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            logger.debug("Post请求");
            URL realUrl = new URL(sendParam.get(Dict.TRANS_NAME).toString() + "?" + Dict.ACCESS_TOKEN + "=" + sendParam.get(Dict.ACCESS_TOKEN) + "&type=" + type);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + Constants.BOUNDARY);
            connection.setConnectTimeout(connectTimeout);// 连接超时30秒
            connection.setReadTimeout(readTimeout);// 读取超时30秒
            connection.setRequestProperty("Charset", "UTF-8");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(connection.getOutputStream());
            // 发送请求参数
            logger.debug("http request message type===>\t" + type + "\tparam===>" + sendParam.toString());
            uploadImages(out, sendParam);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.debug("发送 POST 请求出现异常！", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeIo(out, in);
        }
        return Util.getResponseParam(result);
    }

    /**
     * 微信支付
     *
     * @param sendParam
     * @param mchId
     * @return
     * @throws Exception
     */
    @Override
    public Object weChatPay(String mchId, Map<String, Object> sendParam) throws Exception {
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File("/myData/software/cert/apiclient_cert.p12"));
        keyStore.load(instream, mchId.toCharArray());
        instream.close();
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
        SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslcsf).build();
        HttpPost httpost = new HttpPost(sendParam.get(Dict.TRANS_NAME).toString());
        httpost.addHeader("Connection", "keep-alive");
        httpost.addHeader("Accept", "*/*");
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpost.addHeader("Host", "api.mch.weixin.qq.com");
        httpost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpost.addHeader("Cache-Control", "max-age=0");
        httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        httpost.setEntity(new StringEntity(sendParam.get(Dict.PAY_XML).toString(), "UTF-8"));
        CloseableHttpResponse response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        logger.debug(jsonStr);
        EntityUtils.consume(entity);
        Map<String, Object> resp = Util.parse(jsonStr);
        if(!"SUCCESS".equals(resp.get("return_code")) || null == resp.get("result_code") || !"SUCCESS".equals(resp.get("result_code"))){
            throw new RuntimeException(resp.get("return_msg").toString());
        }
        return resp;
    }

    /**
     * 上传图片
     *
     * @param out
     * @param map
     */
    private static void uploadImages(DataOutputStream out, Map<String, Object> map) {
        Object fileObj = map.get(Dict.FILEOBJ);
        if (fileObj instanceof List) {
            List<String> fileList = (List<String>) fileObj;
            for (String filePath : fileList) {
                Util.writeMaterial(out, filePath);
            }
        } else if (fileObj instanceof String) {
            Util.writeMaterial(out, fileObj.toString());
        }
    }

    private void closeIo(DataOutputStream out, BufferedReader in) {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

}
