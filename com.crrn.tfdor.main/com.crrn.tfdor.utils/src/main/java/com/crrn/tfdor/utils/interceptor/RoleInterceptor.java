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
    private AuditingInterceptor auditing;
    /**
     * 在Controller方法前进行拦截
     *     如果返回false
     *          从当前拦截器往回执行所有拦截器的afterCompletion方法,再退出拦截器链.
     *     如果返回true
     *          执行下一个拦截器,直到所有拦截器都执行完毕.再运行被拦截的Controller.
     *          然后进入拦截器链,从最后一个拦截器往回运行所有拦截器的postHandle方法.
     *          接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String action = request.getParameter("actionName");
        if (action.equals("login.do")) {
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
        return auditing.auditing(request, response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在Controller方法后进行拦截
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有拦截器的afterCompletion方 法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public void setAuditing(AuditingInterceptor auditing) {
        this.auditing = auditing;
    }
}
