package com.unrealandroid.polyapp.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.unrealandroid.polyapp.R;

import static com.unrealandroid.polyapp.R.id.menuLogo;

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

        ImageView imageView = (ImageView) getView().findViewById(menuLogo);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.polytechnice.fr"));
                startActivity(intent);
            }
        });
    }
}
