package com.unrealandroid.polyapp.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unrealandroid.polyapp.R;

/**
 * @author A.Giroud
 */
public class MenuFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public MenuFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MenuFragment newInstance(int sectionNumber) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
