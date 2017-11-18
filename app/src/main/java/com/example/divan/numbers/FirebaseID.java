package com.example.divan.numbers;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Divanshu Rohatgi on 15-03-2017.
 */

public class FirebaseID extends FirebaseInstanceIdService {

    private static final String TAG = "MyAndroidFCMIIDService";

    @Override
    public void onTokenRefresh() {
        //Get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
    private void sendRegistrationToServer(String token) {
        //Implement this method if you want to store the token on your server
    }
}