package com.example.simpleasynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Random;
import android.widget.ProgressBar;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mTextView2;
//    private ProgressBar progressBar;

//    SimpleAsyncTask(TextView tv, ProgressBar pb) {
    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
        mTextView2 = new WeakReference<>(tv);
        //this.progressBar = pb;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
//        this.progressBar.setMax(n);
        int s = n * 200;
            try {
                for (int count = 1; count <= n; count++){
                    Thread.sleep(s);
                    publishProgress(count);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onProgressUpdate(Integer... value) {
        mTextView2.get().setText("Sleeping for..."+ value[0] + " milliseconds!");
        //progressBar.setProgress(value[0]);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        //progressBar.setVisibility(View.GONE);
    }
}