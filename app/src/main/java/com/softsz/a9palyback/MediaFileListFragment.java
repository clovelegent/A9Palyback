package com.softsz.a9palyback;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.softsz.a9palyback.MediaDateActivity.AUDIOMODEL;
import static com.softsz.a9palyback.MediaDateActivity.AUDIO_CONTENT_URI;
import static com.softsz.a9palyback.MediaDateActivity.IMAGEMODEL;
import static com.softsz.a9palyback.MediaDateActivity.IMAGE_CONTENT_URI;
import static com.softsz.a9palyback.MediaDateActivity.VIDEOMODE;
import static com.softsz.a9palyback.MediaDateActivity.VIDEO_CONTENT_URI;

public class MediaFileListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "MediaFileListFragment";
    public static final String KEY_IMAGE_BROWSE = "key_image_browse";

    private Context context;

    private String mediaDate = null;
    private int mediaTag = 3;

    private ContentResolver mContentResolver = null;
    private Cursor mCursor = null;

    private static List mRecords = null;

    private ListView mListView = null;

    public static final String MEDIA_DATA_KEY = "media_data_key";
    public static final String MEDIA_DURATION_KEY = "media_duration_key";
    public static final String MEDIA_DISPLAYNAME_KEY = "media_display_name_key";

    private boolean isCameraMedia = false;

    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Bundle b = getArguments();
        mediaDate = b.getString(MediaDateActivity.FRAGMENT_DATE_KEY);
        mediaTag = b.getInt(MediaDateActivity.FRAGMENT_TAG_KEY);
        Log.d(TAG, "mediaDate = " + mediaDate + ",mediaTag = " + mediaTag);

        fragmentManager = getFragmentManager();

        mContentResolver = context.getContentResolver();

        switch (mediaTag) {
            case VIDEOMODE:
                if (!mediaDate.equals(context.getResources().getString(R.string.Camera))) {
                    isCameraMedia = false;
                    mRecords = new ArrayList<RecordVideo>();
                    mRecords.clear();
                    mCursor = mContentResolver.query(VIDEO_CONTENT_URI, null, null, null, null);
                    while (mCursor.moveToNext()) {
                        String _data = mCursor.getString(mCursor.getColumnIndex("_data"));
                        if (_data.contains(mediaDate)) {
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
                            mRecords.add(new RecordVideo(_data, _display_name, _size, mime_type, title
                                    , duration, resolution, latitude, longitude, important, date_added));
                        }
                    }
                } else {
                    isCameraMedia = true;
                    mRecords = new ArrayList<CameraMedia>();
                    mRecords.clear();
                    Uri mVideoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    mCursor = mContentResolver.query(mVideoUri, null, null, null, MediaStore.Images.Media.DATE_MODIFIED);
                    while (mCursor.moveToNext()) {
                        String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        String name = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        mRecords.add(new CameraMedia(path, name));
                    }
                }

                mCursor.close();
                break;
            case IMAGEMODEL:
                if (!mediaDate.equals(context.getResources().getString(R.string.Camera))) {
                    isCameraMedia = false;
                    mRecords = new ArrayList<RecordImage>();
                    mRecords.clear();
                    mCursor = mContentResolver.query(IMAGE_CONTENT_URI, null, null, null, null);
                    while (mCursor.moveToNext()) {
                        String _data = mCursor.getString(mCursor.getColumnIndex("_data"));
                        if (_data.contains(mediaDate)) {
                            int _size = mCursor.getInt(mCursor.getColumnIndex("_size"));
                            String _display_name = mCursor.getString(mCursor.getColumnIndex("_display_name"));
                            String mime_type = mCursor.getString(mCursor.getColumnIndex("mime_type"));
                            String title = mCursor.getString(mCursor.getColumnIndex("title"));
                            long date_added = mCursor.getLong(mCursor.getColumnIndex("date_added"));
                            double latitude = mCursor.getDouble(mCursor.getColumnIndex("latitude"));
                            double longitude = mCursor.getDouble(mCursor.getColumnIndex("longitude"));
                            int orientation = mCursor.getInt(mCursor.getColumnIndex("orientation"));
                            int important = mCursor.getInt(mCursor.getColumnIndex("important"));
                            mRecords.add(new RecordImage(_data, _display_name, _size, mime_type, date_added, title, latitude,
                                    longitude, orientation, important));
                        }
                    }
                } else {
                    isCameraMedia = true;
                    mRecords = new ArrayList<String>();
                    mRecords.clear();
                    Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    mCursor = mContentResolver.query(mImageUri, null, null, null, MediaStore.Images.Media.DATE_MODIFIED);
                    while (mCursor.moveToNext()) {
                        String tPath = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        String tName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        mRecords.add(new CameraMedia(tPath, tName));
                    }
                }
                mCursor.close();
                break;
            case AUDIOMODEL:
                isCameraMedia = false;
                mRecords = new ArrayList<RecordAudio>();
                mRecords.clear();
                mCursor = mContentResolver.query(AUDIO_CONTENT_URI, null, null, null, null);
                while (mCursor.moveToNext()) {
                    String data = mCursor.getString(mCursor.getColumnIndex("_data"));
                    Log.d(TAG, data);
                    if (data.contains(mediaDate)) {
                        long date_add = mCursor.getLong(mCursor.getColumnIndex("date_added"));
                        String _display_name = mCursor.getString(mCursor.getColumnIndex("_display_name"));
                        String mime_type = mCursor.getString(mCursor.getColumnIndex("mime_type"));
                        String title = mCursor.getString(mCursor.getColumnIndex("title"));
                        int duration = mCursor.getInt(mCursor.getColumnIndex("duration"));
                        int important = mCursor.getInt(mCursor.getColumnIndex("important"));
                        RecordAudio ra = new RecordAudio(data, date_add, _display_name, mime_type, title, duration, important);
                        mRecords.add(ra);
                    }
                }
                mCursor.close();
                break;
            default:
                throw new IllegalArgumentException("Unknow Tag:" + mediaTag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mediaListfragment = inflater.inflate(R.layout.media_list_fragment, null);
        mListView = (ListView) mediaListfragment.findViewById(R.id.media_list_view);
        mListView.setAdapter(new MyMediaAdapter(context, mRecords));
        mListView.setOnItemClickListener(this);
        return mediaListfragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mediaTag == 3)/*audio list*/ {
            AudioPlayFragment audioPlayFragment = new AudioPlayFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MEDIA_DATA_KEY, ((RecordAudio) mRecords.get(position)).getData());
            bundle.putInt(MEDIA_DURATION_KEY, (int) ((RecordAudio) mRecords.get(position)).getDuration());
            bundle.putString(MEDIA_DISPLAYNAME_KEY, ((RecordAudio) mRecords.get(position)).getDisplayName());

            audioPlayFragment.setArguments(bundle);
            audioPlayFragment.setCancelable(true);
            audioPlayFragment.show(getFragmentManager(), "AudioPlayFragment");
        } else if (mediaTag == 1)/*image list*/ {
            /*Bundle bundle = new Bundle();
            if (isCameraMedia) {
                bundle.putString(KEY_IMAGE_BROWSE, ((CameraMedia)mRecords.get(position)).getData());
                //bundle.putInt(KEY_IMAGE_BROWSE,position);
            } else {
                bundle.putString(KEY_IMAGE_BROWSE, ((RecordImage)mRecords.get(position)).getData());
                //bundle.putInt(KEY_IMAGE_BROWSE,position);
            }
            ImageBrowserFragment imageBrowserFragment = new ImageBrowserFragment();
            imageBrowserFragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.media_list_fl, imageBrowserFragment, imageBrowserFragment.getClass().getName());
            transaction.addToBackStack(null);
            transaction.commit();*/

            String path;
            if (isCameraMedia) {
                path = ((CameraMedia) mRecords.get(position)).getData();
            } else {
                path = ((RecordImage) mRecords.get(position)).getData();
            }
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            startActivity(intent);

        } else if (mediaTag == 2) /*video list*/ {
            String path;
            if (isCameraMedia) {
                path = ((CameraMedia) mRecords.get(position)).getData();
            } else {
                path = ((RecordVideo) mRecords.get(position)).getData();
            }
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(Uri.fromFile(new File(path)), "video/*");
            startActivity(intent);
        }
    }

    public static List getmRecords(){
        return mRecords;
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


            switch (mediaTag) {
                case 1:
                    viewHolder.image.setImageResource(R.drawable.fm_picture);
                    if (isCameraMedia) {
                        viewHolder.title.setText(((CameraMedia) mFiles.get(position)).getDisplayName());
                    } else {
                        viewHolder.title.setText(((RecordImage) (mFiles.get(position))).getDisplayName());
                    }
                    break;
                case 2:
                    viewHolder.image.setImageResource(R.drawable.fm_video);
                    if (isCameraMedia) {
                        viewHolder.title.setText(((CameraMedia) mFiles.get(position)).getDisplayName());
                    } else {
                        viewHolder.title.setText(((RecordVideo) (mFiles.get(position))).getDisplayName());
                    }
                    break;
                case 3:
                    viewHolder.title.setText(((RecordAudio) (mFiles.get(position))).getDisplayName());
                    viewHolder.image.setImageResource(R.drawable.fm_audio);
                    break;
            }

            return convertView;
        }

        class ViewHolder {
            public TextView title;
            public ImageView image;
        }
    }

}
