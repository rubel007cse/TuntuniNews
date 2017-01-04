package com.mrubel.tuntuninews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.mylist);
        b = (Button) findViewById(R.id.addingnewnews);

        fetchingData();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddingNews.class));
            }
        });

    }



    void fetchingData(){


        String myURL = "http://mrubel.com/tuntuninews/api/gettingnews.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            final String[] news_title = new String[response.length()];
            final String[] news_detail = new String[response.length()];
            final String[] news_time = new String[response.length()];

                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);
                        news_title[i] = jsonObject.getString("title");
                        news_detail[i] = jsonObject.getString("news");
                        news_time[i] = jsonObject.getString("time");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                lv.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.mylistview, R.id.textviewforlist, news_title));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, Details.class);
                        intent.putExtra("MyTITLE", news_title[position]);
                        intent.putExtra("MyNEWS", news_detail[position]);
                        intent.putExtra("MyTime", news_time[position]);
                        startActivity(intent);

                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });


        com.mrubel.tuntuninews.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "Data Loaded Successfully!", Toast.LENGTH_SHORT).show();

    }




}
