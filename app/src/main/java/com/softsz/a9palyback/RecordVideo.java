package com.softsz.a9palyback;

public class RecordVideo extends DataCommon{

    private String displayName;
    private int size;
    private String mimeType;
    private long dateAdded;
    private String title;
    private long duration;
    private String resolution;
    private double latitude;
    private double longitude;
    private int important;

    public RecordVideo(String data, String displayName, int size, String mimeType, String title, long duration, String resolution, double latitude, double longitude, int important,long dateAdd) {
        this.data = data;
        this.displayName = displayName;
        this.size = size;
        this.mimeType = mimeType;
        this.title = title;
        this.duration = duration;
        this.resolution = resolution;
        this.latitude = latitude;
        this.longitude = longitude;
        this.important = important;
        this.dateAdded = dateAdd;
    }

    public RecordVideo() {
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getData() {
        return data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getSize() {
        return size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return duration;
    }

    public String getResolution() {
        return resolution;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getImportant() {
        return important;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return "RecordVedio{" +
                "data='" + data + '\'' +
                ", displayName='" + displayName + '\'' +
                ", size=" + size +
                ", mimeType='" + mimeType + '\'' +
                ", dateAdded=" + dateAdded +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", resolution='" + resolution + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", important=" + important +
                '}';
    }
}
