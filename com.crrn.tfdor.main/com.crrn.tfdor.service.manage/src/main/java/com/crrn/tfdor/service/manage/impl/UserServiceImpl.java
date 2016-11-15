package com.crrn.tfdor.service.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.UserDao;
import com.crrn.tfdor.domain.manage.UserInfo;
import com.crrn.tfdor.service.manage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 重置密码
     *
     * @param password
     * @param userId
     * @return
     */
    @Override
    public void resetPasd(String password, String userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",userId);
        param.put("password",password);
        userDao.resetPasd(param);
    }

    /**
     * 修改用户登陆次数
     *
     * @param map
     */
    @Override
    public void modifyUserinfo(Map<String, Object> map) {
        userDao.modifyUserinfo(map);
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
        roleMap.put("roleName", roleName);
        roleMap.put("roleSeq", "");
        roleMap.put("channelId", role.get("channelId"));
        userDao.addRole(roleMap);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq", roleMap.get("roleSeq"));
            map.put("menuId", str);
            userDao.addRolemenurelate(map);
        }
    }

    /**
     * 查询用户列表
     *
     * @param user
     */
    @Override
    public List<Map<String, Object>> queryUserInfo(Map<String, Object> user) {
        return userDao.queryUserInfo(user);
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
        roleMap.put("roleName", roleName);
        roleMap.put("roleSeq", role.get("roleSeq"));
        roleMap.put("channelId", role.get("channelId"));
        userDao.modifyRole(roleMap);
        userDao.deleteRolemenurelate(role);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq", roleMap.get("roleSeq"));
            map.put("menuId", str);
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

    /**
     * 添加用户
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void addUser(Map<String, Object> param) {
        userDao.addUser(param);

    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryUserById(Map<String, Object> map) {
        return userDao.queryUserById(map);
    }

    /**
     * 修改用户
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void modifyUser(Map<String, Object> map) {
        userDao.modifyUser(map);
    }

    /**
     * 添加渠道
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void addChannel(Map<String, Object> map) {
        userDao.addChannel(map);
    }

    /**
     * 修改渠道信息
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void modifyChannel(Map<String, Object> map) {
        userDao.modifyChannel(map);
    }


    /**
     * 删除渠道信息
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteChannel(Map<String, Object> map) {
        userDao.deleteChannel(map);
    }



    /**
     * 查询商户信息
     *
     * @param map
     */
    @Override
    public List<Map<String, Object>> queryMerchant(Map<String, Object> map) {
        String channelId = (String) map.get("channelId");
        Map<String, Object> bMap = new HashMap<>();
        if (!channelId.equals("tfdor")) {
            bMap.put("channelId", channelId);
        }
        return userDao.queryMerchant(bMap);
    }

    /**
     * 删除渠道信息
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void addMerchant(Map<String, Object> map) {
        userDao.addMerchant(map);
    }

    @Override
    public void deleteUser(Map<String, Object> map) {
        userDao.deleteUser(map);
    }

    /**
     * 修改商户
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void modifyMerchant(Map<String, Object> map) {
        userDao.modifyMerchant(map);
    }

    /**
     * 重置密码
     * @param map
     */
    @Override
    public void resetPwd(Map<String, Object> map) {
        userDao.resetPasd(map);
    }
}
