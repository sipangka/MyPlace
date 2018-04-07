package com.assignment.myplace.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.assignment.myplace.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private View view;
    private Context context;

    public CustomInfoWindowAdapter(Context context) {
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.custom_info_window,
                null);

        this.context = context;
    }

    @Override
    public View getInfoContents(Marker marker) {

       /* if (MapsDragableActivity.this.marker != null
                && MapsDragableActivity.this.marker.isInfoWindowShown()) {
            MapsDragableActivity.this.marker.hideInfoWindow();
            MapsDragableActivity.this.marker.showInfoWindow();
        }*/
        return null;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        //MapsDragableActivity.this.marker = marker;

          /*  buttonSelect = ((Button) view.findViewById(R.id.buttonSelect));
            buttonSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MapsDragableActivity.this, marker.getPosition().toString(), Toast.LENGTH_LONG).show();
                }
            });*/

        return view;
    }
}