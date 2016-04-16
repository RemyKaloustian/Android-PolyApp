package com.unrealandroid.polyapp.event;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unrealandroid.polyapp.AsyncTaskImage;
import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;
import com.unrealandroid.polyapp.projet_news.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Kevin on 02/04/2016.
 */
public class SingleEvent extends AppCompatActivity {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Event event = intent.getParcelableExtra("Event");
        setContentView(R.layout.single_event);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(this.openFileInput(event.getTitle()));
            File f = new File(event.getTitle());
            f.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DBHelper dbHelper = null;
        try {
            dbHelper = new DBHelper(this);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final ScrollView scrollView = (ScrollView) findViewById(R.id.eventScrollView);
        TextView title = (TextView) findViewById(R.id.titleSingleEvent);
        TextView content = (TextView) findViewById(R.id.contentSingleEvent);
        ImageView imageView = (ImageView) findViewById(R.id.imageSingleEvent);

        setTitle(event.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
        //AsyncTaskImage asyncTaskImage = new AsyncTaskImage(imageView);
        //asyncTaskImage.execute(event.getImagePath());
        title.setText(event.getTitle());
        title.setTypeface(null, Typeface.BOLD);
        content.setText(event.getContent());


        googleMap = ((ScrollableMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        ((ScrollableMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .setListener(new ScrollableMapFragment.OnTouchListener() {
                    @Override
                    public void onTouch() {
                        scrollView.requestDisallowInterceptTouchEvent(true);
                    }
                });

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(event.getLocation_lat(), event.getLocation_long()))
                .title(event.getTitle()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(event.getLocation_lat(), event.getLocation_long()), 15.0f));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
