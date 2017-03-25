package com.example.maxim.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by maxim on 22/03/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private final Videos videos;
    private OnVideoSelectedListener onVideoSelectedListener;

    public VideoAdapter(Videos videos)
    {
        this.videos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_viewholder,parent,false);
        return  new VideoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(VideoViewHolder holder,int position)
    {
        holder.bind(videos.get(position));
    }
    @Override
    public int getItemCount()
    {
        return videos != null ? videos.size() :0;
    }
    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener)
    {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}
