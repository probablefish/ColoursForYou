package com.example.coloursforyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import static android.view.View.GONE;

public class MainActivity extends Activity {

    private Button retryButton;
    private TextView textView;
    private Parcelable[] colours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestManager.getInstance(this);
        setContentView(R.layout.activity_main);
        welcome();
        configureRetryButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generateColoursFromRequest();
    }

    private void generateColoursFromRequest() {
        JsonArrayRequest jsonOArrayRequest = new JsonArrayRequest(
                Request.Method.GET, getString(R.string.name_generator_url), null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                colours = new Parcelable[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    Colour colour = new Colour(ColourHexGenerator.getRandomColourHex(), response.optString(i, getString(R.string.unknown_colour_name)));
                    colours[i] = colour;
                }

                startColourViewerActivity();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(R.string.request_failure_message);
                retryButton.setVisibility(View.VISIBLE);
            }
        });
        RequestManager.getInstance(this).addToRequestQueue(jsonOArrayRequest);
    }

    private void startColourViewerActivity() {
        Intent intent = new Intent(this, ColourViewerActivity.class);
        intent.putExtra("colours", colours);
        startActivity(intent);
    }

    private void welcome() {
        textView = findViewById(R.id.textView);
        textView.setText(R.string.welcome);
    }

    private void configureRetryButton() {
        retryButton = findViewById(R.id.retry_button);
        retryButton.setVisibility(GONE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateColoursFromRequest();
            }
        });
    }
}
