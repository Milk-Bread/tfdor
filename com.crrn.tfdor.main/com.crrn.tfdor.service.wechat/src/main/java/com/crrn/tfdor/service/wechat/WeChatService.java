package com.crrn.tfdor.service.wechat;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.wechat.QrcodeImg;

public interface WeChatService {

    /**
     * Description: access_token获取入库
     * @Version1.0 2016年10月8日 下午9:09:07 by chepeiqing (chepeiqing@icloud.com)
     * @param map
     */
    public void iAccessToken(Map<String, Object> map);

    /**
     * Description: 删除过期的access_token
     * @Version1.0 2016年10月8日 下午9:18:56 by chepeiqing (chepeiqing@icloud.com)
     */
    public void dAccessToken(String channelId);

    /**
     * Description:查询accessToken
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public Map<String, Object> qAccessToken(String channelId) throws ParseException;

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
    public Map<String, Object> msgType(Map<String, Object> param);
    /**
     * Description:记录生成的二维码
     * @param map
     */
    public void iQrcodeimg(Map<String, Object> map);
}
