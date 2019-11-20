package com.example.demo.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Util.HttpInvoke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static com.example.demo.Util.Constans.*;

@Service
public class ResourcePriceService {

    @Autowired
    HttpInvoke httpInvoke;

    @Autowired
    RestTemplate restTemplate;


    public Double getBaseprice(JSONObject userDevicePrice) {
        Double cost = userDevicePrice.getDouble("cost");

        String basePrice = httpInvoke.postInvoke("", RESOURCES_BASEPRICE_URL + cost.toString() + "/");


        return Double.valueOf(basePrice);

    }

    public void sendExPrice(JSONObject finalJson) throws JsonProcessingException {

        httpInvoke.putInvoke(finalJson, DEVICE_RESOURCE_URL);

    }

    public Double getFinalPrice(JSONObject finalPrice) {

        JSONArray priceArray = JSONArray.parseArray(httpInvoke.postInvoke(finalPrice.toJSONString(), FINAL_PRICE_URL));
        Double totalPrice = 0.0;
        for (int i = 0; i < priceArray.size(); i++) {
            JSONObject price = priceArray.getJSONObject(i);
            totalPrice += price.getDouble("price");
        }
        Random num = new Random(5);
        JSONObject totalNum = new JSONObject();
        totalNum.put("totalPrice", totalPrice);
        return totalPrice;
    }


}
