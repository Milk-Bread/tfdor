package com.crrn.tfdor.service.manage;

import java.util.List;

import com.crrn.tfdor.domain.wechat.QrcodeImg;

public interface MarketingService {
	/**
     *  Description:查询生成的二维码
     * @return
     */
    public List<QrcodeImg> qQrcodeimg();
}
