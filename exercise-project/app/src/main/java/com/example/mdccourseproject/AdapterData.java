package com.example.mdccourseproject;

public class AdapterData {
    private String headline;
    private String url;
    private String text;

    public AdapterData(String headline, String url, String text){
        this.headline = headline;
        this.url = url;
        this.text = text;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
