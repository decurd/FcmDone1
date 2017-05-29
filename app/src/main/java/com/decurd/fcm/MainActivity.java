package com.decurd.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String URL_STORE_TOKEN = "http://192.168.0.21/fcm/storeFcmToken.php";
    private BroadcastReceiver broadcastReceiver;

    private EditText editTextEmail;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTokenToServer();
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        Log.d(TAG, "Token: " + SharedPrefManager.getInstace(this).getToken());

        // 동적으로 브로드캐스트 리시버를 등록
        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
    }

    private void sendTokenToServer() {
        final String email = editTextEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
        } else {
            if (SharedPrefManager.getInstace(this).getToken() != null) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URL_STORE_TOKEN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("token", SharedPrefManager.getInstace(getApplicationContext()).getToken());
                        params.put("email", email);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(this, "토큰이 생성되지 않았습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
