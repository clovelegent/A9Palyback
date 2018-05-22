package com.softsz.a9palyback;

public class CameraMedia extends DataCommon{
    private String displayName;

    public CameraMedia(String data, String displayName) {
        this.data = data;
        this.displayName = displayName;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "CameraMedia{" +
                "data='" + data + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
