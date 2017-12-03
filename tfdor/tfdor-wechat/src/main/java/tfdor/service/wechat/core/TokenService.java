package tfdor.service.wechat.core;

import tfdor.domain.manage.CheckModel;
import tfdor.utils.CHECKMSG;
import tfdor.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class TokenService {


    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    /**
     * 微信开发者验证
     * @param wxToken
     * @param tokenModel
     * @return
     */
    @Transactional
    public String validate(String wxToken, CheckModel tokenModel) {
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce = tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if (signature != null && timestamp != null & nonce != null) {
            String[] str = {wxToken, timestamp + "", nonce + ""};
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = Util.encode("SHA1", bigStr).toLowerCase();
            // 确认请求来至微信
            if (digest.equals(signature)) {
                logger.debug("微信开发者校验成功，echostr：" + echostr);
                return echostr;
            }
        }
        throw new RuntimeException(CHECKMSG.WECHAT_DEVELOPER_VALIDATION_FAILED);
    }


}
