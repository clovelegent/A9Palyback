package com.softsz.a9palyback;

public class RecordAudio extends DataCommon{
    private long Date = 0;
    private String DisplayName = null;
    private String MimeType = null;
    private String Title = null;
    private long Duration = 0;
    private int Important = 0;

    public RecordAudio(String data, long date, String displayName, String mimeType, String title, long duration,int important) {
        this.data = data;
        Date = date;
        DisplayName = displayName;
        MimeType = mimeType;
        Title = title;
        Duration = duration;
        Important = important;
    }

    public RecordAudio() {
    }

    public String getData() {
        return data;
    }

    public long getDate() {
        return Date;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public String getMimeType() {
        return MimeType;
    }

    public String getTitle() {
        return Title;
    }

    public long getDuration() {
        return Duration;
    }

    public int getImportant() {
        return Important;
    }

    public void setData(String data) {

        this.data = data;
    }

    public void setDate(long date) {
        Date = date;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public void setMimeType(String mimeType) {
        MimeType = mimeType;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }

    public void setImportant(int important) {
        Important = important;
    }

    @Override
    public String toString() {
        return "RecordAudio{" +
                "Data='" + data + '\'' +
                ", Date='" + Date + '\'' +
                ", DisplayName='" + DisplayName + '\'' +
                ", MimeType='" + MimeType + '\'' +
                ", Title='" + Title + '\'' +
                ", Duration=" + Duration +
                ", Important=" + Important +
                '}';
    }
}
