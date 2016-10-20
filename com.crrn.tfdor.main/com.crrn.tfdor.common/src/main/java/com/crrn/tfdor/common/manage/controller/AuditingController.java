package com.crrn.tfdor.common.manage.controller;

import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.service.manage.AuditingService;
import com.crrn.tfdor.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 16/10/18
 * @Time 上午10:52
 */
@Controller
public class AuditingController {

    @Resource
    private AuditingService auditingService;

    /**
     * Description: 用户登陆
     *
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:50:11 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addAuditing.do", method = RequestMethod.POST)
    @ResponseBody
    public void addAuditing(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute("_USER");
        Map<String, Object> auditingData = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String paraValue = request.getParameter(paraName);
            auditingData.put(paraName, paraValue);
        }
        map.put("auditingData", Util.mapToJson(auditingData).toString());//待复合参数
        map.put("promoterSeq", userinfo.getUserSeq());
        map.put("promoter", userinfo.getUserName());
        map.put("auditPersonSeq", Integer.valueOf(request.getParameter("auditPersonSeq")));
        map.put("auditPerson", request.getParameter("auditPerson"));//指定审核人
        String auditing = (String) request.getSession().getAttribute("auditig" + userinfo.getUserSeq());
        String[] str = auditing.split(",");
        map.put("auditingTrans", str[0]);
        map.put("auditingName", str[1]);
        auditingService.addAuditing(map);
    }

    /**
     * 查询可以复合的人
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryAuditPerson.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryAuditPerson(HttpServletRequest request, HttpServletResponse response){
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute("_USER");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId",userinfo.getChannel().channelId);
        map.put("userSeq",userinfo.getUserSeq());
        return auditingService.queryAuditPerson(map);
    }

    /**
     * 查询可以复合的人
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "auditingList.do", method = RequestMethod.POST)
    @ResponseBody
    public Object auditingList(HttpServletRequest request, HttpServletResponse response){
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute("_USER");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("auditPersonSeq", userinfo.getUserSeq());
        map.put("state", "I");
        return auditingService.auditingList(map);
    }

     /**
     * 审核通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "audiAgree.do", method = RequestMethod.POST)
    @ResponseBody
    public void audiAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("auditingTrans");
        request.getRequestDispatcher(action).forward(request,response);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("state", "S");
        param.put("state", request.getParameter("auditingSeq"));
        auditingService.modifyAuditing(param);
    }



}
