package tfdor.common.controller.wechat;

import tfdor.domain.manage.CheckModel;
import tfdor.domain.manage.Merchant;
import tfdor.domain.wechat.CreateQrcodeImg;
import tfdor.service.wechat.WeChatService;
import tfdor.service.wechat.core.TokenService;
import tfdor.service.wechat.core.Transformer;
import tfdor.utils.Dict;
import tfdor.utils.Util;
import tfdor.utils.WeChat;
import tfdor.utils.aes.WXBizMsgCrypt;
import tfdor.utils.common.Transport;
import tfdor.utils.configurer.PropertyConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Merchant merch = weChatService.qMerchant(tokenModel.getAppId());
        return tokenService.validate(merch.getWxToken(), tokenModel);
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
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response, CheckModel tokenModel) throws Exception {
        logger.debug("微信request param name" + request.getParameterNames());
        logger.debug("微信request param value" + request.getParameterMap().toString());
        Merchant merch = weChatService.qMerchant(tokenModel.getAppId());
        //验证微信消息
        tokenService.validate(merch.getWxToken(), tokenModel);
        Map<String, Object> map = transformer.parse(request);
        if (Dict.ENCRYPT_AES.equals(tokenModel.getEncrypt_type())) {//安全模式
            //微信消息解密工具类
            WXBizMsgCrypt ct = new WXBizMsgCrypt(merch.getWxToken(), merch.getEncodingAesKey(), merch.getAppId());
            //解密
            Map<String, Object> decryMap = ct.decryptMsg(tokenModel.getMsg_signature(), tokenModel.getTimestamp().toString(), tokenModel.getNonce().toString(), (String) map.get("fromXML"));
            Map<String, Object> msgMap = weChatService.msgType(decryMap, response, merch);
            //微信返回消息
            String respXml = transformer.former(msgMap);
            //微信返回消息加密
            String respXmlEncryp = ct.encryptMsg(respXml, tokenModel.getTimestamp().toString(), tokenModel.getNonce().toString());
            logger.debug("安全模式返回报文：\\r\\n" + respXmlEncryp);
            //返回微信数据
            response.getWriter().write(respXmlEncryp);
        } else {//明文模式
            Map<String, Object> msgMap = weChatService.msgType(map, response, merch);
            //微信返回消息
            String respXml = transformer.former(msgMap);
            //返回微信数据
            response.getWriter().write(respXml);
        }
    }


    /**
     * Description: 生成带参数微信二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "createQrcodeImg.do", method = RequestMethod.POST)
    @ResponseBody
    public void createQrcodeImage(HttpServletRequest request, CreateQrcodeImg createQrcodeImg) throws Exception {
        String appId = request.getParameter("appId");
        Merchant mch = weChatService.qMerchant(appId);
        String imagesPath = PropertyConfigurer.getMessage("config.imagesPath");
        createQrcodeImg.setPreservation(imagesPath +"/" + "images" + "/" + mch.getMchId() + "/" + Util.getCurrentTime());
        createQrcodeImg.setMchId(mch.getMchId());
        weChatService.addQrcode(createQrcodeImg, appId);
    }

    /**
     * 修改二维码参数
     *
     * @param createQrcodeImg
     * @throws Exception
     */
    @RequestMapping(value = "modifyCreateQrcodeImage.do", method = RequestMethod.POST)
    @ResponseBody
    public void modifyCreateQrcodeImage(CreateQrcodeImg createQrcodeImg) throws Exception {
        weChatService.modifyCreateQrcodeImage(createQrcodeImg);
    }


    /**
     * Description: 获取永久素材的列表 GET
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "getBatchGetMaterial.do", method = RequestMethod.POST)
    public
    @ResponseBody
    Object getBatchGetMaterial(HttpServletRequest request,String appId,String type) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.BATCHGET_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        sendParam.put("offset", 0);
        sendParam.put("count", 20);
        sendParam.put("type", type);
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
    @RequestMapping(value = "addMaterial.do", method = RequestMethod.POST)
    public
    @ResponseBody
    Object addMaterial(HttpServletRequest request, String channelId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.ADD_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(channelId));
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
    @RequestMapping(value = "delMaterial.do", method = RequestMethod.POST)
    @ResponseBody
    public Object delMaterial(HttpServletRequest request, String appId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.DEL_MATERIAL);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        sendParam.put("media_id", request.getParameter("mediaId"));
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
    @RequestMapping(value = "addNews.do", method = RequestMethod.POST)
    @ResponseBody
    public Object addNews(HttpServletRequest request, String appId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.ADD_NEWS);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        List<Map<String, Object>> articles = new ArrayList<>();
        Map<String, Object> news = new HashMap<String, Object>();
        news.put("title", "关注微信抢红包");
        news.put("thumb_media_id", "7W8JfIKXWeS1_QS7ynY4Keklmv2QV1fhWD6uDOI6oiE");
        news.put("author", "微信");
        news.put("digest", "没有");
        news.put("show_cover_pic", 1);
        news.put("content", "我们都是突然长大。那个瞬间，在无可挽回的事实前，学会了从容不迫；在大势所趋时，学会了不动声色。开始保守地给予，迅速地放弃，游刃有余地周旋。在那些众口一辞的节日里，将最好的情感夹杂在寻常祝福中，试图蒙蔽隐秘的初衷。——姬霄");
        news.put("content_source_url", "www.baidu.com");
        articles.add(news);
        sendParam.put("articles", articles);
        Map respMap = (Map) transport.sendPost(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }

    /**
     * Description: 新增微信菜单
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addWechatMenu.action", method = RequestMethod.POST)
    @ResponseBody
    public Object addWechatMenu(HttpServletRequest request, String appId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.CREATE_MENU);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        List<Map<String, Object>> button = new ArrayList<Map<String, Object>>();
        Map<String, Object> buttonMap = new HashMap<String, Object>();
        buttonMap.put("name","管理端");
        buttonMap.put("type","view");
        buttonMap.put("url","http://www.tfdor.cc/webapp/index.html");
        button.add(buttonMap);

        Map<String, Object> buttonMap2 = new HashMap<String, Object>();
        buttonMap2.put("name","百度搜");
        buttonMap2.put("type","view");
        buttonMap2.put("url","https://www.baidu.com");
        button.add(buttonMap2);

        Map<String, Object> buttonMap3 = new HashMap<String, Object>();
        buttonMap3.put("name","微商城");
        buttonMap3.put("type","view");
        buttonMap3.put("url","https://www.tfdor.cc/web/index.html");
        button.add(buttonMap3);

        sendParam.put("button",button);
        Map respMap = (Map) transport.sendPost(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }

    /**
     * Description: 删除微信菜单
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "deleteWechatMenu.action", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteWechatMenu(HttpServletRequest request, String appId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.DELETE_MENU);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        Map respMap = (Map) transport.sendGet(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }

    /**
     * Description: 查询微信菜单
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "getWechatMenu.action", method = RequestMethod.POST)
    @ResponseBody
    public Object getWechatMenu(HttpServletRequest request, String appId) throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.TRANS_NAME, WeChat.GET_MENU);
        sendParam.put(Dict.ACCESS_TOKEN, weChatService.getAccessToken(appId));
        Map respMap = (Map) transport.sendGet(sendParam);
        logger.debug(respMap.toString());
        return respMap;
    }



     /**
     * Description: 查询JSApiTicket
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "getJsConfigData.action", method = RequestMethod.POST)
    @ResponseBody
    public Object getJsConfigData(HttpServletRequest request, String appId) throws Exception {
        appId = "wx67c2134f4935acfb";
        long timestamp = new Date(System.currentTimeMillis()).getTime()/1000;
        String nonceStr = "Wm3WZYTPz0wcrrnW";
        Map<String, Object> sendParam = new HashMap<String, Object>();
        String jsTicket = weChatService.getJsapiTicket(appId);
        sendParam.put("timestamp",timestamp);
        sendParam.put("noncestr",nonceStr);
        sendParam.put("jsapi_ticket",jsTicket);
        sendParam.put("url","http://localhost/web/index.html");
        String signature = Util.getJsApiSignature(sendParam);
        sendParam.put("appId",appId);
        sendParam.put("signature",signature);
        logger.debug(sendParam.toString());
        return sendParam;
    }





}

