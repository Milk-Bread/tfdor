package com.crrn.tfdor.dao;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.Channel;
import com.crrn.tfdor.domain.wechat.CreateQrcodeImg;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.domain.wechat.RedPackBean;

/**
 * Description:微信相关表数据库操作
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年10月8日 下午9:04:51 by chepeiqing (chepeiqing@icloud.com)
 */
public interface WeChantDao {

    /**
     * Description: access_token获取入库
     * @Version1.0 2016年10月8日 下午9:09:07 by chepeiqing (chepeiqing@icloud.com)
     * @param map
     */
    public void iAccessToken(Map<String, Object> map);

    /**
     * Description: 删除过期的access_token
     * @Version1.0 2016年10月8日 下午9:18:56 by chepeiqing (chepeiqing@icloud.com)
     */
    public void dAccessToken(String channelId);

    /**
     * Description:查询accessToken
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public Map<String, Object> qAccessToken(String channelId);

    /**
     * 查询渠道信息
     * @param channelId
     * @return
     */
    public Channel qChannel(String channelId);

    /**
     * Description:记录生成的二维码
     * @param map
     */
    public void iQrcodeimg(Map<String, Object> map);

    /**
     *  Description:查询生成的二维码
     * @return
     */
    public List<QrcodeImg> qQrcodeimg();

    /**
     * 二维码生成配置表查询
     * @param map
     * @return
     */
    public List<Map<String,Object>> qCreateQrcodeImg(Map<String, Object> map);
    /**
     * 查询二维码使用情况
     * @param map
     * @return
     */
    public QrcodeImg qQrcodeimgByTicket(Map<String, Object> map);

    /**
     * 修改二维码状态
     * @param map
     */
    public void uQrcodeImg(Map<String, Object> map);

    /**
     *  查询红包列表
     * @param map
     * @return
     */
    public List<RedPackBean> queryRedPack(Map<String, Object> map);
}
