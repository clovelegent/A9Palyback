package com.softsz.a9palyback;

public class RecordImage extends DataCommon{
    private String displayName;
    private int size;
    private String mimeType;
    private long dateAdded;
    private String title;
    private double latitude;
    private double longitude;
    private int orientation;
    private int important;

    public RecordImage(String data, String displayName, int size, String mimeType, long dateAdded, String title, double latitude, double longitude, int orientation, int important) {
        this.data = data;
        this.displayName = displayName;
        this.size = size;
        this.mimeType = mimeType;
        this.dateAdded = dateAdded;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.orientation = orientation;
        this.important = important;
    }

    public RecordImage() {
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

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setImportant(int important) {
        this.important = important;
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

    public long getDateAdded() {
        return dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getImportant() {
        return important;
    }

    @Override
    public String toString() {
        return "RecordImage{" +
                "data='" + data + '\'' +
                ", displayName='" + displayName + '\'' +
                ", size=" + size +
                ", mimeType='" + mimeType + '\'' +
                ", dateAdded=" + dateAdded +
                ", title='" + title + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", orientation=" + orientation +
                ", important=" + important +
                '}';
    }
}
