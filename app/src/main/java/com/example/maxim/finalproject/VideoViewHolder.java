package com.example.maxim.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by maxim on 17/03/2017.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder{

    private TextView title;
    private ImageView minia;

    public VideoViewHolder(View itemView)
    {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        minia = (ImageView) itemView.findViewById(R.id.minia);
    }
    public void bind(Video video)
    {
        title.setText(video.getTitle());
        Picasso.with(itemView.getContext()).load(video.getUrlImage()).into(minia);
    }
}
