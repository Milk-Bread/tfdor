package com.tfdor.service.wechat;


import com.tfdor.domain.wechat.CheckModel;

public interface TokenService {
  public String validate(String wxToken, CheckModel tokenModel);
}
