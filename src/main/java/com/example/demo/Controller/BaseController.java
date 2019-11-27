//定义ExceptionHandler解决未被controller层吸收的exception
package com.example.demo.Controller;

import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import com.example.demo.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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

        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
            ModelAndView mv = new ModelAndView();
            //error是静态资源根目录下的error.ftl模版的视图名称
            mv.setViewName("myerror");
            if(businessException.getErrCode()==10003){
                mv.addObject("actionUrl","getDeviceResource");
            }else if (businessException.getErrCode()==10004){
                mv.addObject("actionUrl","getAppList");
            }
            mv.addObject("errMsg", businessException.getErrMsg());
            return mv;
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOW_ERROR.getErrMsg());
        }
        ex.printStackTrace();
        return CommonReturnType.create(responseData, "fail");
       /* Map<String, Object> responseData = new HashMap<>();
        responseData.put("errMsg", "当前网络状态不稳定，请刷新页面重试");
        return  "当前网络状态不稳定，请刷新页面重试";*/
    }
}