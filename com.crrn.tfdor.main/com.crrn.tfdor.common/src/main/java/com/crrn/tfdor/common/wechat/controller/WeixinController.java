package com.crrn.tfdor.common.wechat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crrn.tfdor.domain.manage.CheckModel;
import com.crrn.tfdor.service.wechat.WeChatService;
import com.crrn.tfdor.service.wechat.core.TokenService;
import com.crrn.tfdor.service.wechat.core.Transformer;
import com.crrn.tfdor.utils.Constants;
import com.crrn.tfdor.utils.Dict;
import com.crrn.tfdor.utils.Util;
import com.crrn.tfdor.utils.WeChat;
import com.crrn.tfdor.utils.common.Transport;


@Controller
public class WeixinController {

    @Autowired
    private TokenService tokenService;
    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
    @Resource(name = "httpTransport")
    private Transport transport;
    @Resource
    private WeChatService weChatService;
    @Autowired
    private Transformer transformer;

    /**
     * 开发者模式token校验
     *
     * @param tokenModel
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "wechat", method = RequestMethod.GET)
    @ResponseBody
    public String validate(CheckModel tokenModel) throws ParseException, IOException {
        logger.debug("微信token校验" + tokenModel.toString());
        return tokenService.validate(Constants.WXTOKEN, tokenModel);
    }

    /**
     * Description: 接收微信推送的消息
     *
     * @return
     * @throws ParseException
     * @throws IOException
     * @Version1.0 2016年10月9日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "wechat", method = RequestMethod.POST)
    @ResponseBody
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        Map<String, Object> map = transformer.parse(request);
        Map<String, Object> msgMap = weChatService.msgType(map);
        String respXml = transformer.former(msgMap);
        response.getWriter().write(respXml);
    }

    /**
     * Description: 获取access_token号
     *
     * @return access_token
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "getAccessToken", method = RequestMethod.GET)
    @ResponseBody
    public String getAccessToken() {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.GRANT_TYPE, Constants.CLIENT_CREDENTIAL);
        sendParam.put(Dict.APPID, Constants.APPID);
        sendParam.put(Dict.SECRET, Constants.APPSECRET);
        sendParam.put(Dict.TRANS_NAME, WeChat.TOKEN);
        String accessToken = "";
        try {
            Map<String, Object> access = weChatService.qAccessToken();
            if (access == null || false == (Boolean) access.get("effective")) {
                Map<String, Object> resp = (Map<String, Object>) transport.sendGet(sendParam);
                sendParam = new HashMap<String, Object>();
                sendParam.put("accessToken", resp.get("access_token"));
                sendParam.put("invalidTime", resp.get("expires_in"));
                accessToken = (String) resp.get("access_token");
                if (accessToken != null) {
                    weChatService.dAccessToken();
                    weChatService.iAccessToken(sendParam);
                }
            } else {
                accessToken = (String) access.get("accessToken");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    /**
     * Description: 生成带参数微信二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "createQrcodeImg", method = RequestMethod.POST)
    @ResponseBody
    public void creatQrcodeImage(HttpServletRequest request) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            String id = Util.getSysJournalNo(5,true);
            // 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
            sendParam.put("action_name", Dict.QR_LIMIT_SCENE);
            Map<String, Object> action_info = new HashMap<>();
            Map<String, Object> scene = new HashMap<>();
            scene.put("scene_id", id);
            action_info.put("scene", scene);
            sendParam.put("action_info", action_info);
            sendParam.put(Dict.TRANS_NAME, WeChat.CREAT_QRCODE_IMAGE);
            sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
            // 生成二维码ticket
            Map<String, Object> respTicket = (Map<String, Object>) transport.sendPost(sendParam);
            Map<String ,Object> ticket = new HashMap<>();
            ticket.put("ticket", respTicket.get("ticket"));
            ticket.put("Name", Util.getCurrentDate()+id);
            ticket.put(Dict.TRANS_NAME, WeChat.SHOW_QRCODE);
            transport.sendGet(ticket);
            Map<String, Object> map = new HashMap<>();
            map.putAll(ticket);
            map.putAll(scene);
            map.putAll(respTicket);
            map.put("action_name", Dict.QR_LIMIT_SCENE);
            map.put("appId", Constants.APPID);
            map.put("preservation",Constants.PATH_QRCODE_IMAGE);
            weChatService.iQrcodeimg(map);
        }
    }


    /**
     * Description: 获取永久素材的列表 GET
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "getBatchGetMaterial", method = RequestMethod.POST)
    public
    @ResponseBody
    Object getBatchGetMaterial() throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.BATCHGET_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
        sendParam.put("offset",0);
        sendParam.put("count",20);
        sendParam.put("type","image");
        Map map = (Map) transport.sendPost(sendParam);
        return map;
    }

    /**
     * Description: 新增永久图片素材
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addMaterial", method = RequestMethod.POST)
    public
    @ResponseBody
    Object addMaterial(HttpServletRequest request) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.ADD_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
        sendParam.put(Dict.FILEOBJ, request.getParameter(Dict.FILEOBJ));
        String type = request.getParameter("type");
        Map respMap = (Map) transport.addMaterial(sendParam, type);
        logger.debug(respMap.toString());
        return respMap;
    }

    /**
     * Description: 删除永久图片素材
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "delMaterial", method = RequestMethod.POST)
    @ResponseBody
    public Object delMaterial(HttpServletRequest request) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.DEL_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
        sendParam.put("media_id",request.getParameter("mediaId"));
        Map respMap = (Map) transport.sendPost(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }


    /**
     * Description: 新增永久图文素材
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addNews", method = RequestMethod.POST)
    @ResponseBody
    public Object addNews(HttpServletRequest request) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.ADD_NEWS);
        sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
        List<Map<String,Object>> articles = new ArrayList<>();
        Map<String, Object> news = new HashMap<String, Object>();
        news.put("title","关注微信抢红包");
        news.put("thumb_media_id","7W8JfIKXWeS1_QS7ynY4Keklmv2QV1fhWD6uDOI6oiE");
        news.put("author","微信");
        news.put("digest","没有");
        news.put("show_cover_pic",1);
        news.put("content","我们都是突然长大。那个瞬间，在无可挽回的事实前，学会了从容不迫；在大势所趋时，学会了不动声色。开始保守地给予，迅速地放弃，游刃有余地周旋。在那些众口一辞的节日里，将最好的情感夹杂在寻常祝福中，试图蒙蔽隐秘的初衷。——姬霄");
        news.put("content_source_url","www.baidu.com");
        articles.add(news);
        sendParam.put("articles",articles);
        Map respMap = (Map) transport.sendPost(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }



}

