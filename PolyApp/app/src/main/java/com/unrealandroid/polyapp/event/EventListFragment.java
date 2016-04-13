package com.unrealandroid.polyapp.event;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;
import com.unrealandroid.polyapp.projet_news.Project;
import com.unrealandroid.polyapp.projet_news.ProjectCustomAdapter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Kevin on 02/04/2016.
 */
public class EventListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private EventCustomAdapter eventCustomAdapter;

    public EventListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EventListFragment newInstance(int sectionNumber) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            eventCustomAdapter = new EventCustomAdapter(getActivity(), 0, dbHelper.getAllEvent());
            GridView gridView = (GridView) getView().findViewById(R.id.gridevent);
            gridView.setAdapter(eventCustomAdapter);
            gridView.setOnItemClickListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Event event = eventCustomAdapter.getItem(position);
        if(event.getBitmap() != null    ) {
            Intent intent = new Intent(getContext(), SingleEvent.class);
            intent.putExtra("Event", event);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            event.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, bytes);
            FileOutputStream fo = null;
            try {
                fo = getContext().openFileOutput(event.getTitle(), Context.MODE_PRIVATE);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            startActivity(intent);
        }
    }
}
