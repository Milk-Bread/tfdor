package com.tfdor.core.transport;


import java.util.Map;

/**
 * Description: 请求Transport公共父类
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年10月2日 上午10:53:43 by chepeiqing (chepeiqing@icloud.com)
 */
public abstract interface Transport {
    /**
     * Description: 发送http Get请求
     * @param sendParam
     * @return
     * @throws Exception
     */
    public abstract Object sendGet(Map<String, Object> sendParam) throws Exception;
    /**
     * Description: 发送http Post请求
     * @param sendParam
     * @return
     * @throws Exception
     */
    public abstract Object sendPost(Map<String, Object> sendParam) throws Exception;

    /**
     * Description: 上传素材
     * @param sendParam
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     * @throws Exception
     */
    public abstract Object addMaterial(Map<String, Object> sendParam, String type) throws Exception;


    /**
     * 微信支付
     * @param mchId
     * @param sendParam
     * @return
     * @throws Exception
     */
    public abstract Object submit(String mchId, Map<String, Object> sendParam) throws Exception;


}
