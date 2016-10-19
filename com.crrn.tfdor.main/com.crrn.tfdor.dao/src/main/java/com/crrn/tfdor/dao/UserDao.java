package com.crrn.tfdor.dao;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.UserInfo;


public interface UserDao {

    /**
     * Description:登陆校验
     *
     * @param user
     * @return
     * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
     */
    public Map<String, Object> loginCheck(UserInfo user);

    /**
     * Description:登陆校验
     *
     * @param param
     * @return
     * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
     */
    public List<Map<String, Object>> roleQuery(Map<String, Object> param);

    /**
     * 新增角色
     *
     * @param map
     * @return
     */
    public Integer addRole(Map<String, Object> map);

    /**
     * 新增角色菜单关联
     *
     * @param map
     */
    public void addRolemenurelate(Map<String, Object> map);

    /**
     * 查询用户列表
     */
    public List<UserInfo> queryUserInfo(UserInfo user);
}
