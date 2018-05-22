package com.softsz.a9palyback;


public abstract class DataCommon {
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataCommon{" +
                "data='" + data + '\'' +
                '}';
    }
}
