package com.softsz.a9palyback;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

public class ScanFileService extends Service {

    private static final String TAG = "ScanFileService";
    public static final String AUTHORITY = "com.soft.recordermediaprovider.contentprovider";
    public static final Uri VIDEO_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/video");
    public static final Uri IMAGE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/image");
    public static final Uri AUDIO_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/audio");

    private ContentResolver mContentResolver = null;
    private Cursor mCursor = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ScanFileService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mContentResolver == null) {
            mContentResolver = getContentResolver();
        }
        new ScanTask().execute();

        return START_NOT_STICKY;
    }

    private class ScanTask extends AsyncTask<String, Object, Long> {

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "ScanFileService ScanTask onPreExecute");
        }

        @Override
        protected Long doInBackground(String... strings) {
            scanDBData(AUDIO_CONTENT_URI);
            scanDBData(IMAGE_CONTENT_URI);
            scanDBData(VIDEO_CONTENT_URI);
            return null;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    private void scanDBData(Uri uri) {
        mCursor = mContentResolver.query(uri, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                try {
                    String _data = mCursor.getString(mCursor.getColumnIndex("_data"));
                    File file = new File(_data);
                    //Log.d(TAG, _data+"  "+file.exists());
                    if (!file.exists()) {
                        mContentResolver.delete(uri, "_data=?", new String[]{_data});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
