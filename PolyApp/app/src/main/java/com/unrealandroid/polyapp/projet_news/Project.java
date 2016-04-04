package com.unrealandroid.polyapp.projet_news;

import android.widget.ImageView;

/**
 * Created by Charly on 04/04/2016.
 */
public class Project {

    String title, content, imagePath;
    int id;

    public Project(int id, String content, String imagePath, String title) {

        this.content = content;
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return imagePath;
    }
}
