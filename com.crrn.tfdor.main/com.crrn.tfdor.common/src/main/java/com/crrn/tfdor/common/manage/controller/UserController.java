package com.crrn.tfdor.common.manage.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.manage.Menu;
import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.service.manage.MenuService;
import com.crrn.tfdor.service.manage.UserService;
import com.crrn.tfdor.utils.BeanUtils;
import com.crrn.tfdor.utils.CHECKMSG;
import com.crrn.tfdor.utils.EncodeUtil;

/**
 * Description: 核心控制器
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年6月27日 下午3:27:05 by chepeiqing (chepeiqing@icloud.com)
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/")
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    /**
     * Description: 用户登陆
     *
     * @param request
     * @param userId
     * @param password
     * @return
     * @Version1.0 2016年8月1日 下午3:50:11 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, String userId, String password) {
        String passwordaes = EncodeUtil.aesEncrypt(password);
        Map<String, Object> userMap = userService.loginCheck(userId, passwordaes);
        if (userMap == null) {
            throw new RuntimeException(CHECKMSG.USER_DOES_NOT_EXIST);
        }
        UserInfo user = BeanUtils.map2Bean(userMap, UserInfo.class);
        Channel channel = BeanUtils.map2Bean(userMap, Channel.class);
        user.setChannel(channel);
        // 创建session
        request.getSession(true);
        user.setLogout(false);
        // 将user对象存入session
        user.setLoginTime(new Timestamp(System.currentTimeMillis()));
        request.getSession().setAttribute("_USER", user);
        return user;
    }

    /**
     * Description: 用户登出
     *
     * @return
     * @Version1.0 2016年8月1日 下午3:50:11 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public void logout(HttpServletRequest request) {
    	UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        user.setLogout(true);
        //清除Session的所有信息
        request.getSession().invalidate();
    }


    /**
     * Description: 根据用户角色加载菜单
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "lodeMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public Object lodeMenu(HttpServletRequest request) {
    	UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        String roleSeqStr = request.getParameter("roleSeq");
        Integer roleSeq = null;
        if(roleSeqStr == null || "".equals(roleSeqStr)){
            roleSeq = user.getRoleSeq();
        }else {
            roleSeq = Integer.valueOf(roleSeqStr);
        }
        List<Map<String, Object>> _menuList = (List<Map<String, Object>>) request.getSession().getAttribute("_menuList"+roleSeq);
        if (_menuList == null) {
            String parentId = "00000000";
            List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
            // 查询主菜单
            List<Menu> zuMenu = menuService.getMenu(parentId, roleSeq);
            for (Menu menu : zuMenu) {
                Map<String, Object> menuMap = new HashMap<String, Object>();
                menuMap.put("menuOne", menu.getMenuName());
                menuMap.put("menuIdOne", menu.getMenuId());
                parentId = menu.getMenuId();
                // 查子菜单
                List<Menu> ziMenu = menuService.getMenu(parentId, roleSeq);
                List<Map<String, Object>> menu2List = new ArrayList<Map<String, Object>>();
                for (Menu menu2 : ziMenu) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", menu2.getMenuName());
                    map.put("url", menu2.getTransId());
                    map.put("id", menu2.getMenuId());
                    menu2List.add(map);
                }
                menuMap.put("menuTwo", menu2List);
                menuList.add(menuMap);
            }
            request.getSession().setAttribute("_menuList"+roleSeq, menuList);
            return menuList;
        } else {
            return _menuList;
        }
    }

    /**
     * Description: 新增角色
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addRole.do", method = RequestMethod.POST)
    @ResponseBody
    public void addRole(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleName", request.getParameter("roleName"));
        map.put("roleArr", request.getParameter("roleArr"));
        UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        map.put("channelId", user.getChannel().getChannelId());
        userService.addRole(map);
    }

    /**
     * Description: 根据用户角色和渠道加载菜单
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "queryRole.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRole(HttpServletRequest request) {
        String roleName = (String) request.getParameter("roleName");
        UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        String channel = user.getChannel().getChannelId();
        Map<String, Object> param = new HashMap<>();
        param.put("roleName",roleName);
        param.put("channelId",channel);
        List<Map<String, Object>> roleList = userService.queryRole(param);
        String sessionId = request.getRequestedSessionId();
        return roleList;
    }


    /**
     * Description: 修改角色
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "modifyRole.do", method = RequestMethod.POST)
    @ResponseBody
    public void modifyRole(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("roleName", request.getParameter("roleName"));
        map.put("roleSeq", request.getParameter("roleSeq"));
        map.put("roleArr", request.getParameter("roleArr"));
        map.put("channelId", request.getParameter("channelId"));
        userService.modifyRole(map);
        //更新role菜单缓存
        request.getSession().removeAttribute("_menuList"+request.getParameter("roleSeq"));
    }

    /**
     * Description: 查询渠道
     * @param request
     * @return
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "queryChannel.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryChannel(HttpServletRequest request){
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("channelId",request.getParameter("channelId"));
        return userService.queryChannel(param);
    }

}
