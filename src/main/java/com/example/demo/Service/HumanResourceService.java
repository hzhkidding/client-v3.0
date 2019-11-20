package com.example.demo.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.demo.Entity.Human;
import com.example.demo.Util.HttpInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static com.example.demo.Util.Constans.HUMAN_RESOURCE_REG_URL;

@Slf4j
@Service
public class HumanResourceService {

    public JSONObject humanResourceRegJson = (JSONObject) JSONObject.parse("{\n" +
            "\t\"resourceSpec\":{\n" +
            "\t\t\n" +
            "\t\t\"kind\": \"Human\",\n" +
            "\t\t\"phase\": \"Notready\",\n" +
            "\t\t\"bound\": false,\n" +
            "\t\t \"accessMode\": \"Exclusive\",\n" +
            "\t\t \"icon\": \"string\",\n" +
            "\t\t \"description\": \"人力资源\"\n" +
            "\t\t}\n" +
            "}");
    @Autowired
    HttpInvoke httpInvoke;

    @Autowired
    RestTemplate restTemplate;


    public void humanResourceReg(String phoneNumber) {
        Human human = new Human();
        human.setPhoneNumber(phoneNumber);
        JSONObject labels = new JSONObject();
        JSONObject resourceSpec = (JSONObject) humanResourceRegJson.get("resourceSpec");
        labels.put("io.fusionapp.crowdsourcing/phone", phoneNumber);
        resourceSpec.put("name", "human" + phoneNumber);
        resourceSpec.put("labels", labels);
        humanResourceRegJson.put("resourceSpec", resourceSpec);
        httpInvoke.postInvoke(humanResourceRegJson.toJSONString(), HUMAN_RESOURCE_REG_URL);

    }


    public List<Human> getHumanResource() {
        List<Human> humanList = new ArrayList<>();
        JSONArray resourceCollect = (JSONArray) JSONArray.parse(httpInvoke.getInvoke(HUMAN_RESOURCE_REG_URL));
        for (int i = 0; i < resourceCollect.size(); i++) {
            JSONObject resouceJsonObj = resourceCollect.getJSONObject(i);
            if (resouceJsonObj.get("kind").equals("Human")) {
                JSONObject humanResourceJsonObj = resouceJsonObj;
                JSONObject labels = (JSONObject) humanResourceJsonObj.get("labels");
                Human human = new Human();
                human.setPhoneNumber(String.valueOf(labels.get("io.fusionapp.crowdsourcing/phone")));
                humanList.add(human);
            }
        }
        return humanList;
    }

}
