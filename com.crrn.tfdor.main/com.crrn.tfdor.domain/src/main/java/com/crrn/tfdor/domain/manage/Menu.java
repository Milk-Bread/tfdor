package com.crrn.tfdor.domain.manage;

import java.sql.Timestamp;


/**
 * Description: 菜单
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年8月1日 下午1:19:02 by chepeiqing (chepeiqing@icloud.com)
 */
public class Menu extends BaseCodeModel {
  /** 菜单ID **/
  private String menuId;
  /** 菜单名称 **/
  private String menuName;
  /** 父级菜单ID **/
  private String parentId;
  /** 排序 **/
  private Integer orderId;
  /** 交易ID **/
  private String transId;
  /** 创建时间 **/
  private Timestamp createTime;

  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getTransId() {
    return transId;
  }

  public void setTransId(String transId) {
    this.transId = transId;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

}
