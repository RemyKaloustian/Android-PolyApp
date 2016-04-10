package com.unrealandroid.polyapp.event;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.unrealandroid.polyapp.AsyncTaskImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin on 02/04/2016.
 */
public class Event implements Parcelable{

    private int id;
    private String title;
    private String content;
    private float location_lat;
    private float location_long;
    private String imagePath;
    private String date;
    private Bitmap image;

    public Event(int id, String title, String content, float location_lat, float location_long, final String imagePath, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location_lat = location_lat;
        this.location_long = location_long;
        this.imagePath = imagePath;
        this.date = date;

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL urlImage = new URL(imagePath);
                    HttpURLConnection con = (HttpURLConnection) urlImage.openConnection();
                    InputStream is = con.getInputStream();
                    image = BitmapFactory.decodeStream(is);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    protected Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        location_lat = in.readFloat();
        location_long = in.readFloat();
        imagePath = in.readString();
        date = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

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

    public Bitmap getImage(){
        return image;
    }

    public String getImagePath(){
        return imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeFloat(location_lat);
        dest.writeFloat(location_long);
        dest.writeString(imagePath);
        dest.writeString(date);
        //image.writeToParcel(dest, 0);
    }
}
