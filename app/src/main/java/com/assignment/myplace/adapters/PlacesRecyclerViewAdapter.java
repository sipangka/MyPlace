package com.assignment.myplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.assignment.myplace.R;
import com.assignment.myplace.customviews.CenterCheckBox;
import com.assignment.myplace.persistence.Place;
import com.squareup.picasso.Picasso;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter<PlacesRecyclerViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Place item);
        void onFavoriteClick(Place item, boolean isChecked);
    }

    private List<Place> mData;
    private Context mContext;
    private final OnItemClickListener mListener;


    class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView placeImageView;
        private TextView placeNameTV;
        private TextView urlLinkTV;
        private CenterCheckBox favoriteRadio;

        ViewHolder(View view) {
            super(view);

            placeNameTV = (TextView) view.findViewById(R.id.placeNameTV);
            urlLinkTV = (TextView) view.findViewById(R.id.urlLinkTV);
            placeImageView = (CircleImageView) view.findViewById(R.id.placeImageView);
            favoriteRadio = (CenterCheckBox) view.findViewById(R.id.favoriteRadio);
        }

        public void bind(final Place item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (v.getId() != favoriteRadio.getId()){
                        listener.onItemClick(item);
                    }
                }
            });
        }
    }

    public PlacesRecyclerViewAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void setData(List<Place> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;

        layout = R.layout.recycler_view_place_item;

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Place place = mData.get(position);

        viewHolder.favoriteRadio.setOnCheckedChangeListener(null);
        viewHolder.placeNameTV.setText(String.format("%s %s", place.getName(), place.getAddress()));
        //viewHolder.urlLinkTV.setText(place.getId());
        viewHolder.favoriteRadio.setChecked(place.getFavorite());

        if (place.getIconLink() != null) {
            Picasso.with(mContext).load(place.getIconLink()).into(viewHolder.placeImageView);
        }

        viewHolder.favoriteRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.onFavoriteClick(place, isChecked);
            }
        });

        viewHolder.bind(place, mListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }


}