package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.unrealandroid.polyapp.AsyncTaskImage;
import com.unrealandroid.polyapp.R;

/**
 * Created by Charly on 06/04/2016.
 */
public class SingleProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_project);

        Intent intent = getIntent();
        Project project = intent.getParcelableExtra("Project");

        setTitle(project.getTitle());

        TextView text = (TextView) findViewById(R.id.test);
        text.setText(project.getContent());


        ImageView image = (ImageView) findViewById(R.id.singleImage);
        AsyncTaskImage asyncTaskImage = new AsyncTaskImage(image);
        asyncTaskImage.execute(project.getImage());

    }
}
