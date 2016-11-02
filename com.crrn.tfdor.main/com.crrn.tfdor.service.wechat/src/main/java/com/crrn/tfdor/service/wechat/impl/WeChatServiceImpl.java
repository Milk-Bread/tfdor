package com.crrn.tfdor.service.wechat.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.service.wechat.core.Transformer;
import com.crrn.tfdor.utils.Util;
import com.crrn.tfdor.utils.WeChat;
import com.crrn.tfdor.utils.common.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.WeChantDao;
import com.crrn.tfdor.domain.wechat.Event;
import com.crrn.tfdor.domain.wechat.MsgType;
import com.crrn.tfdor.service.wechat.WeChatService;
import com.crrn.tfdor.utils.Dict;

import javax.annotation.Resource;

@Service
public class WeChatServiceImpl implements WeChatService {
    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    @Autowired
    public WeChantDao weChatDao;
    @Resource(name = "httpTransport")
    private Transport transport;
    @Autowired
    private Transformer transformer;

    @Override
    public void iAccessToken(Map<String, Object> map) {
        weChatDao.iAccessToken(map);
    }

    @Override
    public void dAccessToken(String channelId) {
        weChatDao.dAccessToken(channelId);
    }

    @Override
    public Map<String, Object> qAccessToken(String channelId) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> accessMap = weChatDao.qAccessToken(channelId);
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
     * 查询渠道信息
     *
     * @param channelId
     * @return
     */
    @Override
    public Channel qChannel(String channelId) {
        return weChatDao.qChannel(channelId);
    }

    @Override
    public Map<String, Object> msgType(Map<String, Object> param) {
        logger.debug("Enter a message distribution......");
        String msgType = (String) param.get(Dict.MSGTYPE);
        Map<String, Object> msgMap = new HashMap<>();
        //微信消息为事件消息
        if (msgType.equals(MsgType.event.toString())) {
            logger.debug("Messages are distributed as event messages");
            String event = (String) param.get(Dict.EVENT);
            if (Event.subscribe.toString().equals(event)) {//事件类型为未关注扫码
                logger.debug("事件类型为未关注扫码");
                QrcodeImg qrcodeimg = weChatDao.qQrcodeimgByTicket(param);
                if(qrcodeimg != null) {
                    logger.debug("二维码第一次使用");
                    Map<String, Object> payParam = new HashMap<String, Object>();
                    sendRedPack(msgMap, param);
                    String respXml = transformer.former(msgMap);
                    payParam.put(Dict.PAY_XML, respXml);
                    payParam.put(Dict.TRANS_NAME, WeChat.PAY.SENDREDPACK);
                    try {
                        String str = (String) transport.weChatPay("1402828602", payParam);
                        param.put("state","S");
                        weChatDao.uQrcodeImg(param);
                    } catch (Exception e) {
                        param.put("state","F");
                        weChatDao.uQrcodeImg(param);
                        e.printStackTrace();
                    }
                }
//                msgTypeByImage(msgMap, param);
            } else if (Event.unsubscribe.toString().equals(event)) {
                logger.debug("事件类型为取消关注");

            } else if (Event.SCAN.toString().equals(event)) {
                logger.debug("事件类型为扫码(已经关注)");
//                msgTypeByImage(msgMap, param);
//                msgTypeByNews(msgMap, param);
            } else if (Event.VIEW.toString().equals(event)) {
                logger.debug("事件类型为点击菜单跳转链接时的事件推送");

            } else if (Event.CLICK.toString().equals(event)) {
                logger.debug("事件类型为点击菜单拉取消息时的事件推送");

            } else if (Event.LOCATION.toString().equals(event)) {
                logger.debug("事件类型为上报地理位置");

            }
        } else if (msgType.equals(MsgType.text.toString())) {//微信消息为文本消息
            logger.debug("消息类型为文本消息");
            msgTypeByText(msgMap, param);
        } else if (msgType.equals(MsgType.image.toString())) {//微信消息为图片消息
            logger.debug("消息类型为图片消息");

        } else if (msgType.equals(MsgType.link.toString())) {//微信消息为连接消息
            logger.debug("消息类型为连接消息");

        } else if (msgType.equals(MsgType.shortvideo.toString())) {//微信消息为小视频消息消息
            logger.debug("消息类型为小视频消息");

        } else if (msgType.equals(MsgType.location.toString())) {//微信消息为地理位置消息
            logger.debug("消息类型为地理位置消息");

        } else if (msgType.equals(MsgType.video.toString())) {//微信消息为视频消息消息
            logger.debug("消息类型为视频消息");

        } else if (msgType.equals(MsgType.voice.toString())) {//微信消息为语音消息消息
            logger.debug("消息类型为语音消息");

        }
        return msgMap;
    }

