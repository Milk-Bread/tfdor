package com.crrn.tfdor.service.manage;

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
  public List<Map<String, Object>> roleQuery(Map<String, Object> param);

  /**
   * 新增角色
   * @param role
   * @return
   */
  public void addRole(Map<String, Object> role);
}
