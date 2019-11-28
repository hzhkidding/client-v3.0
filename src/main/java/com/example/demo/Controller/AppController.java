package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.Action;
import com.example.demo.Entity.App;
import com.example.demo.Entity.AppDetail;
import com.example.demo.Service.AppService;
import com.example.demo.Util.HttpInvoke;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.demo.Util.Constans.APP_STATUS_URL;

@Slf4j
@Controller
public class AppController extends BaseController{
    Map<String,Integer> idToNum = new HashMap<>();
    Map<String,Map<Integer,String>> globalStateMap = new HashMap<>();

   /* public int num = 0;*/
    @Autowired
    HttpInvoke httpInvoke;

  /*  private String status;*/
    @Autowired
    AppService appService;
  /*  public JSONArray deviceListArray;

    public String appInstanceId;*/
    public Double X;
    public Double Y;

    @RequestMapping(path = {"/getAppList"}, method = RequestMethod.GET)
    public String getAllAppList(Model model) {

        List<App> appListService = appService.getAppList();
        List<App> appList = new ArrayList<>();
        for(int i =0;i<appListService.size();i++){
            App app = appListService.get(i);
            app.setNo(i);
            appList.add(app);
        }
        model.addAttribute("AppList", appList);
        return "app";
    }

    //进入应用详情页
    @RequestMapping(path = {"/appInstance"}, method = RequestMethod.POST)

    public String appInstance(Model model,@RequestParam("appId") String appId, @RequestParam("userId") String userId,@RequestParam("appName") String appName) throws BusinessException {

        List<String>  deviceNameList= new ArrayList<>();
        AppDetail appDetail;

        Map map;
        try {
            map = appService.appInstance(appId, userId, this.X, this.Y);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.APP_INSTANCE_ERROR, "当前应用可用资源被占用，请稍后重试");
        }
        appDetail = (AppDetail) map.get("appDetail");
        appDetail.setAppName(appName);
        model.addAttribute("AppDatail",appDetail);
        model.addAttribute("appInstanceId",map.get("appInstanceId"));
        if(appName.contains("星巴克")){
            return "starbucks";
        }
        return "newabout";
    }
    @RequestMapping(path = {"/delAppInstance"}, method = RequestMethod.POST)
    @ResponseBody
    public String delAppInstance(@RequestBody String appInstanceId) {
        JSONObject jsonObject = JSONObject.parseObject(appInstanceId);
        log.info("删除实例"+jsonObject.getString("appInstanceId"));
        return appService.delAppInstance(jsonObject.getString("appInstanceId"));

    }
   //应用调用
    @RequestMapping(path = {"/appInvoke"}, method = RequestMethod.POST)
    public String appInvoke(Model model,@RequestParam("appInstanceId") String appInstanceId,@RequestParam("appName") String appName,@RequestParam("appDetailImage") String appDetailImage) throws BusinessException {
        //  String appInstanceId = this.appInstanceId;
        log.info("开始调用应用，实例Id",appInstanceId);
        List<Action> actionList = null;
        try {
            actionList = appService.appInvoke(appInstanceId);

        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.APP_INVOKE_ERROR, "当前状态不稳定，请刷新重试");

        }
        idToNum.put(appInstanceId,0);
        Map map  = new HashMap<Integer,String>();
        globalStateMap.put(appInstanceId,map);
        model.addAttribute("appName",appName);
        model.addAttribute("appDetailImage",appDetailImage);
        model.addAttribute("ActionList",actionList);
        model.addAttribute("appInstanceId",appInstanceId);
        return "appRunning";
    }

    @RequestMapping(path = {"/getStatus2"}, method = RequestMethod.POST)
    @ResponseBody
    public String getStatus2(@RequestBody String appInstanceIdJson) {
        JSONObject jsonObject0 = JSONObject.parseObject(appInstanceIdJson);
        String appInstanceId = jsonObject0.getString("appInstanceId");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appInstanceId);
        log.info(appInstanceId);
        JSONArray jsonArray = JSONArray.parseArray(httpInvoke.postInvoke(map,APP_STATUS_URL));
        int newNum = idToNum.get(appInstanceId);
        int sizeNum = jsonArray.size();

        if(newNum != jsonArray.size()) {
            JSONObject jsonObject = jsonArray.getJSONObject(newNum);
            log.info("state=="+jsonObject.getString("state"));
            if (jsonObject.getString("state").equals("2")) {
                log.info(jsonArray.toJSONString());
                log.info(jsonObject.getString("action_name")+jsonObject.getString("state"));
                idToNum.put(appInstanceId,++newNum);
                log.info("num=="+newNum);
                return String.valueOf(newNum);
            }
        }
        if(newNum == jsonArray.size()){
            return "-1";
        }

        return "0";
    }
    @RequestMapping(path = {"/getStatus"}, method = RequestMethod.POST)
    @ResponseBody
    public String getStatus(@RequestBody String appInstanceIdJson) {
        JSONObject jsonObject0 = JSONObject.parseObject(appInstanceIdJson);
        String appInstanceId = jsonObject0.getString("appInstanceId");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appInstanceId);
        JSONArray jsonArray = JSONArray.parseArray(httpInvoke.postInvoke(map,APP_STATUS_URL));
        int newNum = idToNum.get(appInstanceId);
        Map  stateMap= globalStateMap.get(appInstanceId);
        for(int i =0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(jsonObject.getString("state").equals("2")){
                if(stateMap.containsKey(i)!=true){
                    stateMap.put(i,jsonObject.getString("state"));
                    newNum++;
                 /*   log.info(jsonObject.getString("action_name")+jsonObject.getString("state"));
                    log.info(String.valueOf(newNum));*/
                    return String.valueOf(i+1);
                }
            }
        }
        if(newNum == jsonArray.size()){
            return "-1";
        }
        return "0";
    }
}