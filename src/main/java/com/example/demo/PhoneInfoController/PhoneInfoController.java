package com.example.demo.PhoneInfoController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 监听周边wifi信息
 *
 */
@Slf4j
@Controller
public class PhoneInfoController {
    private List list = new ArrayList();
    private  int flag = 1;
    public  String phoneInfo;
    private Map map;
    @ResponseBody
    @RequestMapping(value = "/phoneInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String phoneInfo(@RequestBody JSONObject phoneInfo) {
        // 直接将json信息打印出来
        //JSONObject resultArray = (JSONObject) JSONObject.parse(phoneInfo.toJSONString());
        //HTTP获取设备资源信息
        JSONObject jsonInfo   = new JSONObject();
        jsonInfo.put("k",5);
        jsonInfo.put("latitude",0);
        jsonInfo.put("longitude",0);
        jsonInfo.put("rssi",phoneInfo.get("result"));
        Date date = new Date();
        list.add(0,date);
        list.add(1,jsonInfo.toJSONString());
     //   this.phoneInfo = jsonInfo.toJSONString();
      //  log.info(this.phoneInfo);
        if(flag == 1) {
            log.info(jsonInfo.toJSONString());
            flag = 0;
        }
        return this.phoneInfo;
    }
    public String getPhoneInfo() throws BusinessException {
        Date date = new Date();
        Date datePhone = null;
        try {
            datePhone = (Date) list.get(0);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.LOCATION_ERROR, "未获取手机位置信息，请确保wifi处于开启状态，并开启位置授权");

        }
        long interval = (date.getTime() - datePhone.getTime())/1000;
        if(interval>50){
            throw new BusinessException(EmBusinessError.LOCATION_ERROR, "未获取手机位置信息，请确保wifi处于开启状态，并开启位置授权");
        }
        System.out.println("两个时间相差"+interval+"秒");
        return (String) list.get(1);

    }
}