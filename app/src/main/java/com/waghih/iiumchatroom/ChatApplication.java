package com.waghih.iiumchatroom;

import com.firebase.client.Firebase;

/**
 * Created by farooq on 27/2/2016.
 */
public class ChatApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
