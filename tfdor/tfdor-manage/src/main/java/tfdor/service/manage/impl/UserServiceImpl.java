package tfdor.service.manage.impl;

import tfdor.dao.UserDao;
import tfdor.domain.manage.UserInfo;
import tfdor.service.manage.UserService;
import tfdor.utils.CHECKMSG;
import tfdor.utils.EncodeUtil;
import tfdor.utils.handlerexception.ValidationRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> loginCheck(String userId, String password) {
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        Map<String, Object> userMap = userDao.loginCheck(user);
        String passwordaes = EncodeUtil.aesEncrypt(userId + password);
        if (userMap == null) {
            throw new RuntimeException(CHECKMSG.USER_DOES_NOT_EXIST);
        } else {
            Integer count = (Integer) userMap.get("pasdErrorCount");
            if (count > 4) {//密码输错5次
                Timestamp updateTime = Timestamp.valueOf((String)userMap.get("lastLoginTime")) ;
                Timestamp time = new Timestamp(System.currentTimeMillis());
                if (time.getTime() - updateTime.getTime() >= 24 * 60 * 60 * 1000) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("count", 0);
                    map.put("userSeq", userMap.get("userSeq"));
                    userDao.updateLoginErrorCount(map);
                } else {
                    throw new ValidationRuntimeException(CHECKMSG.USER_HAS_BEEN_LOCKED);
                }
            }
            if (!passwordaes.equals(userMap.get("password").toString())) {//密码错误
                Map<String, Object> map = new HashMap<>();
                map.put("count", ++count);
                map.put("userSeq", userMap.get("userSeq"));
                userDao.updateLoginErrorCount(map);
                throw new ValidationRuntimeException(CHECKMSG.PASSWORD_ERROR, new Object[]{5 - count});
            }
        }
        userMap.remove("password");
        return userMap;
    }

    /**
     * 重置密码
     *
     * @param map
     * @return
     */
    @Override
    public void resetPasd(Map<String, Object> map) {
        userDao.resetPasd(map);
    }

    /**
     * 修改用户登陆次数
     *
     * @param map
     */
    @Override
    public void modifyUserinfo(Map<String, Object> map) {
        userDao.modifyUserinfo(map);
    }

    /**
     * Description:角色查询
     *
     * @param param
     * @return
     * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
     */
    @Override
    public List<Map<String, Object>> queryRole(Map<String, Object> param) {
        String channelId = (String) param.get("channelId");
        String flag = (String) param.get("flag");
        Map<String, Object> bMap = new HashMap<>();
        bMap.put("roleName", param.get("roleName"));
        if (flag != null) {
            bMap.put("channelId", channelId);
        } else {
            if (!"tfdor".equals(channelId)) {
                bMap.put("channelId", channelId);
            }
        }
        return userDao.roleQuery(bMap);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addRole(Map<String, Object> role) {
        String roleName = (String) role.get("roleName");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("roleName", roleName);
        roleMap.put("roleSeq", "");
        roleMap.put("channelId", role.get("channelId"));
        userDao.addRole(roleMap);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq", roleMap.get("roleSeq"));
            map.put("menuId", str);
            userDao.addRolemenurelate(map);
        }
    }

    /**
     * 查询用户列表
     *
     * @param user
     */
    @Override
    public List<Map<String, Object>> queryUserInfo(Map<String, Object> user) {
        String channelId = (String) user.get("channelId");
        Map<String, Object> bMap = new HashMap<>();
        bMap.put("userName", user.get("userName"));
        if (!channelId.equals("tfdor")) {
            bMap.put("channelId", channelId);
        }
        return userDao.queryUserInfo(bMap);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void modifyRole(Map<String, Object> role) {
        String roleName = (String) role.get("roleName");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("roleName", roleName);
        roleMap.put("roleSeq", role.get("roleSeq"));
        roleMap.put("channelId", role.get("channelId"));
        userDao.modifyRole(roleMap);
        userDao.deleteRolemenurelate(role);
        String menuStr = (String) role.get("roleArr");
        String[] menuArr = menuStr.split(",");
        for (String str : menuArr) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleSeq", roleMap.get("roleSeq"));
            map.put("menuId", str);
            userDao.addRolemenurelate(map);
        }
    }

    /**
     * 渠道查询
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> queryChannel(Map<String, Object> param) {
        String channelId = (String) param.get("channelId");
        Map<String, Object> bMap = new HashMap<>();
        bMap.put("channelName", param.get("channelName"));
        if (!"tfdor".equals(channelId)) {
            bMap.put("channelId", channelId);
        }
        return userDao.queryChannel(bMap);
    }

    /**
     * 添加用户
     *
     * @param param
     * @return
     */
    @Override
    public void addUser(Map<String, Object> param) {
        userDao.addUser(param);

    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryUserById(Map<String, Object> map) {
        return userDao.queryUserById(map);
    }

    /**
     * 修改用户
     *
     * @param map
     */
    @Override
    public void modifyUser(Map<String, Object> map) {
        userDao.modifyUser(map);
    }

    /**
     * 添加渠道
     *
     * @param map
     */
    @Override
    public void addChannel(Map<String, Object> map) {
        userDao.addChannel(map);
    }

    /**
     * 修改渠道信息
     *
     * @param map
     */
    @Override
    public void modifyChannel(Map<String, Object> map) {
        userDao.modifyChannel(map);
    }

    /**
     * 删除渠道信息
     *
     * @param map
     */
    @Override
    public void deleteChannel(Map<String, Object> map) {
        userDao.deleteChannel(map);
    }

    /**
     * 查询商户信息
     *
     * @param map
     */
    @Override
    public List<Map<String, Object>> queryMerchant(Map<String, Object> map) {
        String channelId = (String) map.get("channelId");
        Map<String, Object> bMap = new HashMap<>();
        bMap.put("merchantName", map.get("merchantName"));
        if (!channelId.equals("tfdor")) {
            bMap.put("channelId", channelId);
        }
        return userDao.queryMerchant(bMap);
    }

    /**
     * 新增渠道信息
     *
     * @param map
     */
    @Override
    public void addMerchant(Map<String, Object> map) {
        userDao.addMerchant(map);
    }

    /**
     * 删除用户信息
     *
     * @param map
     */
    @Override
    public void deleteUser(Map<String, Object> map) {
        userDao.deleteUser(map);
    }

    /**
     * 修改商户
     *
     * @param map
     */
    @Override
    public void modifyMerchant(Map<String, Object> map) {
        userDao.modifyMerchant(map);
    }

    /**
     * 删除角色信息
     *
     * @param map
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteRole(Map<String, Object> map) {
        userDao.deleteRole(map);
        userDao.deleteRolemenurelate(map);
    }

    /**
     * 查询角色用户
     *
     * @param map
     */
    @Override
    public void queryRoleUserInfo(Map<String, Object> map) {
        Integer count = userDao.queryRoleUserInfo(map);
        if (count > 0 ){
            throw new RuntimeException(CHECKMSG.DELETE_ROLE_ERROR);
        }
    }

    /**
     * 查询角色用户信息
     *
     * @param map
     */
    @Override
    public void queryAddUserById(Map<String, Object> map) {
        Integer count = userDao.queryAddUserById(map);
        if(count > 0){
            throw new ValidationRuntimeException(CHECKMSG.LANDING_ID_ALREADY_EXISTS);
        }
    }

}
