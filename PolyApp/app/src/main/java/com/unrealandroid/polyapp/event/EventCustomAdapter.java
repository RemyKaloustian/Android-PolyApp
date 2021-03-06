package com.unrealandroid.polyapp.event;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unrealandroid.polyapp.AsyncTaskImage;
import com.unrealandroid.polyapp.AsyncTaskImageSmart;
import com.unrealandroid.polyapp.MainActivity;
import com.unrealandroid.polyapp.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Kevin on 02/04/2016.
 */
public class EventCustomAdapter extends ArrayAdapter<Event> {

    public EventCustomAdapter(Context context, int resource, List<Event> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.event_preview, null);
        }
        Event event = getItem(position);

        TextView title = (TextView) convertView.findViewById(R.id.eventTitle);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageEvent);

        if(event.getBitmap() == null)
        {
            AsyncTaskImageSmart asyncTaskImage = new AsyncTaskImageSmart(image, event);
            asyncTaskImage.execute(event.getImagePath());
        }
        else
        {
            image.setImageBitmap(event.getBitmap());
        }
        title.setText(event.getTitle());
        title.setTypeface(null, Typeface.BOLD);

        return convertView;
    }
}
