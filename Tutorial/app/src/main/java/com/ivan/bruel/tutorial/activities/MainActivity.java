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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NotificationsHelper(this, "615316872340").requestPushToken(new NotificationsHelper.Callback() {
            @Override
            public void onSuccess(String token) {
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
