package com.tfdor.service.event;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfdor.domain.manage.Merchant;
import com.tfdor.domain.wechat.RedPackBean;
import com.tfdor.enums.AmountType;
import com.tfdor.enums.RedpackType;

import com.tfdor.core.transformer.Transformer;
import com.tfdor.core.transport.Transport;
import com.tfdor.dao.WeChantDao;
import com.tfdor.tools.dicts.CheckMsg;
import com.tfdor.tools.dicts.Dict;
import com.tfdor.tools.dicts.WeChat;
import com.tfdor.tools.utils.Util;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2016/11/10
 * @Time 下午11:30
 */
@Service
public class MsgEvent {
    private static Logger logger = LoggerFactory.getLogger(MsgEvent.class);
    @Autowired
    public WeChantDao weChatDao;
    @Resource(name = Dict.HTTPTRANSPORT)
    private Transport transport;
    @Autowired
    private Transformer transformer;

    /**
     * 营销推广-现金红包
     *
     * @param merchant
     * @param param
     */
    @Transactional
    public void activityByRedPack(Map<String, Object> msgMap, Map<String, Object> param, Merchant merchant) throws Exception {
        Map<String, Object> payMsgMap = new HashMap<>();
        Map<String, Object> paramSql = new HashMap<String, Object>();
        String eventKey = (String) param.get("EventKey");
        if (eventKey.indexOf("qrscene_") == 0) {//第一次扫码
            eventKey = eventKey.substring(8);
            param.put("EventKey", eventKey);
        }
        paramSql.put("sceneStr", eventKey);
        Map<String, Object> qrcodeImg = weChatDao.qQrcodeimgBysCeneStr(paramSql);
        if(qrcodeImg == null){
            param.put("Content", "您好,该二维码不能识别");
            msgTypeByText(msgMap, param);
            return;
        }
        Map<String, Object> paramRedPack = new HashMap<>();
        paramRedPack.put("mchId",merchant.getMchId());
        paramRedPack.put("redPackSeq",qrcodeImg.get("redPackSeq"));
        RedPackBean redPackBean = weChatDao.queryRedPackByMchId(paramRedPack);
        if (redPackBean == null) {
            param.put("Content", "您好，欢迎关注" + merchant.getMchName());
            msgTypeByText(msgMap, param);
            return;
        }
        if (qrcodeImg != null && "N".equals(redPackBean.getState()) && "N".equals(qrcodeImg.get("cState")) && ("I".equals(qrcodeImg.get("qState")) || "F".equals(qrcodeImg.get("qState")))) {
            Timestamp beginDate = (Timestamp) qrcodeImg.get("beginDate");
            Timestamp endDate = (Timestamp) qrcodeImg.get("endDate");
            Timestamp time = new Timestamp(System.currentTimeMillis());
            if (time.getTime() <= beginDate.getTime()) {
                param.put("Content", "您好，活动暂未开放，敬请期待");
                msgTypeByText(msgMap, param);
            } else if (time.getTime() >= endDate.getTime()) {
                param.put("Content", "您好，本期活动已经结束，感谢您的支持");
                msgTypeByText(msgMap, param);
            } else if (beginDate.getTime() <= time.getTime() && time.getTime() <= endDate.getTime()) {
                logger.debug(">>>>>>>>微信红包准备中～～～～～～");
                logger.debug("二维码第一次使用");
                sendRedPack(payMsgMap, param, merchant, redPackBean);
            }
        } else if (!"N".equals(qrcodeImg.get("cState"))) {
            param.put("Content", "您好，活动已经结束，敬请关注");
            msgTypeByText(msgMap, param);
        } else if ("S".equals(qrcodeImg.get("qState"))) {
            param.put("Content", "您好，该二维码已经使用过了");
            msgTypeByText(msgMap, param);
        }
    }


