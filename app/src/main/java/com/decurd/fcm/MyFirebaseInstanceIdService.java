package com.decurd.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by decurd on 2017-05-29.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();

    public static final String TOKEN_BROADCAST = "myfcmtokenbroadcast";

    @Override
    public void onTokenRefresh() {  // 앱 신규설치 또는 재설치시 최초 한번만 실행됨
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // 브로드캐스트 리시버 송출
        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));

        storeToken(refreshedToken);
    }

    private void storeToken(String token) {

        SharedPrefManager.getInstace(getApplicationContext()).storeToken(token);

    }

}
