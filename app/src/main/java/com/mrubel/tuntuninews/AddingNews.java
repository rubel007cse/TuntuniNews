package com.mrubel.tuntuninews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddingNews extends AppCompatActivity {

    EditText _title, _date, _news;
    Button _submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_news);

        _title = (EditText) findViewById(R.id.news_title);
        _date = (EditText) findViewById(R.id.news_date);
        _news = (EditText) findViewById(R.id.new_details);

        _submit = (Button) findViewById(R.id.submit_news);


        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

         });
    }
}

