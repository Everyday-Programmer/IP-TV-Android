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

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        countries = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        String url = "https://iptv-org.github.io/api/countries.json";

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
                                Country country = new Country();
                                country.setName(object.getString("name"));
                                country.setCode(object.getString("code"));
                                country.setFlag(object.getString("flag"));
                                StringBuilder languages = new StringBuilder();
                                for (int j = 0; j < object.getJSONArray("languages").length(); j++) {
                                    if (j == object.getJSONArray("languages").length() - 1) {
                                        languages.append(object.getJSONArray("languages").getString(j));
                                    } else {
                                        languages.append(object.getJSONArray("languages").getString(j)).append(",");
                                    }
                                }
                                country.setLanguages(languages.toString());
                                countries.add(country);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CountryAdapter adapter = new CountryAdapter(MainActivity.this, countries);
                                    recyclerView.setAdapter(adapter);

                                    adapter.setOnItemClickListener(new CountryAdapter.OnItemClickListener() {
                                        @Override
                                        public void onClick(String country) {
                                            Intent intent = new Intent(MainActivity.this, ChannelListActivity.class);
                                            intent.putExtra("country", country);
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