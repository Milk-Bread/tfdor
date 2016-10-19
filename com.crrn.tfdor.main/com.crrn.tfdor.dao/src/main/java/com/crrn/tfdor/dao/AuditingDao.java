package com.crrn.tfdor.dao;

import java.util.List;
import java.util.Map;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 16/10/18
 * @Time 上午11:05
 */
public interface AuditingDao {

    /**
     * 新增复合数据
     * @param map
     */
    public void addAuditing(Map<String, Object> map);

    /**
     * 查询复合人员
     * @param param
     * @return
     */
    public List<Map<String,Object>> queryAuditPerson(Map<String, Object> param);

    /**
     * 复合记录查询
     * @param param
     * @return
     */
    public List<Map<String,Object>> auditingList(Map<String, Object> param);
}
