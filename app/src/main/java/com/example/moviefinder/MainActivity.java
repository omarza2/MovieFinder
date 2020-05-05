package com.example.moviefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    String name;

    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = findViewById(R.id.search);
        TextView message = findViewById(R.id.message);
        Intent intent = new Intent(this, SideActivity.class);
        nameInput = findViewById(R.id.editText);

        search.setOnClickListener(unused -> {
            name = nameInput.getText().toString();
            intent.putExtra("Movie", name);
            startActivity(intent);



        });
        Intent intent1 = getIntent();
        count = intent1.getIntExtra("variable", 0);
        if (count == 1) {
            message.setVisibility(View.VISIBLE);
        }
    }

}

