package com.unrealandroid.polyapp.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unrealandroid.polyapp.R;


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

        GoogleMap googleMap;
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        final LatLng TutorialsPoint = new LatLng(21 , 57);
    }
}
