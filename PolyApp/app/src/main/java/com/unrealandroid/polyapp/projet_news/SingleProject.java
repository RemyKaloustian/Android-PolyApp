package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
public class SingleProject extends AppCompatActivity implements View.OnClickListener{

    private final static String COLOR_FULLY_TRANSPARENT = "#00000000";
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_project);

        Intent intent = getIntent();
        project = intent.getParcelableExtra("Project");

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

        /**** Mail button events ****/

        FloatingActionButton mailButton = (FloatingActionButton) findViewById(R.id.project_mailButton);
        mailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.project_mailButton :
            {
                String bodyMail = new String(project.getTitle() + "\n\n" + project.getContent()
                        + "\n\n" + "Participants : " + project.getParticipants());

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("text/plain"); // MIME french definition = text/plain : données textuelles.
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarde ce projet !");
                emailIntent.putExtra(Intent.EXTRA_TEXT, bodyMail);
                emailIntent.setData(Uri.parse("mailto:")); // For an empty "send to"
                emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // To go to singleProject when the user presses back
                                                                     // instead of returning to the mail app.
                startActivity(emailIntent);

            }
        }
    }
}
