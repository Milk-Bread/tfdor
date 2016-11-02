package com.crrn.tfdor.common.manage.controller;

/**
 * Created by pengyuming on 16/10/12.
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
     * @Version1.0 2016年10月10日 下午4:37:49 by pengyuming (pengym_27@163.com)
     */
    @RequestMapping(value = "getQrcodeImg.do", method = RequestMethod.POST)
    @ResponseBody
    public Object getQrcodeImage(HttpServletRequest request) throws Exception {
        return marketingService.qQrcodeimg();
    }


    /**
     *  查询红包列表
     * @return
     */
    @RequestMapping(value = "queryRedPack.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRedPack(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("channelId", request.getParameter("channelId"));
        return marketingService.queryRedPack(map);
    }
}
