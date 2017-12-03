package tfdor.utils;

/**
 * Description: 微信调用接口名称定义
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年10月8日 下午8:25:41 by chepeiqing (chepeiqing@icloud.com)
 */
public class WeChat {
    /**
     * 获取access_token号
     **/
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 创建二维码ticket（永久/临时）
     **/
    public static final String CREAT_QRCODE_IMAGE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    /**
     * 生成关注二维码
     **/
    public static final String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    /**
     * 分类型获取永久素材的列表 POST
     **/
    public static final String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
    /**
     * 获取永久素材的列表 GET
     **/
    public static final String GET_MATERIALCOUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";
    /**
     * 新增其他类型永久素材
     * type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * media form-data中媒体文件标识，有filename、filelength、content-type等信息
     */
    public static final String ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material";
    /**
     * 删除永久图片素材
     */
    public static final String DEL_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/del_material";
    /**
     * 新增图文素材
     */
    public static final String ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news";

    /**
     * 微信自定义菜单
     */
    public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create";

    /**
     * 微信自定义菜单
     */
    public static final String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete";
    /**
     * 查询微信菜单
     */
    public static final String GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get";

    /**
     * 获取用户信息
     */
    public static final String GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 微信支付地址
     */
    public static interface PAY {
        /**
         * 微信普通红包
         */
        public static final String SENDREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

        /**
         * 微信裂变红包
         */
        public static final String SENDGROUPREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";

    }



    public static interface JSAPI{
        /**
         * 微信普通红包
         */
        public static final String GETTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    }
}
