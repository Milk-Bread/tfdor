package com.crrn.tfdor.service.manage;

import java.util.List;

import com.crrn.tfdor.domain.manage.Menu;

public interface MenuService {
  List<Menu> getMenu(String parentId, Integer roleSeq);
}
