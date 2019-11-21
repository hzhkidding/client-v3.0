package com.example.demo.Entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class AppDetail {

    public String appName;
    public String appDetailImage;
    public List<String> deviceNameList;
}
