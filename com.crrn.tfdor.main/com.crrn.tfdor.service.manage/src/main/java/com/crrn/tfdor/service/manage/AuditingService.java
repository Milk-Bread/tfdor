package com.crrn.tfdor.service.manage;

import com.crrn.tfdor.domain.wechat.QrcodeImg;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 16/10/18
 * @Time 上午10:56
 */
public interface AuditingService {
    /**
     *  Description:添加审核数据
     * @return
     */
    public void addAuditing(Map<String, Object> param);

    /**
     * 查询复合人员
     * @param param
     */
    public List<Map<String,Object>> queryAuditPerson(Map<String, Object> param);

    /**
     * 待复合记录查询
     * @param param
     * @return
     */
    public PageInfo<Map<String,Object>> auditingList(Map<String, Object> param);

    /**
     * 复合记录查询
     * @param param
     * @return
     */
    public PageInfo<Map<String,Object>> audiResultList(Map<String, Object> param);

    /**
     * 修改审核流水记录
     * @param param
     */
    public void modifyAuditing(Map<String, Object> param);
}
