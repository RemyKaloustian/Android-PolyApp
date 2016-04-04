package com.unrealandroid.polyapp.projet_news;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;
import com.unrealandroid.polyapp.event.EventCustomAdapter;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Charly on 04/04/2016.
 */
public class ProjectListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ProjectListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProjectListFragment newInstance(int sectionNumber) {
        ProjectListFragment fragment = new ProjectListFragment();
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
            //ListView listView = (ListView) getView().findViewById(R.id.gridevent);
            //listView.setAdapter(eventCustomAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
