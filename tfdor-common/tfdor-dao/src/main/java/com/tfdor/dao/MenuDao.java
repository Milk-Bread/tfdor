package com.tfdor.dao;

import com.tfdor.domain.manage.Menu;

import java.util.List;
import java.util.Map;

public interface MenuDao {

  /**
   * Description:加载菜单
   * @Version1.0 2016年8月1日 下午2:24:38 by chepeiqing (chepeiqing@icloud.com)
   * @return
   */
  public List<Menu> getMenu(Map<String, Object> map);
}
