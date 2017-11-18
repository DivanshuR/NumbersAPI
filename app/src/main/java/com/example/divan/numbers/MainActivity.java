package com.example.divan.numbers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //marquee text
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);  // Set focus to the textview


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out. Your message goes here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
            }
        });



        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Check your internet connection.", Toast.LENGTH_LONG).show();
        }

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("ca-app-pub-3940256099942544/6300978111")
                .build();
        mAdView.loadAd(adRequest);
        mAdView = (AdView) findViewById(R.id.adView);

        mAdView.loadAd(adRequest);

        ImageButton trivia = (ImageButton) findViewById(R.id.trivia);
        ImageButton math = (ImageButton) findViewById(R.id.math);
        ImageButton date = (ImageButton) findViewById(R.id.date);
        ImageButton year = (ImageButton) findViewById(R.id.year);


        trivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MainActivity.this, TriviaActivity.class));
                } catch (ActivityNotFoundException e) {
// show message to user

                }
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MainActivity.this, MathActivity.class));
                } catch (ActivityNotFoundException e) {
// show message to user

                }
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MainActivity.this, DateActivity.class));
                } catch (ActivityNotFoundException e) {
// show message to user

                }
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MainActivity.this, YearActivity.class));

                } catch (ActivityNotFoundException e) {
// show message to user

                }

            }
        });




    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();




    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_call) {
            // Handle the camera action
            startActivity(new Intent(MainActivity.this,CallActivity.class));

        } else if (id == R.id.nav_mail) {
            startActivity(new Intent(MainActivity.this,MailActivity.class));

        } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
        }

        else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this,AboutActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
