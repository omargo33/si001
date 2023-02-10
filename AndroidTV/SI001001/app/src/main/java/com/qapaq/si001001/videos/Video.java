package com.qapaq.si001001.videos;


public class Video {
    String name;
    String url;
    String path;
    long duration;

    public Video(String name, String url, String path, long duration) {
        this.name = name;
        this.url = url;
        this.path = path;
        this.duration = duration;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
