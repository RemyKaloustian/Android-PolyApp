package com.unrealandroid.polyapp.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Kevin on 02/04/2016.
 */
public class EventListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

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
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            EventCustomAdapter eventCustomAdapter = new EventCustomAdapter(getActivity(), 0, dbHelper.getAllEvent());
            GridView gridView = (GridView) getView().findViewById(R.id.gridevent);
            gridView.setAdapter(eventCustomAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
