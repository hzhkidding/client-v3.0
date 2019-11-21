package com.example.demo.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.Infomation;
import com.example.demo.Util.HttpInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Util.Constans.*;

@Service
public class InfoResourceService {
    @Autowired
    HttpInvoke httpInvoke;



    public List<Infomation> getInfoList() {
        JSONArray infoJsonArray = JSONArray.parseArray(httpInvoke.getInvoke(INFO_LIST_URL));
        List<Infomation> infoList = new ArrayList<>();

        for (int i = 0; i < infoJsonArray.size(); i++) {
            JSONObject infoJsonObj = infoJsonArray.getJSONObject(i);
            Infomation info = new Infomation();
            info.setInfoName(infoJsonObj.get("apkName").toString());
            info.setApiArray(JSONArray.parseArray(infoJsonObj.get("apiInfo").toString()));
            String infoId = infoJsonObj.get("dexName").toString().substring(0, infoJsonObj.get("dexName").toString().indexOf("."));
            info.setInfoId(infoId);
            info.setColor(COLOR[i%COLOR.length]);
            info.setImageUrl("images/icons/law.png");
            infoList.add(info);
        }
        return infoList;

    }

}
