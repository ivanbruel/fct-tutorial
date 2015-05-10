package com.ivan.bruel.tutorial.helpers;

import android.os.AsyncTask;

public class SimpleAsyncTask extends AsyncTask<Void, Void, Void> {

    private Runnable mRunnable;

    public SimpleAsyncTask(Runnable runnable) {
        mRunnable = runnable;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mRunnable.run();
        return null;
    }
}