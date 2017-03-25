package com.example.maxim.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity  implements OnVideoSelectedListener{

    private static final String Search = "SEARCH";
    private static final String API_KEY = "AIzaSyCLDf6Aa-caPVh-OAqgqI2GHV-gaJC77kA";
    private String keyword;
    private RecyclerView recyclerView;

    //pour verif l'url : https://developers.google.com/youtube/v3/docs/search/list
    // pour verif les cles d'api : https://console.developers.google.com/apis/credentials?project=sacred-booking-160917
    // pour la console de recherche : https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyDLf6lrgLvIj92CrkcqrZ1PpdtZRr04UPU

public static void start(Context context, String keyword)
{
    Intent intent = new Intent(context,ResultActivity.class);
    intent.putExtra("KEYWORD",keyword);
    context.startActivity(intent);
}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        keyword = getIntent().getStringExtra(Search);
        setTitle("Search results from " + keyword);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getResults(keyword);

    }

    private void getResults(String Keyword) {

        StringRequest videoRequest = new StringRequest("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResult=25&key=" + API_KEY + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    //parse data from webservice to get Video as Java object
                    Videos videos = new Gson().fromJson(response, Videos.class);
                    setAdapter(videos);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Keyword", "Error");
            }
        });

        Volley.newRequestQueue(this).add(videoRequest);
    }
    private void setAdapter(Videos videos)
    {
        VideoAdapter adapter = new VideoAdapter(videos);
        adapter.setOnVideoSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onVideoSelected(Video video) {
        ResultActivity.start(this,video.getTitle());
    }
}
