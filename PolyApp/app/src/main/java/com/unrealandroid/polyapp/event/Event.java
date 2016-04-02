package com.unrealandroid.polyapp.event;

/**
 * Created by Kevin on 02/04/2016.
 */
public class Event {

    private int id;
    private String title;
    private String content;
    private int location_lat;
    private int location_long;

    public Event(int id, String title, String content, int location_lat, int location_long) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location_lat = location_lat;
        this.location_long = location_long;
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

    public int getLocation_lat() {
        return location_lat;
    }

    public int getLocation_long() {
        return location_long;
    }
}
