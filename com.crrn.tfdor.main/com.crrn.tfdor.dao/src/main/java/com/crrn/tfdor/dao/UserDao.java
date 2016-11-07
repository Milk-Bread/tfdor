package com.crrn.tfdor.dao;

import com.crrn.tfdor.domain.manage.UserInfo;

import java.util.List;
import java.util.Map;


public interface UserDao {

  /**
   * Description:登陆校验
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param user
   * @return
   */
  public Map<String, Object> loginCheck(UserInfo user);

  /**
   * Description:登陆校验
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param param
   * @return
   */
  public List<Map<String, Object>> roleQuery(Map<String, Object> param);

  /**
   * 渠道查询
   * @param param
   * @return
   */
  public List<Map<String,Object>> queryChannel(Map<String, Object> param);

  /**
   * 新增角色
   * @param map
   * @return
     */
  public Integer addRole(Map<String, Object> map);

    /**
     * 查询用户列表
     */
    public List<UserInfo> queryUserInfo(UserInfo user);
  /**
   * 新增角色菜单关联
   * @param map
   */
  public void addRolemenurelate(Map<String, Object> map);

  /**
   * 删除角色菜单关联
   */
  public void deleteRolemenurelate(Map<String, Object> map);

  /**
   * 修改角色
   */
  public void modifyRole(Map<String, Object> map);

  /**
   *  添加用户
   * @param userInfo
   */
  public void addUser(UserInfo userInfo);

  /**
   *  修改用户
   * @param userInfo
   */
  public void modifyUser(UserInfo userInfo);

  /**
   *  添加渠道
   * @param map
   */
  public void addChannel(Map<String, Object> map);

  /**
   *  修改渠道信息
   * @param map
   */
  public void modifyChannel(Map<String, Object> map);

  /**
   * 删除渠道信息
   * @param map
   */
  public void deleteChannel(Map<String, Object> map);

  /**
   * 删除用户信息
   * @param map
   */
  public void deleteUser(Map<String, Object> map);
}
