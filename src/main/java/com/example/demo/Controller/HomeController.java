package com.example.demo.Controller;

import com.example.demo.Service.DeviceResourceService;
import com.example.demo.Service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin.util.UIUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

@Controller/*@RequestMapping("/user") @CrossOrigin(origins = {"*"}, allowCredentials = "true")*/
public class HomeController extends BaseController{


    public String test;

    @RequestMapping("/collect")
    public String collect() {
        return "collect";
    }

    @RequestMapping("/resource")
    public String res() {
        return "resource";
    }

    @RequestMapping("/home")
    public String join(HttpServletRequest httpServletRequest,HttpServletResponse response) {

        String token = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if(token==null){
            Cookie cookie = new Cookie("token","ss");
            cookie.setMaxAge(3600 * 24 * 5);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "configuration";
    }

    @RequestMapping("/" )
    public String index(){
        return "configuration";
    }
}
