package com.example.ip_tv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

public class ChannelListActivity extends AppCompatActivity {
    String country;
    ArrayList<Channel> channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        country = getIntent().getStringExtra("country");

        channels = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler);

        OkHttpClient client = new OkHttpClient();

        String url = "https://iptv-org.github.io/api/channels.json";

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
                                Channel channel = new Channel();
                                channel.setName(object.getString("name"));
                                channel.setId(object.getString("id"));
                                JSONArray altNames = object.getJSONArray("alt_names");
                                StringBuilder altNamesStr = new StringBuilder();
                                for (int j = 0; j < altNames.length(); j++) {
                                    if (j == altNames.length() - 1) {
                                        altNamesStr.append(altNames.getString(j));
                                    } else {
                                        altNamesStr.append(altNames.getString(j)).append(",");
                                    }
                                }
                                channel.setAlt_names(altNamesStr.toString());
                                channel.setNetwork(object.getString("network"));
                                JSONArray owners = object.getJSONArray("owners");
                                StringBuilder ownersStr = new StringBuilder();
                                for (int j = 0; j < owners.length(); j++) {
                                    if (j == owners.length() - 1) {
                                        ownersStr.append(owners.getString(j));
                                    } else {
                                        ownersStr.append(owners.getString(j)).append(",");
                                    }
                                }
                                channel.setOwners(ownersStr.toString());
                                channel.setCountry(object.getString("country"));
                                channel.setCity("city");
                                JSONArray broadcastArea = object.getJSONArray("broadcast_area");
                                StringBuilder broadcastAreaStr = new StringBuilder();
                                for (int j = 0; j < broadcastArea.length(); j++) {
                                    if (j == broadcastArea.length() - 1) {
                                        broadcastAreaStr.append(broadcastArea.getString(j));
                                    } else {
                                        broadcastAreaStr.append(broadcastArea.getString(j)).append(",");
                                    }
                                }
                                channel.setBroadcast_area(broadcastAreaStr.toString());
                                JSONArray languages = object.getJSONArray("languages");
                                StringBuilder languagesStr = new StringBuilder();
                                for (int j = 0; j < languages.length(); j++) {
                                    if (j == languages.length() - 1) {
                                        languagesStr.append(languages.getString(j));
                                    } else {
                                        languagesStr.append(languages.getString(j)).append(",");
                                    }
                                }
                                channel.setLanguages(languagesStr.toString());
                                JSONArray categories = object.getJSONArray("categories");
                                StringBuilder categoriesStr = new StringBuilder();
                                for (int j = 0; j < categories.length(); j++) {
                                    if (j == categories.length() - 1) {
                                        categoriesStr.append(categories.getString(j));
                                    } else {
                                        categoriesStr.append(categories.getString(j)).append(",");
                                    }
                                }
                                channel.setCategories(categoriesStr.toString());
                                channel.setIs_nsfw(object.getBoolean("is_nsfw"));
                                channel.setLaunched(object.getString("launched"));
                                channel.setClosed(object.getString("closed"));
                                channel.setReplaced_by(object.getString("replaced_by"));
                                channel.setWebsite(object.getString("website"));
                                channel.setLogo(object.getString("logo"));
                                if (channel.getCountry().trim().equals(country.trim())) {
                                    channels.add(channel);
                                }
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ChannelAdapter adapter = new ChannelAdapter(ChannelListActivity.this, channels);
                                    recyclerView.setAdapter(adapter);

                                    adapter.setStreamClickListener(new ChannelAdapter.StreamClickListener() {
                                        @Override
                                        public void onClick(String channel) {
                                            Intent intent = new Intent(ChannelListActivity.this, StreamListActivity.class);
                                            intent.putExtra("channel", channel);
                                            startActivity(intent);
                                        }
                                    });
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