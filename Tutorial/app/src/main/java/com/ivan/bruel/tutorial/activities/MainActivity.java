package com.ivan.bruel.tutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ivan.bruel.tutorial.R;
import com.ivan.bruel.tutorial.pushnotifications.NotificationsHelper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NotificationsHelper(this, "615316872340")
                .requestPushToken(new NotificationsHelper.Callback() {
                    @Override
                    public void onSuccess(String token) {
                        OkHttpClient okHttpClient = new OkHttpClient();

                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("name", "my name is...");
                            jsonObject.put("age", 23);
                            JSONObject clothing = new JSONObject();
                            clothing.put("type", "tshirt");
                            clothing.put("color", "blue");
                            jsonObject.put("clothing", clothing);
                            jsonObject.toString()
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Request request3 = new Request.Builder().url("http://myserver.com/pushtoken").post(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).build();
                        okHttpClient.newCall(request3).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {

                            }
                        });

                        Request request = new Request.Builder().url("http://myserver.com/pushtoken").get().build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {
                                if (response != null) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.body().string());
                                        boolean isValid = jsonObject.getBoolean("is_valid");
                                        String str = jsonObject.getString("name");
                                        JSONArray jsonArray = jsonObject.getJSONArray("array_of_stuff");
                                        for (int i = 0; i < jsonArray.length(); i) {
                                            JSONObject stuffJSON = jsonArray.getJSONObject(i);
                                            stuffJSON.get
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                        Request request2 = new Request.Builder().url("http://myserver.com/pushtoken").post(RequestBody.create(MediaType.parse("application/json"), "{'pushToken': '" + token + "'}")).build();
                        okHttpClient.newCall(request2).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {

                            }
                        });
                        Log.v("TOKEN", "We got a token, now we should let our server know! " + token);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.v("TOKEN", "Could not get a token :(");
                    }
                });
    }

    public void mapsClick(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void barcodeClick(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, intent);

        if (scanResult != null && scanResult.getContents() != null) {
            new AlertDialog.Builder(this).setTitle(getString(R.string.scan_barcode))
                    .setMessage(scanResult.getContents()).setCancelable(true).show();
        }
    }
}
