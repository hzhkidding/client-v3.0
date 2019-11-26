package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.Action;
import com.example.demo.Entity.App;
import com.example.demo.Entity.AppDetail;
import com.example.demo.Service.AppService;
import com.example.demo.Util.HttpInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.example.demo.Util.Constans.APP_STATUS_URL;

@Slf4j
@Controller
public class AppController extends BaseController{

    public int num = 0;
    @Autowired
    HttpInvoke httpInvoke;

    private String status;
    @Autowired
    AppService appService;
    public JSONArray deviceListArray;

    public String appInstanceId;
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
    public String appInstance(Model model,@RequestParam("appId") String appId, @RequestParam("userId") String userId,@RequestParam("appName") String appName) {

        List<String>  deviceNameList= new ArrayList<>();
        AppDetail appDetail;
        Map map;
        try {
            map = appService.appInstance(appId, userId, this.X, this.Y);
            appDetail = (AppDetail) map.get("appDetail");
        } catch (Exception e) {
            e.printStackTrace();
            return "noapp";
        }
        appDetail.setAppName(appName);
        model.addAttribute("AppDatail",appDetail);
        model.addAttribute("appInstanceId",map.get("appInstanceId"));
        return "test";
    }
    @RequestMapping(path = {"/delAppInstance/{appInstanceId}"}, method = RequestMethod.DELETE)
    public String delAppInstance(@PathVariable("appInstanceId") String appInstanceId) {
        appService.delAppInstance(appInstanceId);
        return "app";
    }
   //应用调用
    @RequestMapping(path = {"/appInvoke"}, method = RequestMethod.POST)
    public String appInvoke(Model model,@RequestParam("appInstanceId") String appInstanceId) {
        //  String appInstanceId = this.appInstanceId;
        System.out.println(appInstanceId);
        List<Action> actionList = appService.appInvoke(appInstanceId);
        model.addAttribute("AppDetail",appService.appDetail);
        model.addAttribute("ActionList",actionList);
        model.addAttribute("appInstanceId",appInstanceId);
        return "appRunning";
    }

    @RequestMapping(path = {"/getStatus"}, method = RequestMethod.POST)
    @ResponseBody
    public String getStatus(@RequestBody String appInstanceIdJson) {
        log.info(appInstanceIdJson);
        JSONObject jsonObject0 = JSONObject.parseObject(appInstanceIdJson);
        String appInstanceId = jsonObject0.getString("appInstanceId");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appInstanceId);
        log.info(appInstanceId);
        JSONArray jsonArray = JSONArray.parseArray(httpInvoke.postInvoke(map,APP_STATUS_URL));
        if(num != jsonArray.size()) {
            JSONObject jsonObject = jsonArray.getJSONObject(num);
            log.info("state=="+jsonObject.getString("state"));
            if (jsonObject.getString("state").equals("2")) {
                log.info(jsonArray.toJSONString());
                log.info(jsonObject.getString("action_name")+jsonObject.getString("state"));
                num++;
                log.info("num=="+num);
                return String.valueOf(num);
            }
        }
        if(num == jsonArray.size()){
            return "-1";
        }
        return "0";
    }
}