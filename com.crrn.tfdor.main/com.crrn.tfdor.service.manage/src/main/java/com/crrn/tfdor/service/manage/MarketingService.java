package com.crrn.tfdor.service.manage;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.wechat.CreateQrcodeImg;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.domain.wechat.RedPackBean;
import com.github.pagehelper.PageInfo;

public interface MarketingService {
	/**
     *  Description:查询生成的二维码
     * @return
     */
    public PageInfo<QrcodeImg> qQrcodeimg(Map<String, Object> param);

    /**
     * 二维码生成配置表查询
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String,Object>> qCreateQrcodeImg(Map<String, Object> map, Integer pageNo, Integer pageSize);


    /**
     *  查询红包列表
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> queryRedPack(Map<String, Object> map);

    /**
     *  修改红包参数
     * @param map
     */
    public void modifyRedPack(Map<String, Object> map);
}
