package com.unrealandroid.polyapp.projet_news;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Charly on 04/04/2016.
 */
public class ProjectListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ProjectCustomAdapter adapter;

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
        View rootView = inflater.inflate(R.layout.project_list, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) getView().findViewById(R.id.projectList);

        try {
            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            adapter = new ProjectCustomAdapter(getActivity(), 0, dbHelper.getAllProject());
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Project project = adapter.getItem(position);
        Intent intent = new Intent(getContext(), SingleProject.class);
        intent.putExtra("Project", project);
        startActivity(intent); // We can't pass an image through an intent !! Too big !
    }
}
