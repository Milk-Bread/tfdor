package com.crrn.tfdor.service.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.UserDao;
import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.service.manage.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> loginCheck(String userId, String password) {
    	UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setPassword(password);
        return userDao.loginCheck(user);
    }

    /**
     * Description:角色查询
     *
     * @param param
     * @return
     * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
     */
    @Override
    public List<Map<String, Object>> queryRole(Map<String, Object> param) {
        return userDao.roleQuery(param);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addRole(Map<String, Object> role) {
        String roleName = (String) role.get("roleName");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("roleName",roleName);
        roleMap.put("roleSeq","");
        roleMap.put("channelId",role.get("channelId"));
        userDao.addRole(roleMap);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq",roleMap.get("roleSeq"));
            map.put("menuId",str);
            userDao.addRolemenurelate(map);
        }
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void modifyRole(Map<String, Object> role) {
        String roleName = (String) role.get("roleName");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("roleName",roleName);
        roleMap.put("roleSeq",role.get("roleSeq"));
        roleMap.put("channelId",role.get("channelId"));
        userDao.modifyRole(roleMap);
        userDao.deleteRolemenurelate(role);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq",roleMap.get("roleSeq"));
            map.put("menuId",str);
            userDao.addRolemenurelate(map);
        }
    }

    /**
     * 渠道查询
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> queryChannel(Map<String, Object> param) {
        return userDao.queryChannel(param);
    }


}
