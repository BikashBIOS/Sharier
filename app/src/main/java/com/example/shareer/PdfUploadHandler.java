package com.example.shareer;

public class PdfUploadHandler {
    public String name;
    public String url;

    public PdfUploadHandler() {
    }

    public PdfUploadHandler(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
