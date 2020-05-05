package com.example.moviefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SideActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        Intent intent = getIntent();
        Intent Intent2 = new Intent(this, MainActivity.class);
        String title = intent.getStringExtra("Movie");
        TextView titleLabel = findViewById(R.id.Title);
        TextView yearLabel = findViewById(R.id.Year);
        TextView genreLabel = findViewById(R.id.Genre);
        TextView plotLabel = findViewById(R.id.Plot);
        TextView runtimeLabel = findViewById(R.id.RunTime);
        TextView ratingLabel = findViewById(R.id.Rating);
        String[] arr = new String[0];
        if (title != null) {
            arr = title.split(" ");
        }


        String extra = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                extra = arr[i];
            } else {
                extra = extra + "+" + arr[i];
            }

        }
        TextView textView = findViewById(R.id.message);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.omdbapi.com/?t=" + extra + "&apikey=e34ba0ab";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url,  null,
                response -> {
                    // display response

                     try {
                         if (response.getString("Response").equals("False")) {
                             Intent2.putExtra("variable", 1);
                             startActivity(Intent2);

                         }
                     ratingLabel.setText("Rating: " + response.getString("imdbRating"));
                     runtimeLabel.setText("Runtime: " + response.getString("Runtime"));
                     plotLabel.setText(response.getString("Plot"));
                     yearLabel.setText("Year: " + response.getString("Year"));
                     genreLabel.setText(response.getString("Genre"));
                     titleLabel.setText(response.getString("Title"));
                     } catch(JSONException e){
                     e.printStackTrace();
                     }
                }, error -> startActivity(Intent2));

        // add it to the RequestQueue
        MySingleton.getInstance(this).addToRequestQueue(getRequest);
    }

}

