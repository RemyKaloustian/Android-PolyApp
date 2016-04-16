package com.unrealandroid.polyapp.article;

/**
 * Created by Rémy Kaloustian on 02/04/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unrealandroid.polyapp.AsyncTaskImage;
import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents an article page
 */
public class SingleArticle extends AppCompatActivity{

    private Article _article;


    public SingleArticle() throws IOException, SQLException {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        _article = new DBHelper(this).getArticle(id);

    }



    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.single_article, container, false);


        TextView title = (TextView)rootView.findViewById(R.id.ArticleTitle);
        title.setText(_article._title);

        TextView date = (TextView)rootView.findViewById(R.id.ArticleDate);
        title.setText(_article._date);

        TextView content = (TextView)rootView.findViewById(R.id.ArticleContent);
        content.setText(_article._content);

        ImageView image = (ImageView)rootView.findViewById(R.id.ArticleImage);

        AsyncTaskImage img = new AsyncTaskImage(image);
        img.execute(_article._pathToImage);






        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}//class SingleArticle
