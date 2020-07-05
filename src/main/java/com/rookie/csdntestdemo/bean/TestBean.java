package com.rookie.csdntestdemo.bean;

public class TestBean {


    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "key='" + key + '\'' +
                '}';
    }
}
