package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        int mUIFlag =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);

        Intent intent = getIntent();
        Project project = intent.getParcelableExtra("Project");

        setTitle(project.getTitle());

        TextView content = (TextView) findViewById(R.id.test);
        content.setText(project.getContent());

        TextView participants = (TextView) findViewById(R.id.participants);
        participants.setText("Participants : " + project.getParticipants());

        ImageView image = (ImageView) findViewById(R.id.singleImage);
        AsyncTaskImage asyncTaskImage = new AsyncTaskImage(image);
        asyncTaskImage.execute(project.getImage());

    }
}
