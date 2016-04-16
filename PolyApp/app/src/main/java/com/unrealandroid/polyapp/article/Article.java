package com.unrealandroid.polyapp.article;

/**
 * Created by Rémy Kaloustian on 02/04/2016.
 */
public class Article {

    public String _pathToImage;
    public String _title;
    public String _content;
    public String _date;
    public int _id;

    public Article(int id, String pathToImage, String title, String content, String date) {
        _id = id;
        _pathToImage =  pathToImage;
        _title = title;
        _content = content;
        _date = date;
    }


}//class Article
