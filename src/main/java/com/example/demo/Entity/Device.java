package com.example.demo.Entity;

import lombok.Data;

@Data
public class Device {

    private String id;
    private String deviceName;
    private String deviceModel;
    private String deviceType;
    private Integer ownerId;
    private Number price;
    private String status;
    private Number x;
    private Number y;
    private String kind;

}
