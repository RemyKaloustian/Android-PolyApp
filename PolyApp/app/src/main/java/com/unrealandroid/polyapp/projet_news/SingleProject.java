package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.unrealandroid.polyapp.AsyncTaskImage;
import com.unrealandroid.polyapp.AsyncTaskImageSmart;
import com.unrealandroid.polyapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

        setTitle(project.getTitle()); // Sets the title of the activity (label in manifest.xml)

        TextView content = (TextView) findViewById(R.id.singleContent);
        content.setText(project.getContent());

        TextView participants = (TextView) findViewById(R.id.participants);
        participants.setText("Participants : " + project.getParticipants());

        /**** Sets special colors for the first project ****/

        if(project.getId() == 1)
        {
            findViewById(R.id.single_project_layout).setBackgroundColor(Color.parseColor("#34495e"));
            content.setTextColor(Color.parseColor("#ecf0f1"));
            participants.setTextColor(Color.parseColor("#ecf0f1"));
            participants.setTypeface(null, Typeface.BOLD);
        }

        /**** Image settings ****/

        ImageView image = (ImageView) findViewById(R.id.singleImage);

        try{
            FileInputStream f = this.openFileInput(project.getTitle());
            Bitmap bitmap = BitmapFactory.decodeStream(f);
            image.setImageBitmap(bitmap);
            File file = new File(project.getTitle());

            file.delete(); // It seems that the file doesn't exist... Have to resolve this because it doesn't delete.
        }
        catch(FileNotFoundException e)
        {
            AsyncTaskImageSmart asyncTaskImage = new AsyncTaskImageSmart(image, project);
            asyncTaskImage.execute(project.getImage());
        }
    }
}
