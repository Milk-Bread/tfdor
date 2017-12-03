package tfdor.service.manage.impl;

import tfdor.dao.AuditingDao;
import tfdor.service.manage.AuditingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Map<String, Object>> auditingList(Map<String, Object> param) {
        //分页开始
        PageHelper.startPage((Integer) param.get("pageNo"), (Integer) param.get("pageSize"));
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(auditingDao.auditingList(param));
        return page;
    }

    /**
     * 复合记录查询
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> audiResultList(Map<String, Object> param) {
        //分页开始
        PageHelper.startPage((Integer) param.get("pageNo"), (Integer) param.get("pageSize"));
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(auditingDao.audiResultList(param));
        return page;
    }

    /**
     * 修改审核流水记录
     *
     * @param param
     */
    @Override
    public void modifyAuditing(Map<String, Object> param) {
        auditingDao.modifyAuditing(param);
    }


}
