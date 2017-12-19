package com.tfdor.enums;

/**
 * @author chepeiqing
 * @date 2017年12月12日 下午8:54:35
 * @description 微信消息类型
 * @modifyBy 修改人
 * @modifyDate 修改时间
 * @modifyNote 修改说明
 */
public enum MsgType {
    /**
     * 文本消息
     **/
    text,
    /****/
    event,
    /**
     * 图片消息
     **/
    image,
    /**
     * 语音消息
     **/
    voice,
    /**
     * 视频消息
     **/
    video,
    /**
     * 小视频消息
     **/
    shortvideo,
    /**
     * 地理位置消息
     **/
    location,
    /**
     * 链接消息
     **/
    link
}
