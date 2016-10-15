package com.crrn.tfdor.domain.wechat;

/**
 * Created by chepeiqing on 16/10/10.
 */
public enum Event {
    /**
     * 用户未关注时，进行关注后的事件推送
     **/
    subscribe,
    /**
     * 取消关注
     */
    unsubscribe,
    /**
     * 用户已关注时的事件推送
     **/
    SCAN,
    /**
     * 上报地理位置事件
     **/
    LOCATION,
    /**
     * 点击菜单拉取消息时的事件推送
     **/
    CLICK,
    /**
     * 点击菜单跳转链接时的事件推送
     **/
    VIEW
}
