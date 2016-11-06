package com.crrn.tfdor.service.manage;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.domain.wechat.RedPackBean;
import com.github.pagehelper.PageInfo;

public interface MarketingService {
	/**
     *  Description:查询生成的二维码
     * @return
     */
    public PageInfo<QrcodeImg> qQrcodeimg(Integer pageNo,Integer pageSize);

    /**
     *  查询红包列表
     * @param map
     * @return
     */
    public List<RedPackBean> queryRedPack(Map<String, Object> map);
}
