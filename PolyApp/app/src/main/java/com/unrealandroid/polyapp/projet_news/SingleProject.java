package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.unrealandroid.polyapp.AsyncTaskImageSmart;
import com.unrealandroid.polyapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Charly on 06/04/2016.
 */
public class SingleProject extends AppCompatActivity {

    private final static String COLOR_FULLY_TRANSPARENT = "#00000000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_project);

        Intent intent = getIntent();
        Project project = intent.getParcelableExtra("Project");

        /**** Title settings in the Appbar ****/

        CollapsingToolbarLayout colapse = (CollapsingToolbarLayout) findViewById(R.id.project_collapsing);
        colapse.setTitle(project.getTitle());
        colapse.setCollapsedTitleTextColor(Color.parseColor("#C5EFF7")); // Sets the color of the title when appbar is collapsed
        colapse.setCollapsedTitleTypeface(Typeface.create((Typeface)null, Typeface.BOLD));
        colapse.setExpandedTitleColor(Color.parseColor(COLOR_FULLY_TRANSPARENT)); // "No title" when the image is not scrolled

        /**** Settings of the textViews ****/

        TextView content = (TextView) findViewById(R.id.project_content);
        content.setText(project.getContent());

        TextView participants = (TextView) findViewById(R.id.project_participants);
        participants.setText("Participants : " + project.getParticipants());

        /**** Image settings ****/

        ImageView image = (ImageView) findViewById(R.id.project_image);

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
