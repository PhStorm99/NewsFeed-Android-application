package com.example.myapplication;

public class NewsFeed {
    private long id;
    private String title;
    private String description;
    private String url;
    private String img;

    public NewsFeed(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public NewsFeed(String title, String description, boolean savedInDB) {
        this(title, description);

    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) { this.img = img; }
}
