package com.crrn.tfdor.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.utils.CHECKMSG;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 16/10/12
 * @Time 下午10:16
 */
public class RoleInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String action = request.getRequestURL().toString();
//        request.getRequestDispatcher("").forward(request,response);
        if (action.indexOf("login.do") > 0) {
            return true;
        } else {
        	UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
            if (user != null) {
                if (user.isLogout()) {
                    request.getSession().removeAttribute("_USER");
                    throw new RuntimeException(CHECKMSG.PLEASE_LOG_IN_AGAIN);//F9C7277B6C72AAE295BB8FF31BB4566E ,F9C7277B6C72AAE295BB8FF31BB4566E
                }
            } else {
                request.getSession().removeAttribute("_USER");
                throw new RuntimeException(CHECKMSG.PLEASE_LOG_IN_AGAIN);
            }
        }
        logger.debug("TransAction:==>" + action);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println(request.getParts());
//        System.out.println();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
