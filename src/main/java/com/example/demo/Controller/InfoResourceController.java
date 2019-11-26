package com.example.demo.Controller;


import com.example.demo.Entity.AppDetail;
import com.example.demo.Entity.Infomation;
import com.example.demo.Service.InfoResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class InfoResourceController extends BaseController {
    @Autowired
    InfoResourceService infoResourceService;


    @RequestMapping(path = {"/getInfoResource"}, method = RequestMethod.GET)
    public String getInfoResource(Model model) {

        List<Infomation> infoList = infoResourceService.getInfoList();
        model.addAttribute("infoList", infoList);
        return "infomation";
    }
    @RequestMapping(path = {"/getInfoDetail/{infoId"}, method = RequestMethod.POST)
    public void getInfoDetail(Model model,@PathVariable("InfoId") String infoId) {

      System.out.println(infoId);
    }


}
