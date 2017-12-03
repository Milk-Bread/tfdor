package tfdor.utils.interceptor;

import tfdor.domain.manage.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录监听类-处理同一时间只允许账号，单地点登录
 *@author chepeiqing
 *@Mail chepeiqin@icloud.com
 *@Date 2016/11/24
 *@Time 上午12:04
 *@version V1.0.0
 */
public class LoginListenner implements HttpSessionAttributeListener {

    /**
     * 用于存放账号和session对应关系的map
     */
    private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
    private static Logger logger = LoggerFactory.getLogger(LoginListenner.class);
    /**
     * 向session中放入数据触发
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        if (name.equals("_USER")) {
            UserInfo user = (UserInfo) event.getValue();
            if (user != null) {
                if(map.get(user.getUserId()) != null) {
                    HttpSession session = map.get(user.getUserId());
                    session.setAttribute("PLAY_EACH_OTHER", "true");
                    session.removeAttribute(user.getUserId());
                }
                map.put(user.getUserId(), event.getSession());
            }
            logger.debug("当前在线人数：" + map.size());
        }
    }

    /**
     * 向session中移除数据触发
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String name = event.getName();
        if (name.equals("_USER")) {
            UserInfo user = (UserInfo) event.getValue();
            map.remove(user.getUserId());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
