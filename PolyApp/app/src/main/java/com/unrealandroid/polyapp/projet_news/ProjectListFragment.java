package com.unrealandroid.polyapp.projet_news;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;

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
        View rootView = inflater.inflate(R.layout.fragment_project_list, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            ProjectCustomAdapter projectCustomAdapter = new ProjectCustomAdapter(getActivity(), 0, dbHelper.getAllProject());
            ListView listView = (ListView) getView().findViewById(R.id.projectList);
            listView.setAdapter(projectCustomAdapter);
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
