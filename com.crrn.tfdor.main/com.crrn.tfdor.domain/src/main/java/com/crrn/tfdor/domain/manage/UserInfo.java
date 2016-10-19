package com.crrn.tfdor.domain.manage;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Description: 用户
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年8月1日 下午1:19:28 by chepeiqing (chepeiqing@icloud.com)
 */
public class UserInfo extends BaseCodeModel implements Serializable {
  /** 用户序号 **/
  private Integer userSeq;
  /** 用户id **/
  private String userId;
  /** 用户名 **/
  private String userName;
  /** 密码 **/
  private String password;
  /** 性别 **/
  private String sex;
  /** 年龄 **/
  private Integer age;
  /** 证件类型 **/
  private String idType;
  /** 证件号码 **/
  private String idNo;
  /** 手机 **/
  private String mobilePhone;
  /** 电话 **/
  private String phone;
  /** 渠道ID **/
  private Channel channel;
  /** 地址 **/
  private String addr;
  /** 角色ID **/
  private Integer roleSeq;
  /** 创建时间 **/
  private Timestamp creteTime;
  /** 登陆标志 **/
  public boolean logout;
  private String sessionId;
  private Timestamp loginTime;

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Timestamp loginTime) {
    this.loginTime = loginTime;
  }

  public boolean isLogout() {
    return logout;
  }

  public void setLogout(boolean logout) {
    this.logout = logout;
  }

  public Timestamp getCreteTime() {
    return creteTime;
  }

  public void setCreteTime(Timestamp creteTime) {
    this.creteTime = creteTime;
  }

  public Integer getRoleSeq() {
    return roleSeq;
  }

  public void setRoleSeq(Integer roleSeq) {
    this.roleSeq = roleSeq;
  }

  public Integer getUserSeq() {
    return userSeq;
  }

  public void setUserSeq(Integer userSeq) {
    this.userSeq = userSeq;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Channel getChannel() {
    return channel;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
}
