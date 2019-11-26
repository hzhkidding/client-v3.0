package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.Human;
import com.example.demo.Service.HumanResourceService;
import com.example.demo.WebScoket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class HumanResourceController extends BaseController{

    @Autowired
    HumanResourceService humanResourceService;
    @Autowired
    WebSocketServer webSocketServer;

    //人力资源注册
    @RequestMapping(path = {"/humanResourceReg"}, method = RequestMethod.POST)
    public String reg(@RequestParam("phoneNumber") String phoneNumber, HttpServletResponse response) {
        humanResourceService.humanResourceReg(phoneNumber);
        Cookie cookie = new Cookie("ticket", phoneNumber);
        cookie.setMaxAge(3600 * 24 * 5);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 5);
        response.addCookie(cookie);
        //跳回主页表
        return "redirect:/getHumanResource";
    }

    //获取人力资源

    @RequestMapping(path = {"/getHumanResource"}, method = RequestMethod.GET)
    public String getHumanResource(Model model, HttpServletResponse response) {

        List<Human> humanList = humanResourceService.getHumanResource();
        model.addAttribute("HumanList", humanList);
        return "human";
    }

    //根据用户手机号给人力资源发送消息
    @RequestMapping(path = {"/msg"}, method = RequestMethod.POST)
    @ResponseBody
    public void sendMsqgToHumanByPhone(@RequestBody String jsonObject) throws IOException {

        JSONObject msgToHuman = (JSONObject) JSONObject.parse(jsonObject);
        log.info(msgToHuman.toJSONString());
        webSocketServer.sendMessageToHumanByPhone(msgToHuman.getString("phoneNumber"), msgToHuman.getString("msg"));

    }

    //跳转到人力资源注册界面
    @GetMapping("/register")
    public String register() {

        return "register";
    }
}
