package com.tfdor.domain.manage;

import com.tfdor.domain.BaseModel;
import com.tfdor.enums.LoginState;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * Description: 用户
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年8月1日 下午1:19:28 by chepeiqing (chepeiqing@icloud.com)
 */
public class UserInfo extends BaseModel implements Serializable {
  /**
   * 用户序号
   **/
  private Integer userSeq;
  /**
   * 用户id
   **/
  private String userId;
  /**
   * 用户名
   **/
  private String userName;
  /**
   * 密码
   **/
  private String password;
  /**
   * 性别
   **/
  private String sex;
  /**
   * 年龄
   **/
  private Integer age;
  /**
   * 证件类型
   **/
  private String idType;
  /**
   * 证件号码
   **/
  private String idNo;
  /**
   * 手机
   **/
  private String mobilePhone;
  /**
   * 电话
   **/
  private String phone;
  /**
   * 渠道ID
   **/
  private Channel channel;
  /**
   * 地址
   **/
  private String addr;
  /**
   * 角色ID
   **/
  private Integer roleSeq;
  /**
   * 创建时间
   **/
  private String createTime;
  /**
   * 密码错误次数
   */
  private Integer pasdErrorCount;

  /**
   * 登陆标志
   **/
  public LoginState loginState;
  /**
   * 登陆sessionId
   */
  private String sessionId;
  /**
   * 用户类型，1-管理员，2-操作员
   */
  private String customerType;
  /**
   * 商户信息列表
   */
  private List<Merchant> merchantList;
  /**
   * 是否需要重置密码
   */
  private String isReSetPwd;
  /**
   * 登陆时间
   */
  private Timestamp loginTime;

  public Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Timestamp loginTime) {
    this.loginTime = loginTime;
  }

  public Integer getPasdErrorCount() {
    return pasdErrorCount;
  }

  public void setPasdErrorCount(Integer pasdErrorCount) {
    this.pasdErrorCount = pasdErrorCount;
  }

  public String getIsReSetPwd() {
    return isReSetPwd;
  }

  public void setIsReSetPwd(String isReSetPwd) {
    this.isReSetPwd = isReSetPwd;
  }

  public List<Merchant> getMerchantList() {
    return merchantList;
  }

  public void setMerchantList(List<Merchant> merchantList) {
    this.merchantList = merchantList;
  }

  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public LoginState getLoginState() {
    return loginState;
  }

  public void setLoginState(LoginState loginState) {
    this.loginState = loginState;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
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

  @Override
  public String toString() {
    return "UserInfo{" +
        "userSeq=" + userSeq +
        ", userId='" + userId + '\'' +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", sex='" + sex + '\'' +
        ", age=" + age +
        ", idType='" + idType + '\'' +
        ", idNo='" + idNo + '\'' +
        ", mobilePhone='" + mobilePhone + '\'' +
        ", phone='" + phone + '\'' +
        ", channel=" + channel +
        ", addr='" + addr + '\'' +
        ", roleSeq=" + roleSeq +
        ", createTime='" + createTime + '\'' +
        ", pasdErrorCount='" + pasdErrorCount + '\'' +
        ", loginState=" + loginState +
        ", sessionId='" + sessionId + '\'' +
        ", customerType='" + customerType + '\'' +
        ", merchantList=" + merchantList +
        ", isReSetPwd='" + isReSetPwd + '\'' +
        ", loginTime=" + loginTime +
        '}';
  }
}
