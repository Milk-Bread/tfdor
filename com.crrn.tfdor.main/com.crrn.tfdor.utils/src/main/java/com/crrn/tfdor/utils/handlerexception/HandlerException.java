package com.crrn.tfdor.utils.handlerexception;

import com.crrn.tfdor.utils.configurer.PropertyConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 统一异常处理
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年6月27日 下午4:23:14 by chepeiqing (chepeiqing@icloud.com)
 */
public class HandlerException implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(HandlerException.class);
    @Resource
    private PropertyConfigurer propertyConfigurer;


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> exceptionMap = new HashMap<String, Object>();
        String msg = propertyConfigurer.getMessage(ex.getMessage());
        if (msg == null || "".equals(msg)) {
            exceptionMap.put("_exceptionCode", "false");
            exceptionMap.put("_exceptionMsg", ex.getMessage() != null ? ex.getMessage().length() > 50 ? "系统内部错误" : ex.getMessage() : "系统内部错误");
        } else {
            if (ex instanceof ValidationRuntimeException) {
                ValidationRuntimeException ve = (ValidationRuntimeException) ex;
                Object[] obj = ve.getObj();
                if (obj != null)
                    msg = MessageFormat.format(msg, obj);
            }
            exceptionMap.put("_exceptionCode", ex.getMessage());
            exceptionMap.put("_exceptionMsg", msg);
        }
        logger.error(ex.getMessage(), ex);
        return new ModelAndView(new MappingJacksonJsonView(), exceptionMap);
    }
}
