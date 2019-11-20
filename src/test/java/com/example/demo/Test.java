package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public  void test(){
        List list = new ArrayList();
        Double s = 3.231231;
        list.add(s);
        String ss = "321.231";
        list.add(ss);
        for (int i = 0; i < list.size(); i++) {

            System.out.println("name:" + list.get(i)+list.get(i).getClass());
        }

    }
}
