package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.DevicePrice;
import com.example.demo.Service.AppService;
import com.example.demo.Service.ResourcePriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
public class ResourcePriceController extends BaseController{

    @Autowired
    AppService appService;
    private String deviceId;
    private Double basePrice;
    @Autowired
    ResourcePriceService resourcePriceService;

  /*  @RequestMapping("/registerDevices/{deviceNo}")
    public String registerDevices(@PathVariable(name = "deviceNo") String deviceId, @CookieValue("userId") String userId, Model model) {
        //System.out.println(deviceNo);
        //  this.deviceId = deviceId;
        return "devicesRegister";
    }*/



    @RequestMapping("/sendCost")
    @ResponseBody
    public Double sendCost(@RequestBody String jsonObject) {

        JSONObject userDevicePrice = JSONObject.parseObject(jsonObject);
        this.deviceId = userDevicePrice.getString("deviceId");
        System.out.println("设备ID sendCost" + deviceId);
        // userDevicePrice.put("deviceId",this.deviceId);
        //userDevicePrice.put("userId",userId);
        Double basePrice = resourcePriceService.getBaseprice(userDevicePrice.getString("name"));

        this.basePrice = basePrice;

        return basePrice;
    }


    @RequestMapping("/sendExPrice")
    @ResponseBody
    public void sendExPrice(@RequestBody String jsonObject) throws JsonProcessingException {
        JSONObject exPriceJsonObj = JSONObject.parseObject(jsonObject);

        String expricejson = exPriceJsonObj.toJSONString();
        JSONObject deviceId = exPriceJsonObj.getJSONObject("deviceId");
        JSONObject finalJson = new JSONObject();
        JSONObject refResource = new JSONObject();
        refResource.put("kind","Edge");
        refResource.put("name",deviceId.getString("name"));
        JSONObject resourceSpec = new JSONObject();
        resourceSpec.put("probeEnabled",true);
        JSONObject labels = new JSONObject();
        labels.put("io.fusionapp.smarthome.coffeepot/price",exPriceJsonObj.getString("exPrice"));
        resourceSpec.put("labels",labels);
        finalJson.put("refResource",refResource);
        finalJson.put("resourceSpec",resourceSpec);
        resourcePriceService.sendExPrice(finalJson);
    }


    @PostMapping("/sendNeed")
    @ResponseBody
    public Double sendNeed(@RequestBody String jsonObject, Model model) {

        JSONObject sendNeed = JSONObject.parseObject(jsonObject);
        String appInstanceId = sendNeed.getString("appInstanceId");
        JSONObject getFinalPrice = new JSONObject();
        getFinalPrice.put("demand", sendNeed.getDouble("demand"));
        getFinalPrice.put("userId", 11);
        getFinalPrice.put("devices", appService.instanceIdToDeviceListArray.get(appInstanceId));
        log.info("获取价格,实例Id"+appInstanceId+"设备列表"+appService.instanceIdToDeviceListArray.get(appInstanceId));
        return resourcePriceService.getFinalPrice(getFinalPrice);
    }
}
