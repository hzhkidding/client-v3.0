package com.example.demo.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Config.RestTemplateConfig;
import com.example.demo.Controller.AppController;
import com.example.demo.PhoneInfoController.PhoneInfoController;
import com.example.demo.Entity.Device;
import com.example.demo.Util.HttpInvoke;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.example.demo.Util.Constans.*;

@Slf4j
@Service
public class
DeviceResourceService {
    public String testJson = "{\n" +
            " \"labelSelector\": [\n" +
            "  { \"key\": \"io.fusionapp/kind\", \"op\": \"Eq\", \"value\": \"smarthome\" } \n" +
            " ]\n" +
            "}";
    @Autowired
    AppController appController;

    @Autowired
    PhoneInfoController phoneInfoController;

    @Autowired
    HttpInvoke httpInvoke;

    public List<Device> getDeviceResource() throws InterruptedException, BusinessException {
        //从phoneController获取手机相关信息//json格式数据

        Thread.sleep(4000);
        if(phoneInfoController.getPhoneInfo()==null){
            throw new BusinessException(EmBusinessError.LOCATION_ERROR, "未获取手机位置信息，请确保wifi处于开启状态，并开启位置授权");
        }
        /**
         * 传递wifi信号强度
         */
      //  log.info(phoneInfo);
        Map map = getLocationInfo(phoneInfoController.getPhoneInfo());
        Object room = map.get("room");
        /*JSONObject labelXY = (JSONObject) JSONObject.parse(httpInvoke.postInvoke(phoneInfo,DEVICE_LABEL_URL));
        Double x = Double.valueOf(labelXY.getString("x"));
        Double y = Double.valueOf(labelXY.getString("y"));
        String room = labelXY.getString("room");
        log.info("x轴:"+x+"y轴"+y);
        appController.X =  x;
        appController.Y = y;
      //  log.info("获取"+labelXY);
        String id = labelXY.getString("id");*/
        JSONObject selectorJson = new JSONObject();
        JSONArray labelSelector = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("key", "io.fusionapp/pos");
        json.put("op", "Eq");
        if(room == null){
            room = "110";
        }
        json.put("value", room);
        labelSelector.add(json);
        selectorJson.put("labelSelector", labelSelector);
        String deviceResourceInfo = httpInvoke.exchange(selectorJson.toJSONString(), DEVICE_RESOURCE_URL);
        JSONArray deviceResourceJsonArray = JSONObject.parseArray(deviceResourceInfo);
        List<Device> devicesList = new ArrayList<>();
        /**f
         * 设置设备List的属性，返回给controller
         */
        for (int i = 0; i < deviceResourceJsonArray.size(); i++) {
            JSONObject deviceObject = deviceResourceJsonArray.getJSONObject(i);
            Device device = new Device();
            String deviceId = getDeviceId(deviceObject);
            device.setId(deviceId);
            JSONObject labelJson = deviceObject.getJSONObject("labels");
            device.setX(labelJson.getDouble("x"));
            device.setY(labelJson.getDouble("y"));
            device.setStatus(deviceObject.getString("phase"));
            device.setDeviceName(deviceObject.getString("aliasName"));
            device.setKind(deviceObject.getString("kind"));
            device.setName(deviceObject.getString("name"));
            devicesList.add(device);
        }
        return devicesList;
    }

    /**
     * 获取设备的ID, 设备的Id由四个字段组成 是个jsson字符串
     *
     * @param jsonObject
     * @return
     */
    public String getDeviceId(JSONObject jsonObject) {
        JSONObject deviceId = new JSONObject();
        deviceId.put("uid", jsonObject.getString("uid"));
        deviceId.put("namespace", jsonObject.getString("namespace"));
        deviceId.put("kind", jsonObject.getString("kind"));
        deviceId.put("name", jsonObject.getString("name"));
        return deviceId.toJSONString();
    }
    public <K,V>  Map getLocationInfo(String phoneInfo){
        Map map = new HashMap<K, V>();
        JSONObject labelLocation = (JSONObject) JSONObject.parse(httpInvoke.postInvoke(phoneInfo,DEVICE_LABEL_URL));
        Double x = Double.valueOf(labelLocation.getString("x"));
        Double y = Double.valueOf(labelLocation.getString("y"));
/*
        Double z = Double.valueOf(labelLocation.getString("z"));
*/
        String room = labelLocation.getString("room");
        log.info("x轴:"+x+"y轴"+y);
        String id = labelLocation.getString("id");
        map.put("room",room);
        map.put("3dx",x);
        map.put("3dy",y);
        return map;
    }

}
