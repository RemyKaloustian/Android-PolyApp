package com.unrealandroid.polyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

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
    private ProjectCustomAdapter adpater;
    private int position;

    public AsyncTaskImageSmart(ImageView imageView, ProjectCustomAdapter adapter, int position) {
        this.imageView = imageView;
        this.adpater = adapter;
        this.position = position;
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
            adpater.setMap(position, bitmap);
        }
    }
}
