package com.example.mohit.scoocart;

/**
 * Created by mohit on 2/27/2018.
 */

public class Notification {
    int imageid;
    String title;
    String time;

    public Notification(int imageid, String title, String time) {
        this.imageid = imageid;
        this.title = title;
        this.time = time;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
