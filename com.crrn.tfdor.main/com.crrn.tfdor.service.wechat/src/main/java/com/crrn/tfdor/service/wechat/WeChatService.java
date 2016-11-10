package com.crrn.tfdor.service.wechat;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.manage.Merchant;
import com.crrn.tfdor.domain.wechat.CreateQrcodeImg;
import com.crrn.tfdor.domain.wechat.QrcodeImg;

import javax.servlet.http.HttpServletResponse;

public interface WeChatService {


    /**
     * Description:查询accessToken
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public String getAccessToken(String channelId) throws Exception;

    /**
     * 查询渠道信息
     * @param channelId
     * @return
     */
    public Channel qChannel(String channelId);

    /**
     * Description:微信消息分发
     * @Version1.0 2016年10月10日 下午10:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     * @param param
     * @return
     */
    public Map<String, Object> msgType(Map<String, Object> param, HttpServletResponse response);

    /**
     * 微信现金红包
     * @param msgMap
     * @param param
     */
    public void sendRedPack(Map<String, Object> msgMap, Map<String, Object> param);

    /**
     * 生成微信二维码
     * @param createQrcodeImg
     */
    public void addQrcode(CreateQrcodeImg createQrcodeImg,String appId) throws Exception;

    /**
     * Description:记录生成的二维码
     * @param qrcodeImg
     */
    public void iQrcodeimg(QrcodeImg qrcodeImg);

    /**
     * 查询商户信息
     * @param appId
     * @return
     */
    public Merchant qMerchant(String appId);
}
