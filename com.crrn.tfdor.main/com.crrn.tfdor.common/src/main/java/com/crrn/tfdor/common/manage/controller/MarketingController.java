package com.crrn.tfdor.common.manage.controller;

/**
 * Created by pengyuming on 16/10/12.
 */

import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.enumeration.wechat.AmountType;
import com.crrn.tfdor.service.manage.MarketingService;
import com.crrn.tfdor.utils.Dict;
import com.crrn.tfdor.utils.Util;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
        if (Dict.BUILT_IN_CHANNEL.equals(user.getChannel().getChannelId())) {
            param.put("channelId", request.getParameter("channelId"));
        } else {
            param.put("channelId", user.getChannel().getChannelId());
        }
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        param.put("beginDate", request.getParameter("beginDate"));
        param.put("endDate", request.getParameter("endDate"));
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
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
        map.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
        map.put("createQISeq", request.getParameter("createQISeq"));
        map.put("state", request.getParameter("state"));
        return marketingService.qQrcodeimg(map);
    }


    /**
     * 查询红包列表
     *
     * @return
     */
    @RequestMapping(value = "queryRedPack.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRedPack(HttpServletRequest request) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("_USER");
        Map<String, Object> param = new HashMap<String, Object>();
        if (Dict.BUILT_IN_CHANNEL.equals(user.getChannel().getChannelId())) {
            param.put("channelId", request.getParameter("channelId"));
        } else {
            param.put("channelId", user.getChannel().getChannelId());

        }
        param.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
        param.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
        return marketingService.queryRedPack(param);
    }

    /**
     * Description: 下载二维码
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "downloadsZip.do", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadsZip(HttpServletRequest request) throws IOException {
        String preservation = request.getParameter("preservation");
        String zipPath = Util.zipCompressorByAnt(preservation);
        File file = new File(zipPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    /**
     * 修改红包参数
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "modifyRedPack.do", method = RequestMethod.POST)
    @ResponseBody
    public void modifyRedPack(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("redPackType", request.getParameter("redPackType"));
        param.put("amountType", request.getParameter("amountType"));
        param.put("totalAmount", request.getParameter("totalAmount"));
        param.put("wishing", request.getParameter("wishing"));
        param.put("actName", request.getParameter("actName"));
        param.put("remark", request.getParameter("remark"));
        param.put("state", request.getParameter("state"));
        String totalNum = request.getParameter("totalNum");
        if(AmountType.FDAT.toString().equals(request.getParameter("amountType"))){
            totalNum = "1";
        } else if(totalNum == null || "".equals(totalNum)){
            totalNum = "1";
        }
        param.put("totalNum", totalNum);
        param.put("redPackSeq", request.getParameter("redPackSeq"));
        marketingService.modifyRedPack(param);
    }

    /**
     * 新增红包参数
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "addRedPack.do", method = RequestMethod.POST)
    @ResponseBody
    public void addRedPack(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mchId", request.getParameter("mchId"));
        param.put("redPackType", request.getParameter("redPackType"));
        param.put("amountType", request.getParameter("amountType"));
        param.put("totalAmount", request.getParameter("totalAmount"));
        param.put("wishing", request.getParameter("wishing"));
        param.put("actName", request.getParameter("actName"));
        param.put("remark", request.getParameter("remark"));
        param.put("state", request.getParameter("state"));
        String totalNum = request.getParameter("totalNum");
        if(totalNum == null || "".equals(totalNum)){
            totalNum = "1";
        }
        param.put("totalNum", totalNum);
        marketingService.addRedPack(param);
    }


    /**
     * Description: 查询商户是否添加过红包
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "queryRedPackByMchIdCount.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRedPackByMchIdCount(String mchId) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mchId",mchId);
        return marketingService.queryRedPackByMchIdCount(param);
    }

    /**
     * Description: 查询商户是否添加过红包
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "queryRedPackList.do", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRedPackList(String mchId) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mchId",mchId);
        return marketingService.queryRedPackList(param);
    }


        /**
     * Description: 根据AppId查询商户信息
     *
     * @return
     * @throws Exception
     * @Version1.0 2016年10月10日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "qMerchantByAppId.do", method = RequestMethod.POST)
    @ResponseBody
    public Object qMerchantByAppId(String appId) throws Exception{
        return marketingService.qMerchantByAppId(appId);
    }



}
