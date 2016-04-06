package com.unrealandroid.polyapp.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;

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
        int idEvent = intent.getIntExtra("IdEvent", 1);
        setContentView(R.layout.single_event);
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
        Event event = dbHelper.getEvent(idEvent);

        TextView title = (TextView) findViewById(R.id.titleSingleEvent);
        TextView content = (TextView) findViewById(R.id.contentSingleEvent);
        ImageView imageView = (ImageView) findViewById(R.id.imageSingleEvent);

        title.setText(event.getTitle());
        //content.setText(event.getContent());
        content.setText("It's doubtful you can change it on click with the default myLocation Marker. However, if you would like the app to automatically zoom " +
                "in on your location once it is found, I would check out my answer to this It's doubtful you can change it on click with the default myLocation Marker. However, if you would like" +
                " the app to automatically zoom in on your location once it is found, I would check out my answer to this ");

        GoogleMap googleMap;
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.615, 7.071944))
                .title("Hello world"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.615, 7.071944), 15.0f));
    }
}
