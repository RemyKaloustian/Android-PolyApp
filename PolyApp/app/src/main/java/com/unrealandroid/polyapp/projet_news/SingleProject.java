package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_project);

        Intent intent = getIntent();
        Project project = intent.getParcelableExtra("Project");

        TextView title = (TextView) findViewById(R.id.project_title);
        title.setText(project.getTitle());

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
