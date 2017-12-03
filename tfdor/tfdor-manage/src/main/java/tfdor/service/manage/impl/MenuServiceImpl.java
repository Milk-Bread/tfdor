package tfdor.service.manage.impl;

import tfdor.dao.MenuDao;
import tfdor.domain.manage.Menu;
import tfdor.service.manage.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Map<String, Object>> getMenu(Integer roleSeq) {
        List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId", "00000000");
        param.put("roleSeq", roleSeq);
        // 查询主菜单
        List<Menu> zuMenu = menuDao.getMenu(param);
        for (Menu menu : zuMenu) {
            Map<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("menuOne", menu.getMenuName());
            menuMap.put("menuIdOne", menu.getMenuId());
            param.put("parentId", menu.getMenuId());
            // 查子菜单
            List<Menu> ziMenu = menuDao.getMenu(param);
            List<Map<String, Object>> menu2List = new ArrayList<Map<String, Object>>();
            for (Menu menu2 : ziMenu) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", menu2.getMenuName());
                map.put("url", menu2.getTransId());
                map.put("id", menu2.getMenuId());
                menu2List.add(map);
            }
            menuMap.put("menuTwo", menu2List);
            menuList.add(menuMap);
        }
        return menuList;
    }

    @Override
    public List<Map<String, Object>> getAudiMenu(String channelId) {
        List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId", "00000000");
        param.put("channelId", channelId);
        // 查询主菜单
        List<Menu> zuMenu = menuDao.getMenu(param);
        for (Menu menu : zuMenu) {
            Map<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("menuOne", menu.getMenuName());
            menuMap.put("menuIdOne", menu.getMenuId());
            param.put("parentId", menu.getMenuId());
            // 查子菜单
            List<Menu> ziMenu = menuDao.getMenu(param);
            List<Map<String, Object>> menu2List = new ArrayList<Map<String, Object>>();
            for (Menu menu2 : ziMenu) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", menu2.getMenuName());
                map.put("url", menu2.getTransId());
                map.put("id", menu2.getMenuId());
                menu2List.add(map);
            }
            menuMap.put("menuTwo", menu2List);
            menuList.add(menuMap);
        }
        return menuList;
    }

}