    /**
     * 微信现金红包发送
     *
     * @param msgMap
     * @param param
     */
    private void sendRedPack(Map<String, Object> msgMap, Map<String, Object> param, Merchant merchant, RedPackBean redPackBean) throws Exception {
        Map<String, Object> payParam = new HashMap<String, Object>();
        String transjnl = Util.getOrderId(merchant.getMchId(), 32);
        String orderId = Util.getOrderId(merchant.getMchId(), 28);
        msgMap.put("nonce_str", transjnl);//随机字符串 随机字符串，不长于32位
        msgMap.put("mch_billno", orderId);//商户订单号 商户订单号（每个订单号必须唯一）  组成：mch_id+yyyymmdd+10位一天内不能重复的数字。 接口根据商户订单号支持重入，如出现超时可再调用。
        msgMap.put("mch_id", merchant.getMchId());//商户号
        msgMap.put("wxappid", merchant.getAppId());//微信分配的公众账号ID（企业号corpid即为此appId）。接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的）
        msgMap.put("send_name", merchant.getMchName());//商户名称
        msgMap.put("re_openid", param.get("FromUserName"));//接受红包的用户 用户在wxappid下的openid
        if (RedpackType.FNRK.toString().equals(redPackBean.getRedPackType())) {//微信裂变红包
            msgMap.put("amt_type", "ALL_RAND");
            payParam.put(Dict.TRANS_NAME, WeChat.PAY.SENDGROUPREDPACK);//裂变红包接口名称
        } else if (RedpackType.OYRK.toString().equals(redPackBean.getRedPackType())) {
            payParam.put(Dict.TRANS_NAME, WeChat.PAY.SENDREDPACK);//现金红包接口名称
        }
        String amount;
        if (AmountType.FDAT.toString().equals(redPackBean.getAmountType())) {//固定金额红包
            amount = Util.moneyYuanToFenByRound(redPackBean.getTotalAmount());
        } else if (AmountType.RMAT.toString().equals(redPackBean.getAmountType())) {//随机金额红包
            String[] totalAmount = redPackBean.getTotalAmount().split("-");
            //精确小数点2位
            NumberFormat formatter = new DecimalFormat("#.##");
            // 设置最小的金额
            double moneyMin = Double.valueOf(totalAmount[0]);
            Random random = new Random();
            //随机一个数，数值范围在最小值与余额之间
            String money = formatter.format(random.nextDouble() * (Double.valueOf(totalAmount[1]) - moneyMin) + moneyMin);
            amount = Util.moneyYuanToFenByRound(money);
        } else {
            throw new RuntimeException(CheckMsg.RED_AMOUNT_TYPE_ERROR);
        }
        msgMap.put("total_amount", amount);//付款金额，单位分
        msgMap.put("total_num", redPackBean.getTotalNum());//红包发放总人数
        msgMap.put("wishing", redPackBean.getWishing());//红包祝福语
        msgMap.put("client_ip", Util.getLocalIP());//调用接口的机器Ip地址
        msgMap.put("act_name", redPackBean.getActName());//活动名称
        msgMap.put("remark", redPackBean.getRemark());//备注信息
        msgMap.put("sign", Util.getSignature(merchant.getSignatureKey(), msgMap));//签名
        String respXml = transformer.former(msgMap);
        payParam.put(Dict.PAY_XML, respXml);
        param.put("transjnl", transjnl);
        param.put("orderId", orderId);
        try {
            Map<String, Object> resp = (Map<String, Object>) transport.submit(merchant.getMchId(), payParam);
            param.put("state", "S");
            param.put("sceneStr", param.get("EventKey"));
            weChatDao.uQrcodeImg(param);
        } catch (Exception e) {
            param.put("state", "F");
            param.put("sceneStr", param.get("EventKey"));
            weChatDao.uQrcodeImg(param);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 消息类型为文本消息处理方法
     *
     * @param msgMap
     * @param param
     */
    @Transactional
    public void msgTypeByText(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.clear();
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
    @Transactional
    public void msgTypeByImage(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.clear();
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
    @Transactional
    public void msgTypeByNews(Map<String, Object> msgMap, Map<String, Object> param) {
        msgMap.clear();
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
