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
	public PageInfo<QrcodeImg> qQrcodeimg(Integer pageNo,Integer pageSize) {
		//分页开始
		PageHelper.startPage(pageNo, pageSize);
		List<QrcodeImg> list = weChatDao.qQrcodeimg();
		//用PageInfo对结果进行包装
		PageInfo<QrcodeImg> page = new PageInfo<QrcodeImg>(list);
		return page;
	}

	@Override
	public List<RedPackBean> queryRedPack(Map<String, Object> map) {
		return weChatDao.queryRedPack(map);
	}

}
