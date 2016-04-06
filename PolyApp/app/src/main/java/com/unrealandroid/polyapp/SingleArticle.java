package com.unrealandroid.polyapp;

/**
 * Created by Rémy Kaloustian on 02/04/2016.
 */

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unrealandroid.polyapp.event.Event;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Represents an article page
 */
public class SingleArticle extends AppCompatActivity{

    private Article _article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);

        try {
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            List<Event> list = dbHelper.getAllEvent();
            _article = dbHelper.getArticle(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.single_article);
        TextView title = (TextView) findViewById(R.id.ArticleTitle);
        title.setText(_article._title);

        TextView date = (TextView) findViewById(R.id.ArticleDate);
        title.setText(_article._date);

        TextView content = (TextView) findViewById(R.id.ArticleContent);
        content.setText(_article._content);

        ImageView image = (ImageView) findViewById(R.id.ArticleImage);

        /*AsyncTaskImage img = new AsyncTaskImage(image);
        img.execute(_article._pathToImage);*/

        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
    }
}//class SingleArticle
