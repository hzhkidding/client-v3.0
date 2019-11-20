package com.example.demo.PhoneInfoController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 监听周边wifi信息
 *
 */
@Slf4j
@Controller
public class PhoneInfoController {
    private  int flag = 1;
    public  String phoneInfo;
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
        this.phoneInfo = jsonInfo.toJSONString();
      //  log.info(this.phoneInfo);
        if(flag == 1) {
            log.info(this.phoneInfo);
            flag = 0;
        }
        return this.phoneInfo;
    }
}