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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.WeChantDao;
import com.crrn.tfdor.domain.wechat.Event;
import com.crrn.tfdor.domain.wechat.MsgType;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.service.wechat.WeChatService;
import com.crrn.tfdor.utils.Dict;

@Service
public class WeChatServiceImpl implements WeChatService {
    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    @Autowired
    public WeChantDao weChatDao;

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
        return null;
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
                msgTypeByImage(msgMap, param);
            } else if (Event.unsubscribe.toString().equals(event)) {
                logger.debug("事件类型为取消关注");

            } else if (Event.SCAN.toString().equals(event)) {
                logger.debug("事件类型为扫码(已经关注)");
                msgTypeByNews(msgMap, param);
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
