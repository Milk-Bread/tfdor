package com.tfdor.domain;

import java.io.Serializable;

/**
 * @author chepeiqing
 * @date 2017年12月12日 下午6:54:22
 * @description domain公共父类
 * @modifyBy 修改人
 * @modifyDate 修改时间
 * @modifyNote 修改说明
 */
public class BaseModel implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 全局返回码
   **/
  private String errcode;
  /**
   * 说明
   **/
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
