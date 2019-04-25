package com.alan.util;

import java.util.ArrayList;
import java.util.List;

public class ChartHelper {
    String name;
    List<Object> data = new ArrayList<Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
