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
        JSONArray infoJsonArray = JSONArray.parseArray(httpInvoke.getInvoke(DEVICE_RESOURCE_URL));
        List<Infomation> infoList = new ArrayList<>();
        for (int i = 0; i < infoJsonArray.size(); i++) {
            JSONObject infoJsonObj = infoJsonArray.getJSONObject(i);
            if(infoJsonObj.getString("kind").equals("Service")) {
                Infomation info = new Infomation();
                info.setInfoName(infoJsonObj.getString("aliasName"));
                info.setInfoId(getInfoId(infoJsonObj));
                //info.setApiArray(JSONArray.parseArray(infoJsonObj.get("apiInfo").toString()));
                info.setImageUrl("images/icons/law.png");
                infoList.add(info);
            }
        }
        return infoList;

    }
    public String getInfoId(JSONObject infoJsonObj){
        JSONObject infoId = new JSONObject();
        infoId.put("uid", infoJsonObj.getString("uid"));
        infoId.put("namespace", infoJsonObj.getString("namespace"));
        infoId.put("kind", infoJsonObj.getString("kind"));
        infoId.put("name", infoJsonObj.getString("name"));
        System.out.println(infoId.toString());
        return infoId.toJSONString();
    }

}
