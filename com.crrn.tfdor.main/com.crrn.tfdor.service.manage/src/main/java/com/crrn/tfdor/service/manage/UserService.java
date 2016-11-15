package com.crrn.tfdor.service.manage;

import com.crrn.tfdor.domain.manage.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 用户登陆
     *
     * @param userId
     * @param password
     * @return
     */
    public Map<String, Object> loginCheck(String userId, String password);

    /**
     * 重置密码
     *
     * @param userId
     * @param password
     * @return
     */
    public void resetPasd(String password, String userId);

    /**
     * 修改用户登陆次数
     *
     * @param map
     */
    public void modifyUserinfo(Map<String, Object> map);

    /**
     * Description:角色查询
     *
     * @param param
     * @return
     * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
     */
    public List<Map<String, Object>> queryRole(Map<String, Object> param);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    public void addRole(Map<String, Object> role);

    /**
     * 查询用户列表
     */
    public List<Map<String, Object>> queryUserInfo(Map<String, Object> map);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    public void modifyRole(Map<String, Object> role);

    /**
     * 渠道查询
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> queryChannel(Map<String, Object> param);

    /**
     * 添加用户
     */
    public void addUser(Map<String, Object> param);

    /**
     * 根据用户ID查询用户信息
     *
     * @param map
     * @return
     */
    public Map<String, Object> queryUserById(Map<String, Object> map);

    /**
     * 修改用户
     */
    public void modifyUser(Map<String, Object> map);

    /**
     * 添加渠道
     *
     * @param map
     */
    public void addChannel(Map<String, Object> map);

    /**
     * 修改渠道信息
     *
     * @param map
     */
    public void modifyChannel(Map<String, Object> map);

    /**
     * 删除渠道信息
     *
     * @param map
     */
    public void deleteChannel(Map<String, Object> map);

    /**
     * 查询商户信息
     *
     * @param map
     */
    public List<Map<String, Object>> queryMerchant(Map<String, Object> map);

    /**
     * 删除用户信息
     *
     * @param map
     */
    public void deleteUser(Map<String, Object> map);

    /*
     *  添加商户信息
     * @param map
     */
    public void addMerchant(Map<String, Object> map);

    /**
     * 修改商户
     *
     * @param map
     */
    public void modifyMerchant(Map<String, Object> map);

    /**
     * 重置密码
     * @param map
     */
    public void resetPwd(Map<String, Object> map);
}
