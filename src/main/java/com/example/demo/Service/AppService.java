package com.example.demo.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Controller.AppController;
import com.example.demo.Controller.ResourcePriceController;
import com.example.demo.Entity.Action;
import com.example.demo.Entity.App;
import com.example.demo.Entity.AppDetail;
import com.example.demo.Util.HttpInvoke;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.Util.Constans.*;
@Slf4j
@Service
public class AppService {

    @Autowired
    RestTemplate restTemplate;
    public AppDetail appDetail;

    @Autowired
    HttpInvoke httpInvoke;
    @Autowired
    ResourcePriceController resourcePriceController;
    public Map instanceIdToDeviceListArray = new HashMap<String,JSONArray>();
  /*  public  JSONArray  deviceListArray;*/
    @Autowired
    AppController appController;

    /**
     * 获取应用列表
     * @return
     */
    public List<App> getAppList(){
        JSONObject appJson = JSONObject.parseObject(httpInvoke.getInvoke(APP_LIST_URL));
        JSONArray appJsonArray = (JSONArray) appJson.get("app_classes_introduction");
        List<App> appList = new ArrayList<>();
        for(int i=0;i<appJsonArray.size();i++){
            JSONObject appJsonObj = appJsonArray.getJSONObject(i);
            App app= new App();
            app.setId(appJsonObj .getString("_id"));
            JSONObject appProperties = (JSONObject) appJsonObj.get("properties");
            if(appProperties.getString("process_author").equals("0")){
                continue;
            }

            app.setName(appProperties.getString("name"));
            app.setProcess_author(appProperties.getString("v"));
            app.setProcess_id(appProperties.getString("process_id"));
            if(appProperties.getString("type").equals("")){
                app.setImage("images/icons/appMarket.png");
            }else {
                app.setImage(appProperties.getString("type"));
            }
            app.setColor(appProperties.getString("color"));
            appList.add(app);
        }
        return appList;

    }
    //根据ID获取应用详情
/*    public JSONObject getAppDetailById(String appId){
        //指定参数请求
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_class_id",appId);
        ResponseEntity<String>  entity = postInvoke(map,APP_DETAIL_URL);
        JSONObject appDetailJsonObj = (JSONObject) JSONObject.parse(entity.getBody());
       // System.out.println(appDetailJsonObj.toJSONString());
        return appDetailJsonObj;
    }*/

    /**
     * 应用实例化
     * @param appId
     * @param userId
     * @param X
     * @param Y
     * @return
     */
    public Map<String,Object> appInstance(String appId,String userId,Double X,Double Y) throws BusinessException {

        Map<String,Object> returnMap = new HashMap();
        JSONArray deviceListArray = new JSONArray();
        List<String> deviceNameList = new ArrayList<>();
        AppDetail appDetail = new AppDetail();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_class_id",appId);
        map.add("user_id",userId);
        map.add("x",String.valueOf(X));
        map.add("y",String.valueOf(Y));
        String appInstanceInfoString = null;

        try {
            appInstanceInfoString = httpInvoke.postInvoke(map,APP_INSTANCE_URL);
        } catch (Exception e) {
            System.out.println("走道咖啡机");
            e.printStackTrace();
            map.remove("app_class_id");
            map.add("app_class_id","5dda2e1ad90231244a5ac0ca");
            map.add("count","1");
            appInstanceInfoString= httpInvoke.postInvoke(map,APP_INSTANCE_URL);
        }
        log.info("应用实例信息"+appInstanceInfoString);
        JSONObject appInstanceInfo = null;
        try {
            appInstanceInfo = JSONObject.parseObject(appInstanceInfoString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.APP_INSTANCE_ERROR, "当前应用可用资源被占用，请稍后重试");
        }
        JSONObject jsonObject = appInstanceInfo.getJSONObject("app_instance_resource");
      //  this.appInstanceId = jsonObject.getString("_id");
        returnMap.put("appInstanceId",jsonObject.getString("_id"));
        String aliaName = null;
        JSONObject deviceIdList = jsonObject.getJSONObject("resource");
        for (Map.Entry<String, Object> entry : deviceIdList.entrySet()) {
            deviceListArray.add(entry.getValue());
            JSONObject deviceObj = (JSONObject) entry.getValue();
            aliaName = deviceObj.getString("aliasName");
            log.info("设备名称"+aliaName);
            deviceNameList.add(aliaName);
        }
        instanceIdToDeviceListArray.put(jsonObject.getString("_id"),deviceListArray);
      //  this.deviceListArray = deviceListArray;
        appDetail.setDeviceNameList(deviceNameList);
        appDetail.setAppDetailImage(appInstanceInfo.getString("process_version"));
        returnMap.put("appDetail",appDetail);
        return returnMap;
    }

    /**
     * 应用调用
     *
     * @return List
     */
    public List appInvoke(String appInstanceId) {
   /*     appController.num = 0;*/
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appInstanceId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        new Thread(){
            public void run(){
               httpInvoke.postInvoke(map,APP_INVOKE_URL);
            }
        }.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.parseArray(httpInvoke.postInvoke(map,APP_STATUS_URL));
        List<Action> actionList = new ArrayList<>();
        for(int i =0 ;i<jsonArray.size();i++){
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Action action = new Action();
        action.setActionName(jsonObject.getString("action_name"));
        action.setActionId(String.valueOf(i+1));
        actionList.add(action);

        }
        return actionList;
    }
    public String delAppInstance(String appInstanceId) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appInstanceId);
        instanceIdToDeviceListArray.remove(appInstanceId);
        return httpInvoke.postInvoke(map,APP_DEL_INSTANCE_URL);

    }
}
