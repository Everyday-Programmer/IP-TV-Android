package com.example.ip_tv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StreamListActivity extends AppCompatActivity {
    String channel;
    ArrayList<Stream> streams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_list);

        channel = getIntent().getStringExtra("channel");

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        streams = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler);

        TextView noStreams = findViewById(R.id.noStreams);

        OkHttpClient client = new OkHttpClient();

        String url = "https://iptv-org.github.io/api/streams.json";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse;
                    if (response.body() != null) {
                        jsonResponse = response.body().string();
                        try {
                            JSONArray array = new JSONArray(jsonResponse);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = new JSONObject(array.getJSONObject(i).toString());
                                Stream stream = new Stream();
                                stream.setChannel(object.getString("channel"));
                                stream.setUrl(object.getString("url"));
                                if (stream.getChannel().trim().equals(channel.trim())) {
                                    streams.add(stream);
                                }
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StreamAdapter adapter = new StreamAdapter(StreamListActivity.this, streams);
                                    recyclerView.setAdapter(adapter);

                                    adapter.setOnItemClickListener(new StreamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onClick(String url) {
                                            Intent intent = new Intent(StreamListActivity.this, PlayerActivity.class);
                                            intent.putExtra("url", url);
                                            startActivity(intent);
                                        }
                                    });

                                    if (streams.isEmpty()) {
                                        noStreams.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.e("NetworkError", "Response not successful");
                }
            }
        });
    }
}