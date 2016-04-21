package com.unrealandroid.polyapp.event;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
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
import java.util.Calendar;
import java.util.GregorianCalendar;


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
        final Event event = intent.getParcelableExtra("Event");
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


        final NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.eventScrollview);
        TextView title = (TextView) findViewById(R.id.titleSingleEvent);
        TextView content = (TextView) findViewById(R.id.contentSingleEvent);
        ImageView imageView = (ImageView) findViewById(R.id.imageSingleEvent);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.add_calendar);
        //floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(R.color.colorPrimary));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, event.getTitle());
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, event.getContent());

                GregorianCalendar calDate = new GregorianCalendar(2016, 4, 21);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(event.getTitle());
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00000000"));
        //collapsingToolbar.setCollapsedTitleTextColor(R.color.white);
        collapsingToolbar.setCollapsedTitleTextColor(Color.parseColor("#C5EFF7"));


        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
        //AsyncTaskImage asyncTaskImage = new AsyncTaskImage(imageView);
        //asyncTaskImage.execute(event.getImagePath());
        title.setText(event.getTitle());
        title.setTypeface(null, Typeface.BOLD);
        content.setText(event.getContent());



        /******* GOOGLE MAP *******/

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
