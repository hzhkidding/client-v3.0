package com.example.demo.Controller;

import com.example.demo.Service.DeviceResourceService;
import com.example.demo.Service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String join() {

        return "configuration";
    }
}
