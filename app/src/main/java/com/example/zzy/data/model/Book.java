package com.example.zzy.data.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private int coverResourceId;

    public Book(String title, int coverResourceId) {
        this.setTitle(title);
        this.setCoverResourceId(coverResourceId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        this.coverResourceId = coverResourceId;
    }
}
