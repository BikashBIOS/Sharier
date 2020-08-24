package com.example.shareer;

public class MultipleImagesHandler {

    String ImgLink, mIKey;

    public MultipleImagesHandler() {
    }

    public MultipleImagesHandler(String imgLink, String mIKey) {
        ImgLink = imgLink;
        this.mIKey = mIKey;
    }

    public String getImgLink() {
        return ImgLink;
    }

    public void setImgLink(String imgLink) {
        ImgLink = imgLink;
    }

    public String getmIKey() {
        return mIKey;
    }

    public void setmIKey(String mIKey) {
        this.mIKey = mIKey;
    }
}
