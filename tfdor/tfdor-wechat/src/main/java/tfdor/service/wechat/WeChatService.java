package tfdor.service.wechat;

import tfdor.domain.manage.Channel;
import tfdor.domain.manage.Merchant;
import tfdor.domain.wechat.CreateQrcodeImg;
import tfdor.domain.wechat.QrcodeImg;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface WeChatService {


    /**
     * Description:查询accessToken
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public String getAccessToken(String appId) throws Exception;

    /**
     * Description:查询jsapiTicket
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public String getJsapiTicket(String appId) throws Exception;

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
    public Map<String, Object> msgType(Map<String, Object> param, HttpServletResponse response,Merchant merchant) throws Exception;

    /**
     * 生成微信二维码
     * @param createQrcodeImg
     */
    public void addQrcode(CreateQrcodeImg createQrcodeImg,String appId) throws Exception;

    /**
     * 生成修改二维码参数
     * @param createQrcodeImg
     */
    public void modifyCreateQrcodeImage(CreateQrcodeImg createQrcodeImg);


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
