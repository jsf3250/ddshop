package com.dhc.ddshop.common.dto;

//排序

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String sort;
    private String order;

    public List<String> getOrderParams(){
        String[] sorts = this.sort.split(",");
        String[] orders = this.order.split(",");
        List<String> list = new ArrayList<>();
        for (int i=0;i<sorts.length;i++) {
            String temp = sorts[i] + " " + orders[i];
            list.add(temp);
        }
        return list;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
