package com.tfdor.core.interceptor;

import com.tfdor.enums.LoginState;
import com.tfdor.tools.dicts.CheckMsg;
import com.tfdor.tools.dicts.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.tfdor.domain.manage.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
   * 如果返回false
   * 从当前拦截器往回执行所有拦截器的afterCompletion方法,再退出拦截器链.
   * 如果返回true
   * 执行下一个拦截器,直到所有拦截器都执行完毕.再运行被拦截的Controller.
   * 然后进入拦截器链,从最后一个拦截器往回运行所有拦截器的postHandle方法.
   * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getMethod().equalsIgnoreCase("GET")) {
      return true;
    }
    String action = request.getParameter("transName");
    logger.info("TransAction:==> [[" + action + "]]" + " IP: [[" + getIpAddress(request) + "]]");
    if (action.equals("login.do") || action.equals("resetLoginPasd.do") || "getPublicKey.do".equals(action)) {
      logger.info("==> [[ transaction id is " + action + " Do not do landing check ]]");
      return true;
    } else {
      UserInfo user = (UserInfo) request.getSession().getAttribute(Dict.SESSIONUSERID);
      if (user != null) {
        if (LoginState.O.equals(user.getLoginState())) {
          request.getSession().removeAttribute(Dict.SESSIONUSERID);
          request.getSession().invalidate();
          throw new RuntimeException(CheckMsg.PLEASE_LOG_IN_AGAIN);
        } else if (LoginState.L.equals(user.getLoginState())) {
          request.getSession().removeAttribute(Dict.SESSIONUSERID);
          request.getSession().invalidate();
          throw new RuntimeException(CheckMsg.ACCOUNT_IN_OTHER_PLACES_YOU_HAVE_TO_FORCE_OFF_THE_ASSEMBLY_LINE);
        }
      } else {
        request.getSession().removeAttribute(Dict.SESSIONUSERID);
        throw new RuntimeException(CheckMsg.PLEASE_LOG_IN_AGAIN);
      }
    }
    return auditing.auditing(request, response);
  }

  /**
   * 在Controller方法后返回视图前拦截
   *
   * @param request
   * @param response
   * @param handler
   * @param modelAndView
   * @throws Exception
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }

  /**
   * 在Controller方法后进行拦截
   * 当有拦截器抛出异常时,会从当前拦截器往回执行所有拦截器的afterCompletion方 法
   *
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

  /**
   * 获取请求IP地址
   *
   * @param request
   * @return
   */
  public static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
