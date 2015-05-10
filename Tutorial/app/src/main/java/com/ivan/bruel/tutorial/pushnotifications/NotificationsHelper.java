package com.ivan.bruel.tutorial.pushnotifications;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ivan.bruel.tutorial.helpers.SimpleAsyncTask;

import java.io.IOException;

public class NotificationsHelper {

    private Context mContext;
    private String mGCMSenderId;

    public NotificationsHelper(Context context, String gcmSenderId) {
        mContext = context;
        mGCMSenderId = gcmSenderId;
    }

    private boolean hasGooglePlayServices() {
        try {
            int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
            return resultCode == ConnectionResult.SUCCESS;
        } catch (Error e) {
            e.printStackTrace();
        }
        return false;
    }

    public void requestPushToken(final Callback callback) {
        if (hasGooglePlayServices()) {
            new SimpleAsyncTask(new Runnable() {
                @Override
                public void run() {
                    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(mContext);
                    try {
                        String pushToken = gcm.register(mGCMSenderId);
                        if (pushToken != null) {
                            callback.onSuccess(pushToken);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onFailure(e);
                    }
                }
            }).execute();
        } else {
            callback.onFailure(new IOException("No Google Play Services"));
        }
    }

    public interface Callback {
        void onSuccess(String token);
        void onFailure(Throwable throwable);
    }
}
