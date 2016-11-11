package com.crrn.tfdor.service.manage.impl;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.wechat.RedPackBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.WeChantDao;
import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.crrn.tfdor.service.manage.MarketingService;

@Service
public class MarketingServiceImpl implements MarketingService {
	@Autowired
	public WeChantDao weChatDao;

	@Override
	public PageInfo<QrcodeImg> qQrcodeimg(Map<String, Object> param) {
		//分页开始
		PageHelper.startPage((Integer)param.get("pageNo"), (Integer) param.get("pageSize"));
		List<QrcodeImg> list = weChatDao.qQrcodeimg(param);
		//用PageInfo对结果进行包装
		PageInfo<QrcodeImg> page = new PageInfo<QrcodeImg>(list);
		return page;
	}

	/**
	 * 二维码生成配置表查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Map<String,Object>> qCreateQrcodeImg(Map<String, Object> map,Integer pageNo, Integer pageSize) {
		//分页开始
		PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> list = weChatDao.qCreateQrcodeImg(map);
		//用PageInfo对结果进行包装
		PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
		return page;
	}

	@Override
	public PageInfo<Map<String, Object>> queryRedPack(Map<String, Object> param) {
		//分页开始
		PageHelper.startPage((Integer)param.get("pageNo"), (Integer) param.get("pageSize"));
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(weChatDao.queryRedPack(param));
		return page;
	}

}
