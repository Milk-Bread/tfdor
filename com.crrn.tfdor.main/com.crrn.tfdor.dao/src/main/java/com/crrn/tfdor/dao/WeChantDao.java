package com.crrn.tfdor.dao;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.wechat.QrcodeImg;

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
    public void dAccessToken();

    /**
     * Description:查询accessToken
     * @Version1.0 2016年10月8日 下午9:47:31 by chepeiqing (chepeiqing@icloud.com)
     * @return
     */
    public Map<String, Object> qAccessToken();

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
}
