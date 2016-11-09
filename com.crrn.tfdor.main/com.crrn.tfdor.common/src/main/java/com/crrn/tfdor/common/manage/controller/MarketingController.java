package com.crrn.tfdor.common.manage.controller;

/**
 * Created by pengyuming on 16/10/12.
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.utils.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crrn.tfdor.service.manage.MarketingService;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 核心控制器
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年10月12日 下午22:14:05 by pengyuming
 */
@Controller
public class MarketingController {

    @Resource
    private MarketingService marketingService;


    /**
     * Description: 查询带参数微信二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "qCreateQrcodeImg.do", method = RequestMethod.POST)
    @ResponseBody
    public Object qCreateQrcodeImg(HttpServletRequest request) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        Map<String, Object> param = new HashMap<String, Object>();
        if (!Dict.BUILT_IN_CHANNEL.equals(user.getChannel().getChannelId())) {
            param.put("channelId", user.getChannel().getChannelId());
        }
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        return marketingService.qCreateQrcodeImg(param, pageNo, pageSize);
    }

    /**
     * Description: 查询带参数微信二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by pengyuming (pengym_27@163.com)
     */
    @RequestMapping(value = "getQrcodeImg.do", method = RequestMethod.POST)
    @ResponseBody
    public Object getQrcodeImage(HttpServletRequest request) throws Exception {
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        return marketingService.qQrcodeimg(pageNo, pageSize);
    }


    /**
     * 查询红包列表
     *
     * @return
     */
    @RequestMapping(value = "queryRedPack.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRedPack(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("channelId", request.getParameter("channelId"));
        return marketingService.queryRedPack(map);
    }

    /**
     * Description: 查询带参数微信二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "addQrcode.do", method = RequestMethod.POST)
    @ResponseBody
    public Object addQrcode(HttpServletRequest request){

        return null;
    }
}
