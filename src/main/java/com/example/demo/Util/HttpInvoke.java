package com.example.demo.Util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Config.RestTemplateConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@Component
public class HttpInvoke {


    @Autowired
    RestTemplate restTemplate;

    public String postInvoke(String requestInfo, String url) {
        System.out.println(requestInfo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestInfo, headers);

        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity.getBody();

    }

    public void putInvoke(JSONObject jsonObject, String url) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> entity = new HttpEntity<String>(value, header);
        restTemplate.put(url, entity);
    }

    public String getInvoke(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public String postInvoke(MultiValueMap<String, String> map, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForEntity(url, request, String.class).getBody();

    }

    public String exchange(String json, String url) {
        RestTemplate newRestTemplate = new RestTemplate();
        newRestTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
       // String json = restTemplate.postForObject("http://localhost:10013/goods/getGoodsContent", entity, String.class);

        newRestTemplate.setRequestFactory(new RestTemplateConfig.HttpComponentsClientRestfulHttpRequestFactory());

       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
*/
        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = newRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        String deviceResourceInfo = responseEntity.getBody();
        return deviceResourceInfo;
    }


}
