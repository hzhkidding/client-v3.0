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

        List<App> appList1 = appService.getAppList();
        List<App> appList = new ArrayList<>();
        for(int i =0;i<appList1.size();i++){
            App app = appList1.get(i);
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
        AppDetail appDetail = new AppDetail();
        try {
            appDetail = appService.appInstance(appId, userId, this.X, this.Y);
        } catch (Exception e) {
            appDetail.setAppDetailImage("images/nuapp.jpg");
            appDetail.setAppName("应用开发中，敬请期待");
            deviceNameList.add("应用开发中，敬请期待");
            appDetail.setDeviceList(deviceNameList);
            model.addAttribute("AppDatail",appDetail);
            return "about";
        }
        appDetail.setAppName(appName);

        deviceNameList.add("咖啡机");
        appDetail.setDeviceList(deviceNameList);
        model.addAttribute("AppDatail",appDetail);
        return "about";
    }
   //应用调用
    @RequestMapping(path = {"/appInvoke"}, method = RequestMethod.GET)
    public String appInvoke(Model model) {
        //  String appInstanceId = this.appInstanceId;

        List<Action> actionList = appService.appInvoke();

        model.addAttribute("ActionList",actionList);
        return "appRunning";
    }

    @RequestMapping(path = {"/getStatus"}, method = RequestMethod.GET)
    @ResponseBody
    public String getStatus() {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("app_instance_id",appService.appInstanceId);
        JSONArray jsonArray = JSONArray.parseArray(httpInvoke.postInvoke(map,APP_STATUS_URL));
        if(num != jsonArray.size()) {
            JSONObject jsonObject = jsonArray.getJSONObject(num);
            log.info(jsonObject.getString("state"));
            if (jsonObject.getString("state").equals("2")) {
                log.info(jsonArray.toJSONString());
                log.info(jsonObject.getString("action_name")+jsonObject.getString("state"));
                num++;
                log.info(String.valueOf(num));
                return String.valueOf(num);
            }
        }
        if(num == jsonArray.size()){
            return "-1";
        }
        return "0";
    }
}