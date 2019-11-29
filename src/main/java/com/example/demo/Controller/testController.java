package com.example.demo.Controller;

import com.example.demo.Entity.Human;
import com.example.demo.Service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class testController {
    @Autowired
    HumanResourceService humanResourceService;

    @RequestMapping(path = {"/h"}, method = RequestMethod.GET)
    public String getHumanResource(Model model, HttpServletResponse response) {

        List<Human> humanList = humanResourceService.getHumanResource();
        model.addAttribute("HumanList", humanList);
        return "newHuman";
    }


    @RequestMapping("/testapp")

    public String testapp() {
        return "test";
    }

    @RequestMapping("/testsb")

    public String testsb() {
        return "starbucks";
    }

    @RequestMapping("/teste")

    public String teste() {
        return "myerror";
    }

}
