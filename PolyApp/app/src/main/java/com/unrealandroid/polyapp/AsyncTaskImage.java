package com.unrealandroid.polyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin on 02/04/2016.
 */
public class AsyncTaskImage extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public AsyncTaskImage (ImageView imageView){
        this.imageView = imageView;
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
        }
    }
}