    /**
     * Description:记录生成的二维码
     *
     * @param map
     */
    @Override
    public void iQrcodeimg(Map<String, Object> map) {
        weChatDao.iQrcodeimg(map);
    }

    @Override
    public void sendRedPack(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.put("nonce_str",Util.getSysJournalNo(32,true));//"10000000000000000000000000000002");//随机字符串 随机字符串，不长于32位
        msgMap.put("mch_billno",Util.getOrderId("1402828602",28));//"1402828602201611020000000000");//商户订单号 商户订单号（每个订单号必须唯一）  组成：mch_id+yyyymmdd+10位一天内不能重复的数字。 接口根据商户订单号支持重入，如出现超时可再调用。
        msgMap.put("mch_id","1402828602");//商户号
        msgMap.put("wxappid","wxe7ae25efff1772cb");//微信分配的公众账号ID（企业号corpid即为此appId）。接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的）
        msgMap.put("send_name","涂盟新型建材厂");//商户名称
        msgMap.put("re_openid",param.get("FromUserName"));//"oDPnjw9suWoFzYWZT451sFFdifCo");//接受红包的用户 用户在wxappid下的openid
        msgMap.put("total_amount",Util.moneyYuanToFenByRound("1"));//付款金额，单位分
        msgMap.put("total_num","1");//红包发放总人数 total_num=1
        msgMap.put("wishing","祝万事如意");//红包祝福语
        msgMap.put("client_ip", Util.getLocalIP());//调用接口的机器Ip地址
        msgMap.put("act_name","扫码关注送红包活动");//活动名称  例：猜灯谜抢红包活动
        msgMap.put("remark","扫码");//备注信息   猜越多得越多，快来抢！
        msgMap.put("sign", Util.getSignature("SDDSD88922323TFDOR8892323KJUIJKJ", msgMap));//签名
    }


    /**
     * 消息类型为文本消息处理方法
     *
     * @param msgMap
     * @param param
     */
    private void msgTypeByText(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.put("ToUserName", param.get("FromUserName"));
        msgMap.put("FromUserName", param.get("ToUserName"));
        msgMap.put("CreateTime", param.get("CreateTime"));
        msgMap.put("MsgType", "text");
        msgMap.put("Content", param.get("Content"));
    }

    /**
     * 回复图片消息
     *
     * @param msgMap
     * @param param
     */
    private void msgTypeByImage(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.put("ToUserName", param.get("FromUserName"));
        msgMap.put("FromUserName", param.get("ToUserName"));
        msgMap.put("CreateTime", param.get("CreateTime"));
        msgMap.put("MsgType", "image");
        Map<String, Object> image = new HashMap<>();
        image.put("MediaId", "7W8JfIKXWeS1_QS7ynY4Keklmv2QV1fhWD6uDOI6oiE");
        msgMap.put("Image", image);
    }

    /**
     * 回复图文消息
     *
     * @param msgMap
     * @param param
     */
    private void msgTypeByNews(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.put("ToUserName", param.get("FromUserName"));
        msgMap.put("FromUserName", param.get("ToUserName"));
        msgMap.put("CreateTime", param.get("CreateTime"));
        msgMap.put("MsgType", "news");
        msgMap.put("ArticleCount", "1");
        Map<String, Object> articles = new HashMap<>();
        List<Map<String, Object>> item = new ArrayList<>();
        Map<String, Object> itMap = new HashMap<>();
        itMap.put("Title", "扫了又扫送红包");
        itMap.put("Description", "收红包收的手抽筋");
        itMap.put("PicUrl", "http://mmbiz.qpic.cn/mmbiz_png/7ic1rPwdWGBtxuzjyvYKXMwZgjmyv6EdakJApDRYV1u3gcDKKPzZUibqgdibGY8aBDhUAWUZLDiaNfQ4rdCO4vdVow/0?wx_fmt=png");
        itMap.put("Url", "http://mmbiz.qpic.cn/mmbiz_png/7ic1rPwdWGBtxuzjyvYKXMwZgjmyv6EdakJApDRYV1u3gcDKKPzZUibqgdibGY8aBDhUAWUZLDiaNfQ4rdCO4vdVow/0?wx_fmt=png");
        item.add(itMap);
        articles.put("item", item);
        msgMap.put("Articles", articles);
    }


}
