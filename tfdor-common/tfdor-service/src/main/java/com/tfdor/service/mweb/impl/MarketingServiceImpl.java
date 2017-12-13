package com.tfdor.service.mweb.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfdor.domain.manage.Merchant;
import tfdor.domain.wechat.RedPackBean;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfdor.dao.WeChantDao;
import com.tfdor.service.mweb.MarketingService;

@Service
public class MarketingServiceImpl implements MarketingService {
    @Autowired
    public WeChantDao weChatDao;

    @Override
    public PageInfo<Map<String, Object>> qQrcodeimg(Map<String, Object> param) {
        //分页开始
        PageHelper.startPage((Integer) param.get("pageNo"), (Integer) param.get("pageSize"));
        List<Map<String, Object>> list = weChatDao.qQrcodeimg(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    /**
     * 二维码生成配置表查询
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> qCreateQrcodeImg(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        //分页开始
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = weChatDao.qCreateQrcodeImg(map);
        //用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public PageInfo<Map<String, Object>> queryRedPack(Map<String, Object> param) {
        //分页开始
        PageHelper.startPage((Integer) param.get("pageNo"), (Integer) param.get("pageSize"));
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(weChatDao.queryRedPack(param));
        return page;
    }

    /**
     * 修改红包参数
     *
     * @param map
     */
    @Override
    public void modifyRedPack(Map<String, Object> map) {
        weChatDao.modifyRedPack(map);
    }

    /**
     * 添加红包参数
     *
     * @param map
     */
    @Override
    public void addRedPack(Map<String, Object> map) {
        weChatDao.addRedPack(map);
    }


    /**
     * 查询商户是否添加过红包
     *
     * @param param
     */
    @Override
    public Integer queryRedPackByMchIdCount(Map<String, Object> param) {
        return weChatDao.queryRedPackByMchIdCount(param);
    }

    /**
     * 查询活动列表
     *
     * @param param
     * @return
     */
    @Override
    public List<RedPackBean> queryRedPackList(Map<String, Object> param) {
        return weChatDao.queryRedPackList(param);
    }

    /**
     * 根据appId查询商户信息
     *
     * @param appId
     * @return
     */
    @Override
    public Merchant qMerchantByAppId(String appId) {
        return weChatDao.qMerchant(appId);
    }

    /**
     * 删除二维码图片
     * @param createQiSeq
     * @return
     */
    @Override
    public void deleteQrCodeImg(String createQiSeq) {
        weChatDao.deleteQrCodeImg(createQiSeq);
    }

    /**
     * 删除二维码记录
     * @param createQiSeq
     * @return
     */
    @Override
    public void deleteQrCode(String createQiSeq) {
        weChatDao.deleteQrCode(createQiSeq);
    }
}
