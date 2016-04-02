package com.unrealandroid.polyapp;

/**
 * Created by Rémy Kaloustian on 02/04/2016.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents an article page
 */
public class SingleArticle extends Fragment{



    public SingleArticle() throws IOException, SQLException {
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");

        Article currentArticle = new DBHelper(getContext()).getArticle(id);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.single_article, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}//class SingleArticle
