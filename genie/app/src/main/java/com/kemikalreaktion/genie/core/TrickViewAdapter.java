package com.kemikalreaktion.genie.core;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.Tag;
import com.kemikalreaktion.genie.util.Trick;
import com.kemikalreaktion.genie.util.TrickCatalog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * TrickViewAdapter
 * TODO: add class header comment
 */
public class TrickViewAdapter extends RecyclerView.Adapter<TrickViewAdapter.ViewHolder> {
    private static final String TAG = Tag.APP_TAG + "TrickViewAdapter";
    ArrayList<Trick> tricks;
    //ArrayList<String> tricks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.trick_img);
        }
    }

    public TrickViewAdapter() {
        tricks = TrickCatalog.getAllTricks();
        /*tricks = new ArrayList<>();
        tricks.add("http://imgur.com/zGvSDQJ");
        tricks.add("http://imgur.com/PUf63rI");
        tricks.add("http://imgur.com/fGrAGrb");
        tricks.add("http://imgur.com/VemIfxQ");
        tricks.add("http://imgur.com/EV5VY5m");
        tricks.add("http://imgur.com/WIHKX5U");
        tricks.add("http://imgur.com/pKI3a3m");
        tricks.add("http://imgur.com/pS1Q1Qa");
        tricks.add("http://imgur.com/i2Fo7PN");
        tricks.add("http://imgur.com/nenXqc0");*/
    }

    @Override
    public TrickViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trick_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position=" + position + ", img=" + tricks.get(position).getImages());

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Picasso.with(GenieManager.getInstance().getApplicationContext())
                .load(tricks.get(position).getImages())
                .resize(200,200)
                .centerCrop()
                .into(holder.img);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tricks.size();
    }
}
