package com.crrn.tfdor.service.manage.impl;

import com.crrn.tfdor.dao.AuditingDao;
import com.crrn.tfdor.service.manage.AuditingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 16/10/18
 * @Time 上午10:57
 */
@Service
public class AuditingServiceImpl implements AuditingService {

    @Autowired
    public AuditingDao auditingDao;

    /**
     * Description:添加审核数据
     * @param param
     * @return
     */
    @Override
    public void addAuditing(Map<String, Object> param) {
        auditingDao.addAuditing(param);
    }

    /**
     * 查询复合人员
     *
     * @param param
     */
    @Override
    public List<Map<String,Object>> queryAuditPerson(Map<String, Object> param) {
        return auditingDao.queryAuditPerson(param);
    }

    /**
     * 复合记录查询
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> auditingList(Map<String, Object> param) {
        return auditingDao.auditingList(param);
    }


}
