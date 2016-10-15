package com.crrn.tfdor.domain.manage;

public class BaseCodeModel {
  /** 全局返回码 **/
  private String errcode;
  /** 说明 **/
  private String errmsg;

  public String getErrcode() {
    return errcode;
  }

  public void setErrcode(String errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
}
