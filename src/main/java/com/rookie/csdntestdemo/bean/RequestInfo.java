package com.rookie.csdntestdemo.bean;

import java.util.List;
import java.util.Map;

public class RequestInfo {

    // 请求体
    private String requestBody;

    // cookie信息
    private List<String> cookies;

    // head
    private Map<String, String> headMap;

    // 耗时
    private Long cost;


    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(Map<String, String> headMap) {
        this.headMap = headMap;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
