package com.unrealandroid.polyapp.article;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.unrealandroid.polyapp.DBHelper;
import com.unrealandroid.polyapp.R;
import com.unrealandroid.polyapp.event.EventCustomAdapter;
import com.unrealandroid.polyapp.event.SingleEvent;
import com.unrealandroid.polyapp.article.SingleArticle;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Kevin on 20/04/2016.
 */
public class ArticleListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private EventCustomAdapter eventCustomAdapter;

    public ArticleListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ArticleListFragment newInstance(int sectionNumber) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.createDataBase();
            dbHelper.openDataBase();
            Button whyPolytech = (Button) getView().findViewById(R.id.why);
            whyPolytech.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SingleArticle.class);
                    intent.putExtra("id", 1);
                    startActivity(intent);
                }
            });

            Button whyIT = (Button) getView().findViewById(R.id.why_2);
            whyIT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SingleArticle.class);
                    intent.putExtra("id", 2);
                    startActivity(intent);
                }
            });

            Button whyAndroid = (Button) getView().findViewById(R.id.why_3);
            whyAndroid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SingleArticle.class);
                    intent.putExtra("id", 3);
                    startActivity(intent);
                }
            });

            //eventCustomAdapter = new EventCustomAdapter(getActivity(), 0, dbHelper.getAllEvent());
            /*GridView gridView = (GridView) getView().findViewById(R.id.gridevent);
            gridView.setAdapter(eventCustomAdapter);
            gridView.setOnItemClickListener(this);*/
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
