package com.unrealandroid.polyapp.article;

/**
 * Created by Rémy Kaloustian on 02/04/2016.
 */
public class Article {

    String _pathToImage;
    String _title;
    String _content;
    String _date;
    int _id;

    public Article(int id, String pathToImage, String title, String content, String date) {
        _id = id;
        _pathToImage =  pathToImage;
        _title = title;
        _content = content;
        _date = date;
    }


}//class Article
