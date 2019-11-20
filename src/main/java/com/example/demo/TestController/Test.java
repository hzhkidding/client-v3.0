package com.example.demo.TestController;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class Test {

    //异步请求调试
    @RequestMapping(path = {"/humanResourceRegTest"},method = RequestMethod.POST)
    @ResponseBody
    public void callBackFor(HttpServletRequest request, @RequestBody String jsonObject) {
        log.info(jsonObject);
    }
    @RequestMapping(path = {"/deviceResouceTest"},method = RequestMethod.GET)
    @ResponseBody
    public String deviceResourceDescovery() {
        JSONObject deviceResouceInfo = new JSONObject();
        deviceResouceInfo.put("资源名称","咖啡机");
        return deviceResouceInfo.toJSONString();
    }
    @RequestMapping(path = {"/about"},method = RequestMethod.GET)
    public String deviceResourceDescovery(Model model) {

        return "about";
    }
    @RequestMapping(path = {"/test"},method = RequestMethod.PUT)
    public String test(Model model,@RequestBody String jsonObject) {
        System.out.println(jsonObject);
        return "home";
    }
    @RequestMapping(path = {"/test2"},method = RequestMethod.PUT)
    public String test2(Model model,@RequestBody String jsonObject) {

        return "home";
    }
    @RequestMapping(path = {"/test3"},method = RequestMethod.PUT)
    public String test3(Model model,@RequestBody String jsonObject) {

        return "home";
    }

   /* @RequestMapping(path = {"/state"},method = RequestMethod.POST)
    public String state(Model model,@RequestParam("app_instance_id") String jsonObject) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.codePointAt("state")
        return "home";*/


}
