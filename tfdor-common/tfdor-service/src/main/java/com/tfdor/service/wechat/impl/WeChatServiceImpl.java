package com.tfdor.service.wechat.impl;

import com.tfdor.core.transport.Transport;
import com.tfdor.dao.WeChantDao;
import com.tfdor.domain.manage.Channel;
import com.tfdor.domain.manage.Merchant;
import com.tfdor.domain.wechat.CreateQrcodeImg;
import com.tfdor.domain.wechat.CustomerInfo;
import com.tfdor.domain.wechat.QrcodeImg;
import com.tfdor.enums.Event;
import com.tfdor.enums.MsgType;
import com.tfdor.service.event.MsgEvent;
import com.tfdor.service.wechat.WeChatService;
import com.tfdor.tools.dicts.CheckMsg;
import com.tfdor.tools.dicts.Constants;
import com.tfdor.tools.dicts.Dict;
import com.tfdor.tools.dicts.WeChat;
import com.tfdor.tools.utils.BeanUtils;
import com.tfdor.tools.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService {
  private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
  @Autowired
  public WeChantDao weChatDao;
  @Resource(name = Dict.HTTPTRANSPORT)
  private Transport transport;
  @Autowired
  private MsgEvent msgEvent;

  /**
   * Description:查询 AccessToken
   * @param appId
   * @return
   * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public String getAccessToken(String appId) throws Exception {
    Map<String, Object> sendParam = new HashMap<String, Object>();
    String accessToken = "";
    try {
      Map<String, Object> access = qAccessToken(appId);
      if (access == null || false == (Boolean) access.get("effective")) {
        Merchant merch = qMerchant(appId);
        sendParam.put(Dict.GRANT_TYPE, Constants.CLIENT_CREDENTIAL);
        sendParam.put(Dict.APPID, merch.getAppId());
        sendParam.put(Dict.SECRET, merch.getAppSecret());
        sendParam.put(Dict.TRANS_NAME, WeChat.ACCESS_TOKEN);
        Map<String, Object> resp = (Map<String, Object>) transport.sendGet(sendParam);
        sendParam = new HashMap<String, Object>();
        sendParam.put("accessToken", resp.get("access_token"));
        sendParam.put("invalidTime", resp.get("expires_in"));
        sendParam.put("mchId", merch.getMchId());
        accessToken = (String) resp.get("access_token");
        if (accessToken != null) {
          weChatDao.dAccessToken(merch.getMchId());
          weChatDao.iAccessToken(sendParam);
        }
      } else {
        accessToken = (String) access.get("accessToken");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return accessToken;
  }

  /**
   * Description:查询jsapiTicket
   * @param appId
   * @return
   * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public String getJsapiTicket(String appId) throws Exception {
    Map<String, Object> sendParam = new HashMap<String, Object>();
    String ticket = "";
    Map<String, Object> jsTicket = qJsapiTicket(appId);
    if (jsTicket == null || false == (Boolean) jsTicket.get("effective")) {
      Merchant merch = qMerchant(appId);
      sendParam.put(Dict.ACCESS_TOKEN, getAccessToken(appId));
      sendParam.put(Dict.TRANS_NAME, WeChat.JSAPI.GETTICKET);
      sendParam.put("type", "jsapi");
      Map<String, Object> resp = (Map<String, Object>) transport.sendGet(sendParam);
      sendParam = new HashMap<String, Object>();
      sendParam.put("jsapiTicket", resp.get("ticket"));
      sendParam.put("invalidTime", resp.get("expires_in"));
      sendParam.put("mchId", merch.getMchId());
      ticket = (String) resp.get("ticket");
      if (ticket != null) {
        weChatDao.dJsapiTicket(merch.getMchId());
        weChatDao.iJsapiTicket(sendParam);
      }
    } else {
      ticket = (String) jsTicket.get("jsapiTicket");
    }
    return ticket;
  }

  /**
   * 查询AccessToken
   * @param appId
   * @return
   * @throws ParseException
   */
  private Map<String, Object> qAccessToken(String appId) throws ParseException {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Map<String, Object> accessMap = weChatDao.qAccessToken(appId);
    if (accessMap == null) {
      return null;
    }
    logger.debug(accessMap.toString());
    Timestamp createTime = (Timestamp) accessMap.get("createTime");
    String invalidTime = (String) accessMap.get("invalidTime");
    long createlong = df.parse(df.format(createTime)).getTime();
    long time = new Date().getTime() - createlong;
    long inbalidlong = Long.valueOf(invalidTime) * 1000 - 1000;
    if (inbalidlong > time) {
      accessMap.put("effective", true);
    } else {
      accessMap.put("effective", false);
    }
    return accessMap;
  }

  /**
   * 查询qJsapiTicket
   * @param appId
   * @return
   * @throws ParseException
   */
  private Map<String, Object> qJsapiTicket(String appId) throws ParseException {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Map<String, Object> ticketMap = weChatDao.qJsapiTicket(appId);
    if (ticketMap == null) {
      return null;
    }
    logger.debug(ticketMap.toString());
    Timestamp createTime = (Timestamp) ticketMap.get("createTime");
    String invalidTime = (String) ticketMap.get("invalidTime");
    long createlong = df.parse(df.format(createTime)).getTime();
    long time = new Date().getTime() - createlong;
    long inbalidlong = Long.valueOf(invalidTime) * 1000 - 1000;
    if (inbalidlong > time) {
      ticketMap.put("effective", true);
    } else {
      ticketMap.put("effective", false);
    }
    return ticketMap;
  }

  /**
   * 查询渠道信息
   * @param channelId
   * @return
   */
  @Override
  public Channel qChannel(String channelId) {
    return weChatDao.qChannel(channelId);
  }

  @Override
  public Map<String, Object> msgType(Map<String, Object> param, HttpServletResponse response, Merchant merchant) throws Exception {
    logger.debug("Enter a message distribution......");
    String msgType = (String) param.get(Dict.MSGTYPE);
    Map<String, Object> msgMap = new HashMap<>();
    // 记录客户信息
    this.getCustomerInfo(merchant.getMchSeq(), param.get("FromUserName").toString(), merchant.getAppId());
    // 微信消息为事件消息
    if (msgType.equals(MsgType.event.toString())) {
      logger.debug("Messages are distributed as event messages");
      String event = (String) param.get(Dict.EVENT);
      if (Event.subscribe.toString().equals(event)) {// 事件类型为未关注扫码
        logger.debug("事件类型为未关注扫码");
        param.put("Content", "你好！欢迎关注" + merchant.getMchName());
        msgEvent.msgTypeByText(msgMap, param);
        msgEvent.activityByRedPack(msgMap, param, merchant);
      } else if (Event.unsubscribe.toString().equals(event)) {
        logger.debug("事件类型为取消关注");

      } else if (Event.SCAN.toString().equals(event)) {
        logger.debug("事件类型为扫码(已经关注)");
        msgEvent.activityByRedPack(msgMap, param, merchant);
      } else if (Event.VIEW.toString().equals(event)) {
        logger.debug("事件类型为点击菜单跳转链接时的事件推送");

      } else if (Event.CLICK.toString().equals(event)) {
        logger.debug("事件类型为点击菜单拉取消息时的事件推送");

      } else if (Event.LOCATION.toString().equals(event)) {
        logger.debug("事件类型为上报地理位置");

      }
    } else if (msgType.equals(MsgType.text.toString())) {// 微信消息为文本消息
      logger.debug("消息类型为文本消息");
      msgEvent.msgTypeByText(msgMap, param);
    } else if (msgType.equals(MsgType.image.toString())) {// 微信消息为图片消息
      logger.debug("消息类型为图片消息");

    } else if (msgType.equals(MsgType.link.toString())) {// 微信消息为连接消息
      logger.debug("消息类型为连接消息");

    } else if (msgType.equals(MsgType.shortvideo.toString())) {// 微信消息为小视频消息消息
      logger.debug("消息类型为小视频消息");

    } else if (msgType.equals(MsgType.location.toString())) {// 微信消息为地理位置消息
      logger.debug("消息类型为地理位置消息");

    } else if (msgType.equals(MsgType.video.toString())) {// 微信消息为视频消息消息
      logger.debug("消息类型为视频消息");

    } else if (msgType.equals(MsgType.voice.toString())) {// 微信消息为语音消息消息
      logger.debug("消息类型为语音消息");

    }
    return msgMap;
  }

  /**
   * Description:记录生成的二维码
   * @param qrcodeImg
   */
  @Override
  public void iQrcodeimg(QrcodeImg qrcodeImg) {
    weChatDao.iQrcodeimg(qrcodeImg);
  }

  /**
   * 查询商户信息
   * @param appId
   * @return
   */
  @Override
  public Merchant qMerchant(String appId) {
    Merchant merch = weChatDao.qMerchant(appId);
    if (merch == null) {
      throw new RuntimeException(CheckMsg.ILLEGAL_REQUEST_PARAMETERS);
    }
    return merch;
  }


  /**
   * 记录生成微信二维码
   * @param createQrcodeImg
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public void addQrcode(CreateQrcodeImg createQrcodeImg, String appId) throws Exception {
    weChatDao.iCreateQrcodeImage(createQrcodeImg);
    // 生成微信二维码
    this.generateQrCode(createQrcodeImg, appId);
  }

  /**
   * 生成修改二维码参数
   * @param createQrcodeImg
   */
  @Override
  public void modifyCreateQrcodeImage(CreateQrcodeImg createQrcodeImg) {
    weChatDao.modifyCreateQrcodeImage(createQrcodeImg);
  }

  /**
   * 创建二维码
   * @param crQimg
   * @param appId
   * @return
   */
  public void generateQrCode(CreateQrcodeImg crQimg, String appId) throws Exception {
    Map<String, Object> sendParam = new HashMap<String, Object>();
    for (int i = 0; i < crQimg.getNumber(); i++) {
      String sceneStr = crQimg.getMchId() + Util.getCurrentTime() + Util.getSysJournalNo(8, true);
      // 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
      sendParam.put("action_name", crQimg.getActionName());
      Map<String, Object> action_info = new HashMap<>();
      Map<String, Object> scene = new HashMap<>();
      if (Dict.QR_SCENE.equals(crQimg.getActionName())) {
        sendParam.put("expire_seconds", Integer.valueOf(crQimg.getExpireSeconds()) * 24 * 60 * 60);
        scene.put("scene_id", sceneStr);
      } else if (Dict.QR_LIMIT_STR_SCENE.equals(crQimg.getActionName())) {
        scene.put("scene_str", sceneStr);
      } else {
        throw new RuntimeException(CheckMsg.QR_CODE_TYPE_ERROR);
      }
      action_info.put("scene", scene);
      sendParam.put("action_info", action_info);
      sendParam.put(Dict.TRANS_NAME, WeChat.CREAT_QRCODE_IMAGE);
      sendParam.put(Dict.ACCESS_TOKEN, getAccessToken(appId));
      // 生成二维码ticket
      Map<String, Object> respTicket = (Map<String, Object>) transport.sendPost(sendParam);
      Map<String, Object> ticket = new HashMap<String, Object>();
      ticket.put("ticket", respTicket.get("ticket"));
      ticket.put("qrcodeName", sceneStr);
      ticket.put("preservation", crQimg.getPreservation());
      ticket.put(Dict.TRANS_NAME, WeChat.SHOW_QRCODE);
      // 获取二维码
      transport.sendGet(ticket);
      QrcodeImg qrcodeImg = new QrcodeImg();
      qrcodeImg.setMchId(crQimg.getMchId());
      qrcodeImg.setCreateQISeq(crQimg.getCreateQISeq());
      qrcodeImg.setQrcodeName(sceneStr);
      qrcodeImg.setSceneStr(sceneStr);
      qrcodeImg.setTicket(respTicket.get("ticket").toString());
      qrcodeImg.setUrl(respTicket.get("url").toString());
      weChatDao.iQrcodeimg(qrcodeImg);
    }
  }

  /**
   * 获取用户信息
   * @param openId
   * @param appId
   * @return
   */
  public void getCustomerInfo(Integer mchSeq, String openId, String appId) throws Exception {
    Map<String, Object> sendParam = new HashMap<String, Object>();
    sendParam.put(Dict.TRANS_NAME, WeChat.GET_USERINFO);
    sendParam.put(Dict.ACCESS_TOKEN, getAccessToken(appId));
    sendParam.put(Dict.LANG, "zh_CN");
    sendParam.put("openid", openId);
    CustomerInfo info = weChatDao.qCustomerInfo(openId);
    Map<String, Object> customerInfo;
    try {
      customerInfo = (Map<String, Object>) transport.sendGet(sendParam);
    } catch (Exception e) {
      logger.debug("The public number has no authority", e);
      return;
    }
    CustomerInfo custm = BeanUtils.map2Bean(customerInfo, CustomerInfo.class);
    custm.setOpenId(customerInfo.get("openid").toString());
    custm.setNickName(customerInfo.get("nickname").toString());
    custm.setSubscribeTime(customerInfo.get("subscribe_time").toString());
    custm.setMchSeq(mchSeq);
    if (info == null) {
      weChatDao.iCustomerInfo(custm);
    } else {
      weChatDao.uCustomerInfo(custm);
    }
  }

}
