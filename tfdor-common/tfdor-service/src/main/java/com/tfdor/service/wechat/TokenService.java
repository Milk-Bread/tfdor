package com.tfdor.service.wechat;

import tfdor.domain.manage.CheckModel;

public interface TokenService {
  public String validate(String wxToken, CheckModel tokenModel);
}
