package com.example.demo.TestController;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class Test {
    public ThreadLocal<Integer> t = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 1;
        }
    };
    public Integer num = 1;



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
    @RequestMapping(path = {"/test"})
    public String test() {
        return "test.ftl";
    }
    @RequestMapping(path = {"/test2"},method = RequestMethod.PUT)
    public String test2(Model model,@RequestBody String jsonObject) {

        return "home";
    }
    @RequestMapping(path = {"/test3"},method = RequestMethod.GET)
    public String test3() {
        System.out.println(Thread.currentThread());
        num = num+1;
        System.out.println("num"+num);
        t.set(t.get()+1);
        System.out.println("t"+t.get());
        return "configuration";

    }

   /* @RequestMapping(path = {"/state"},method = RequestMethod.POST)
    public String state(Model model,@RequestParam("app_instance_id") String jsonObject) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.codePointAt("state")
        return "home";*/


}
