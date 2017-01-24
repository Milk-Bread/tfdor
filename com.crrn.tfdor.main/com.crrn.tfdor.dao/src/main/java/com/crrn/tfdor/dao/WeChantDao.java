package com.crrn.tfdor.dao;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.manage.Merchant;
import com.crrn.tfdor.domain.wechat.CreateQrcodeImg;
import com.crrn.tfdor.domain.wechat.CustomerInfo;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.domain.wechat.RedPackBean;

import java.util.List;
import java.util.Map;

/**
 * Description:微信相关表数据库操作
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年10月8日 下午9:04:51 by chepeiqing (chepeiqing@icloud.com)
 */
public interface WeChantDao {

    /**
     * Description: access_token获取入库
     *
     * @param map
     * @Version1.0 2016年10月8日 下午9:09:07 by chepeiqing (chepeiqing@icloud.com)
     */
    public void iAccessToken(Map<String, Object> map);

    /**
     * Description: 删除过期的access_token
     *
     * @Version1.0 2016年10月8日 下午9:18:56 by chepeiqing (chepeiqing@icloud.com)
     */
    public void dAccessToken(String mchId);

    /**
     * Description:查询accessToken
     *
     * @return
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     */
    public Map<String, Object> qAccessToken(String appId);

    /**
     * 查询渠道信息
     *
     * @param channelId
     * @return
     */
    public Channel qChannel(String channelId);

    /**
     * Description:记录生成的二维码
     *
     * @param qrcodeImg
     */
    public void iQrcodeimg(QrcodeImg qrcodeImg);

    /**
     * Description:查询生成的二维码
     *
     * @return
     */
    public List<Map<String, Object>> qQrcodeimg(Map<String, Object> param);

    /**
     * 二维码生成配置表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> qCreateQrcodeImg(Map<String, Object> map);

    /**
     * 查询二维码使用情况
     *
     * @param map
     * @return
     */
    public Map<String, Object> qQrcodeimgBysCeneStr(Map<String, Object> map);

    /**
     * 记录二维码生成记录
     * @param createQrcodeImg
     * @return
     */
    public void iCreateQrcodeImage(CreateQrcodeImg createQrcodeImg);

    /**
     * 修改二维码参数
     * @param createQrcodeImg
     * @return
     */
    public void modifyCreateQrcodeImage(CreateQrcodeImg createQrcodeImg);

    /**
     * 修改二维码状态
     *
     * @param map
     */
    public void uQrcodeImg(Map<String, Object> map);

    /**
     * 查询红包列表
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> queryRedPack(Map<String, Object> map);

    /**
     * 查询商户信息
     * @param appId
     * @return
     */
    public Merchant qMerchant(String appId);

    /**
     * 查询红包参数
     * @param param
     * @return
     */
    public RedPackBean queryRedPackByMchId(Map<String,Object> param);

    /**
     * 修改红包参数
     * @param map
     */
    public void modifyRedPack(Map<String, Object> map);

    /**
     * 添加红包参数
     * @param map
     */
    public void addRedPack(Map<String, Object> map);

    /**
     * 新增客户信息
     * @param cusm
     */
    public void iCustomerInfo(CustomerInfo cusm);

    /**
     * 查询客户信息
     * @param openId
     */
    public CustomerInfo qCustomerInfo(String openId);
    /**
     * 修改用户信息
     * @param cusm
     */
    public void uCustomerInfo(CustomerInfo cusm);

    /**
     * 查询商户是否添加过红包
     * @param param
     */
    public Integer queryRedPackByMchIdCount(Map<String, Object> param);

    /**
     * 查询活动列表／查询单个活动
     * @param param
     * @return
     */
    public List<RedPackBean> queryRedPackList(Map<String, Object> param);
}
