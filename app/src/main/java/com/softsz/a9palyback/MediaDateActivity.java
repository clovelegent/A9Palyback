package com.softsz.a9palyback;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MediaDateActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "MediaDateActivity";

    public static final String AUTHORITY = "com.soft.recordermediaprovider.contentprovider";
    public static final Uri VIDEO_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/video");
    public static final Uri IMAGE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/image");
    public static final Uri AUDIO_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/audio");
    private Uri currentUri = AUDIO_CONTENT_URI;

    private int Tag; //audio model
    public static final int IMAGEMODEL = 1;
    public static final int VIDEOMODE = 2;
    public static final int AUDIOMODEL = 3;
    public static final int SETTINGSMODEL = 4;

    private ContentResolver mContentResolver = null;
    private Cursor mCursor = null;

    private List<String> mRecordFolder = new ArrayList<>();

    private ListView mListView = null;

    private FragmentManager mFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout framelayout;
    private MediaFileListFragment mediaFileListFragment;
    public static final String FRAGMENT_DATE_KEY = "media_date";
    public static final String FRAGMENT_TAG_KEY = "media_tag";

    private TextView emptyText;

    @Override
    void initViewRes() {
        mContentResolver = getContentResolver();
        Intent intent = getIntent();
        Tag = intent.getExtras().getInt("playback_model", AUDIOMODEL);
        String preDate = null;
        switch (Tag) {
            case VIDEOMODE:
                List<RecordVideo> mRecordVideo = new ArrayList<>();
                currentUri = VIDEO_CONTENT_URI;
                mCursor = mContentResolver.query(currentUri, null, null, null, null);
                while (mCursor.moveToNext()) {
                    String _data = mCursor.getString(mCursor.getColumnIndex("_data"));
                    String _display_name = mCursor.getString(mCursor.getColumnIndex("_display_name"));
                    int _size = mCursor.getInt(mCursor.getColumnIndex("_size"));
                    String mime_type = mCursor.getString(mCursor.getColumnIndex("mime_type"));
                    long date_added = mCursor.getLong(mCursor.getColumnIndex("date_added"));
                    String title = mCursor.getString(mCursor.getColumnIndex("title"));
                    int duration = mCursor.getInt(mCursor.getColumnIndex("duration"));
                    String resolution = mCursor.getString(mCursor.getColumnIndex("resolution"));
                    double latitude = mCursor.getDouble(mCursor.getColumnIndex("latitude"));
                    double longitude = mCursor.getDouble(mCursor.getColumnIndex("longitude"));
                    int important = mCursor.getInt(mCursor.getColumnIndex("important"));

                    mRecordVideo.add(new RecordVideo(_data, _display_name, _size, mime_type, title
                            , duration, resolution, latitude, longitude, important, date_added));
                }

                mRecordFolder.clear();
                mRecordFolder.add(getResources().getString(R.string.Camera));
                if (mRecordVideo.size() > 0) {
                    preDate = getDateString(mRecordVideo.get(0).getDateAdded());
                    StringBuilder sbVideoNames = new StringBuilder();
                    sbVideoNames.append(preDate);
                    mRecordFolder.add(preDate);
                    for (int i = 0; i < mRecordVideo.size(); i++) {
                        String nowDate = getDateString(mRecordVideo.get(i).getDateAdded());
                        if (!((sbVideoNames.toString()).contains(nowDate))) {
                            mRecordFolder.add(nowDate);
                            sbVideoNames.append(nowDate);
                        }
                    }
                }


                break;
            case IMAGEMODEL:
                List<RecordImage> mRecordImage = new ArrayList<>();
                currentUri = IMAGE_CONTENT_URI;
                mCursor = mContentResolver.query(currentUri, null, null, null, null);
                while (mCursor.moveToNext()) {
                    String _data = mCursor.getString(mCursor.getColumnIndex("_data"));
                    int _size = mCursor.getInt(mCursor.getColumnIndex("_size"));
                    String _display_name = mCursor.getString(mCursor.getColumnIndex("_display_name"));
                    String mime_type = mCursor.getString(mCursor.getColumnIndex("mime_type"));
                    String title = mCursor.getString(mCursor.getColumnIndex("title"));
                    long date_added = mCursor.getLong(mCursor.getColumnIndex("date_added"));
                    double latitude = mCursor.getDouble(mCursor.getColumnIndex("latitude"));
                    double longitude = mCursor.getDouble(mCursor.getColumnIndex("longitude"));
                    int orientation = mCursor.getInt(mCursor.getColumnIndex("orientation"));
                    int important = mCursor.getInt(mCursor.getColumnIndex("important"));

                    mRecordImage.add(new RecordImage(_data, _display_name, _size, mime_type, date_added, title, latitude,
                            longitude, orientation, important));
                }

                mRecordFolder.clear();
                mRecordFolder.add(getResources().getString(R.string.Camera));
                if (mRecordImage.size() > 0) {
                    preDate = getDateString(mRecordImage.get(0).getDateAdded());
                    StringBuilder sbImageNames = new StringBuilder();
                    sbImageNames.append(preDate);
                    mRecordFolder.add(preDate);
                    for (int i = 0; i < mRecordImage.size(); i++) {
                        String nowDate = getDateString(mRecordImage.get(i).getDateAdded());
                        if (!((sbImageNames.toString()).contains(nowDate))) {
                            mRecordFolder.add(nowDate);
                            sbImageNames.append(nowDate);
                        }
                    }
                }


                break;
            case AUDIOMODEL:
                List<RecordAudio> mRecordAudios = new ArrayList<>();
                currentUri = AUDIO_CONTENT_URI;
                mCursor = mContentResolver.query(currentUri, null, null, null, null);
                while (mCursor.moveToNext()) {
                    String data = mCursor.getString(mCursor.getColumnIndex("_data"));
                    long date_add = mCursor.getLong(mCursor.getColumnIndex("date_added"));
                    String _display_name = mCursor.getString(mCursor.getColumnIndex("_display_name"));
                    String mime_type = mCursor.getString(mCursor.getColumnIndex("mime_type"));
                    String title = mCursor.getString(mCursor.getColumnIndex("title"));
                    int duration = mCursor.getInt(mCursor.getColumnIndex("duration"));
                    int important = mCursor.getInt(mCursor.getColumnIndex("important"));
                    RecordAudio ra = new RecordAudio(data, date_add, _display_name, mime_type, title, duration, important);
                    mRecordAudios.add(ra);
                }

                if (mRecordAudios.size() > 0) {
                    preDate = getAudioDateString(mRecordAudios.get(0).getDate());
                    StringBuilder sbAudioNames = new StringBuilder();
                    sbAudioNames.append(preDate);
                    mRecordFolder.clear();
                    mRecordFolder.add(preDate);
                    for (int i = 0; i < mRecordAudios.size(); i++) {
                        String nowDate = getAudioDateString(mRecordAudios.get(i).getDate());
                        if (!((sbAudioNames.toString()).contains(nowDate))) {
                            mRecordFolder.add(nowDate);
                            sbAudioNames.append(nowDate);
                        }
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Unknow Tag:" + Tag);
        }
    }

    @Override
    void setMyContentView() {
        setContentView(R.layout.display_media_activity);
        mListView = (ListView) findViewById(R.id.display_view);
        mListView.setOnItemClickListener(this);
        emptyText = (TextView) findViewById(R.id.empty_text);
        mFragmentManager = getFragmentManager();
        mediaFileListFragment = new MediaFileListFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mRecordFolder.size()>0){
            mListView.setAdapter(new MyMediaAdapter(this, mRecordFolder));
        }else{
            emptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "media_date" + mRecordFolder.get(position) + ",tag = " + Tag);
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_DATE_KEY, mRecordFolder.get(position));
        bundle.putInt(FRAGMENT_TAG_KEY, Tag);
        mediaFileListFragment.setArguments(bundle);
        initDefaultFragment();
    }

    private void initDefaultFragment() {
        fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, mediaFileListFragment, mediaFileListFragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public String getAudioDateString(long date) {
        Date mDate = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getResources().getString(
                R.string.audio_db_title_format));
        return simpleDateFormat.format(mDate);
    }
    public String getDateString(long date) {
        Date mDate = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getResources().getString(
                R.string.db_title_format));
        return simpleDateFormat.format(mDate);
    }

    class MyMediaAdapter extends BaseAdapter {

        private Context context;
        private List mFiles;

        public MyMediaAdapter(Context context, List mFiles) {
            this.context = context;
            this.mFiles = mFiles;
        }

        @Override
        public int getCount() {
            return mFiles.size();
        }

        @Override
        public Object getItem(int position) {
            return mFiles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.dir_body_item, null);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.display_image);
                viewHolder.title = (TextView) convertView.findViewById(R.id.display_title);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.image.setImageResource(R.drawable.fm_folder);
            viewHolder.title.setText((mFiles.get(position).toString()));
            return convertView;
        }

        class ViewHolder {
            public TextView title;
            public ImageView image;
        }
    }
}
