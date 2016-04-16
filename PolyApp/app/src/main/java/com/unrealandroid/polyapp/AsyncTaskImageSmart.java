package com.unrealandroid.polyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.unrealandroid.polyapp.event.Event;
import com.unrealandroid.polyapp.projet_news.Project;
import com.unrealandroid.polyapp.projet_news.ProjectCustomAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Charly on 10/04/2016.
 */
public class AsyncTaskImageSmart extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private Project project;
    private Event event;

    public AsyncTaskImageSmart(ImageView imageView, Project project) {
        this.imageView = imageView;
        this.project = project;
    }

    public AsyncTaskImageSmart(ImageView imageView, Event event){
        this.imageView = imageView;
        this.event = event;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try {
            URL urlImage = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) urlImage.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            if(bmp != null)
                return bmp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(imageView != null){
            imageView.setImageBitmap(bitmap);
            if(project != null)
                project.setBitmap(bitmap);
            else
                event.setBitmap(bitmap);
        }
    }
}
