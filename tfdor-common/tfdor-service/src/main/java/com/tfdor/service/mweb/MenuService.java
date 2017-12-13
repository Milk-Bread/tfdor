package com.tfdor.service.mweb;

import java.util.List;
import java.util.Map;

public interface MenuService {
  List<Map<String, Object>> getMenu(Integer roleSeq);
  List<Map<String, Object>> getAudiMenu(String channel);
}
