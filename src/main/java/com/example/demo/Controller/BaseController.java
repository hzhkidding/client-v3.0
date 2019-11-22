//定义ExceptionHandler解决未被controller层吸收的exception
package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//这种方式处理异常只能返回页面路径，无法返回一个Responsebody形式,所以也要加上一个ResponseBody；
    public Object handlerException(HttpServletRequest request, Exception ex) {
        //判断一下如果Ex不是BussinessException
        ex.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("errMsg", "当前网络状态不稳定，请刷新页面重试");
        return  "当前网络状态不稳定，请刷新页面重试";
    }
}