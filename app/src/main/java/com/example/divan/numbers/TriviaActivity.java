package com.example.divan.numbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.Random;

public class TriviaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        final TextView mTextView = (TextView) findViewById(R.id.text);
        final EditText mEditText = (EditText) findViewById(R.id.edit);
        final Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String editTextValue = mEditText.getText().toString();


                if (editTextValue.isEmpty()) {


                    //Toast.makeText(getApplicationContext(),"Please Enter number ",Toast.LENGTH_SHORT).show();

                    Random rand = new Random();
                    int number = rand.nextInt();
                    String myString = String.valueOf(number);

                    //Even if the editText is empty will generate random facts.
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                    String url = "http://numbersapi.com/" + number + "/trivia";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Displaying the response string.
                                    mTextView.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("Uh oh, we don't understand that or Check your Connection :( ");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);


                } else {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                    String url = "http://numbersapi.com/" + editTextValue + "/trivia";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Displaying the response string.
                                    mTextView.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("Uh oh, we don't understand that or Check your Connection :( ");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);


                }

            }
        });



        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(),"Bad Fact",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(),"Good Fact",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(),"Great Fact",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(),"Okay Fact",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(),"Terrible Fact",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        final DayNightSwitch dayNightSwitch = (DayNightSwitch)findViewById(R.id.day_night_switch);
        final View view = findViewById(R.id.view);

        dayNightSwitch.setDuration(450);
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean isNight) {
                if(isNight){
                    Toast.makeText(getApplicationContext(),"Night mode Selected",Toast.LENGTH_SHORT).show();
                    view.setAlpha(1f);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Day mode Selected",Toast.LENGTH_SHORT).show();
                    view.setAlpha(0f);
                }

            }
        });


    }
}
