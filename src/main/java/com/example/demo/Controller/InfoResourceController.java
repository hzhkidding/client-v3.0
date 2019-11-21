package com.example.demo.Controller;


import com.example.demo.Entity.AppDetail;
import com.example.demo.Entity.Infomation;
import com.example.demo.Service.InfoResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InfoResourceController {
    @Autowired
    InfoResourceService infoResourceService;

    //    @RequestMapping(path = {"/infoInstance"}, method = RequestMethod.GET)
//    public String appInstance(Model model, @RequestParam("infoId") String infoId, @RequestParam("userId") String userId, @RequestParam("infoName") String infoName) {
//
//        infoResourceService.infoInstance(infoId, userId);
//        AppDetail appDetail = new AppDetail();
//        appDetail.setAppName(appName);
//        model.addAttribute("AppDatail",appDetail);
//        return "about";
//    }
    @RequestMapping(path = {"/getInfoList"}, method = RequestMethod.GET)
    public String appInstance(Model model) {

        List<Infomation> infoList=infoResourceService.getInfoList();
        model.addAttribute("infoList",infoList);
        return "infomation";
    }


}
