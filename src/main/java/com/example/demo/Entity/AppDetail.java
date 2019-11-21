package com.example.demo.Entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class AppDetail {

    private String appName;
    private String appDetailImage;
    private List<String> deviceList;
}
