package com.crrn.tfdor.service.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crrn.tfdor.dao.MenuDao;
import com.crrn.tfdor.domain.manage.Menu;
import com.crrn.tfdor.service.manage.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuDao menuDao;

  @Override
  public List<Menu> getMenu(String parentId, Integer roleSeq) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("parentId", parentId);
    map.put("roleSeq", roleSeq);
    return menuDao.getMenu(map);
  }

}
