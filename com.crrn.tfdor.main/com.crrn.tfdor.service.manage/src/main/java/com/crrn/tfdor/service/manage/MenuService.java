package com.crrn.tfdor.service.manage;

import java.util.List;
import java.util.Map;

import com.crrn.tfdor.domain.manage.Menu;

public interface MenuService {
  List<Map<String, Object>> getMenu(Integer roleSeq);
  List<Map<String, Object>> getAudiMenu(String channel);
}
