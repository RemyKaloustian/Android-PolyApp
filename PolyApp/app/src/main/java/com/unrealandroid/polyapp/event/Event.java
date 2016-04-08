package com.unrealandroid.polyapp.event;

/**
 * Created by Kevin on 02/04/2016.
 */
public class Event {

    private int id;
    private String title;
    private String content;
    private float location_lat;
    private float location_long;
    private String imagePath;
    private String date;

    public Event(int id, String title, String content, float location_lat, float location_long, String imagePath, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location_lat = location_lat;
        this.location_long = location_long;
        this.imagePath = imagePath;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public float getLocation_lat() {
        return location_lat;
    }

    public float getLocation_long() {
        return location_long;
    }

    public String getImagePath(){
        return imagePath;
    }
}
