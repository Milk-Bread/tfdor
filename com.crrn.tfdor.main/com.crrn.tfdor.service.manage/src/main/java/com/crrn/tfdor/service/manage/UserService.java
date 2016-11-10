package com.crrn.tfdor.service.manage;

import com.crrn.tfdor.domain.manage.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
  public Map<String, Object> loginCheck(String userId, String password);
  /**
   * Description:角色查询
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param param
   * @return
   */
  public List<Map<String, Object>> queryRole(Map<String, Object> param);

  /**
   * 新增角色
   * @param role
   * @return
   */
  public void addRole(Map<String, Object> role);

  /**
   *  查询用户列表
   */
  public List<UserInfo> queryUserInfo(UserInfo user);

  /**
   * 修改角色
   * @param role
   * @return
   */
  public void modifyRole(Map<String, Object> role);

  /**
   * 渠道查询
   * @param param
   * @return
   */
  public List<Map<String,Object>> queryChannel(Map<String, Object> param);

    /**
     *  添加用户
     */
    public void addUser(UserInfo userInfo);

  /**
   *  修改用户
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

  /*
   *  * 查询商户信息
   * @param map
   */
  public List<Map<String, Object>> queryBusiness(Map<String, Object> map);

  /**
   *  添加商户信息
   * @param map
   */
  public void addBusiness(Map<String, Object> map);
}
