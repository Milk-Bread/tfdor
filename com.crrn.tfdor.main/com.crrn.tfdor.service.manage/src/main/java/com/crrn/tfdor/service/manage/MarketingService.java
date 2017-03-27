package com.crrn.tfdor.service.manage;

import com.crrn.tfdor.domain.manage.Merchant;
import com.crrn.tfdor.domain.wechat.RedPackBean;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface MarketingService {
    /**
     * Description:查询生成的二维码
     *
     * @return
     */
    public PageInfo<Map<String, Object>> qQrcodeimg(Map<String, Object> param);

    /**
     * 二维码生成配置表查询
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> qCreateQrcodeImg(Map<String, Object> map, Integer pageNo, Integer pageSize);


    /**
     * 查询红包列表
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> queryRedPack(Map<String, Object> map);

    /**
     * 修改红包参数
     *
     * @param map
     */
    public void modifyRedPack(Map<String, Object> map);

    /**
     * 添加红包参数
     *
     * @param map
     */
    public void addRedPack(Map<String, Object> map);

    /**
     * 查询商户是否添加过红包
     * @param param
     */
    public Integer queryRedPackByMchIdCount(Map<String, Object> param);

    /**
     * 查询活动列表
     * @param param
     * @return
     */
    public List<RedPackBean> queryRedPackList(Map<String, Object> param);

    /**
     * 根据appId查询商户信息
     * @param appId
     * @return
     */
    public Merchant qMerchantByAppId(String appId);
}
