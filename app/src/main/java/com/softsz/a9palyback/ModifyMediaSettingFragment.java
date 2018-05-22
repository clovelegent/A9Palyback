package com.softsz.a9palyback;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.softsz.a9palyback.R;

public class ModifyMediaSettingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static final String TAG = "MediaSettingFragment";
    FrameLayout frameLayout;
    Context context;
    Resources mResources;
    public static final String AUTHORITY = "com.soft.recordermediaprovider.contentprovider";
    public static final Uri MEDIA_SETTINGS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/media_settings");
    ContentResolver mContextResolver = null;

    public static final String MEDIA_SETTINGS_PICTURE_SIZE = "media_settings_picture_size";
    public static final String MEDIA_SETTINGS_CAPTURE_AUTO_UPLOAD = "media_settings_capture_auto_upload";
    public static final String MEDIA_SETTINGS_VIDEO_RESOLUTION = "media_settings_video_resolution";
    public static final String MEDIA_SETTINGS_PRERE_RECORD_TIME = "media_settings_prere_record_time";
    public static final String MEDIA_SETTINGS_DELAY_RECORD_TIME = "media_settings_delay_record_time";
    public static final String MEDIA_SETTINGS_VIDEO_SUBSECTION = "media_settings_video_subsection";
    public static final String MEDIA_SETTINGS_INFRARED_LED = "media_settings_infrared_led";
    public static final String MEDIA_SETTINGS_FLOATING_WINDOW = "media_settings_floating_window";

    private ListPreference pictureSize_pref;
    private ListPreference videoResolution_pref;
    private ListPreference videoPreDo_pref;
    private ListPreference videoDelayDo_pref;
    private ListPreference videoSubsection_pref;
    private ListPreference infraredLed_pref;
    private CheckBoxPreference pictureAutoUpload_pref;
    private CheckBoxPreference videoFloatWindow_pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        frameLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPassword));
        frameLayout.setVisibility(View.VISIBLE);
        addPreferencesFromResource(R.xml.pref_media_setting);
        mResources = context.getResources();
        mContextResolver = context.getContentResolver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pictureSize_pref = (ListPreference) findPreference(getPrefKey(R.string.key_picture_size));
        videoResolution_pref = (ListPreference) findPreference(getPrefKey(R.string.key_video_resolution));
        videoPreDo_pref = (ListPreference) findPreference(getPrefKey(R.string.key_video_pre_do));
        videoDelayDo_pref = (ListPreference) findPreference(getPrefKey(R.string.key_video_delay_do));
        videoSubsection_pref = (ListPreference) findPreference(getPrefKey(R.string.key_video_subsection));
        infraredLed_pref = (ListPreference) findPreference(getPrefKey(R.string.key_infrared_led));
        pictureAutoUpload_pref = (CheckBoxPreference) findPreference(getPrefKey(R.string.key_picture_auto_upload));
        videoFloatWindow_pref = (CheckBoxPreference) findPreference(getPrefKey(R.string.key_video_float_window));

        pictureSize_pref.setOnPreferenceChangeListener(this);
        videoResolution_pref.setOnPreferenceChangeListener(this);
        videoPreDo_pref.setOnPreferenceChangeListener(this);
        videoDelayDo_pref.setOnPreferenceChangeListener(this);
        videoSubsection_pref.setOnPreferenceChangeListener(this);
        infraredLed_pref.setOnPreferenceChangeListener(this);
        pictureAutoUpload_pref.setOnPreferenceChangeListener(this);
        videoFloatWindow_pref.setOnPreferenceChangeListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        frameLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String preferenceKey = preference.getKey();

        //Log.d(TAG, "preferenceKey = " + preferenceKey + ",newValue = " + newValue);

        if (preferenceKey.equals(getPrefKey(R.string.key_picture_size))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_PICTURE_SIZE, (String) newValue);
            return true;
        } else if (preferenceKey.equals(getPrefKey(R.string.key_picture_auto_upload))) {

            if ((boolean) newValue) {
                updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_CAPTURE_AUTO_UPLOAD, "1");
                return true;
            } else {
                updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_CAPTURE_AUTO_UPLOAD, "0");
                return true;
            }

        } else if (preferenceKey.equals(getPrefKey(R.string.key_video_resolution))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_VIDEO_RESOLUTION, (String) newValue);
            return true;
        } else if (preferenceKey.equals(getPrefKey(R.string.key_video_pre_do))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_PRERE_RECORD_TIME, (String) newValue);
            return true;
        } else if (preferenceKey.equals(getPrefKey(R.string.key_video_delay_do))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_DELAY_RECORD_TIME, (String) newValue);
            return true;
        } else if (preferenceKey.equals(getPrefKey(R.string.key_video_subsection))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_VIDEO_SUBSECTION, (String) newValue);
            return true;
        } else if (preferenceKey.equals(getPrefKey(R.string.key_video_float_window))) {

            if ((boolean) newValue) {
                updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_FLOATING_WINDOW, "1");
                return true;
            } else {
                updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_FLOATING_WINDOW, "0");
                return true;
            }

        } else if (preferenceKey.equals(getPrefKey(R.string.key_infrared_led))) {
            updateValue(MEDIA_SETTINGS_CONTENT_URI, MEDIA_SETTINGS_INFRARED_LED, (String) newValue);
            return true;
        }
        return false;
    }

    public String getPrefKey(int resID) {
        return mResources.getString(resID);
    }

    public void updateValue(Uri uri, String mName, String mValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", mValue);
        mContextResolver.update(uri, contentValues, "name=?", new String[]{mName});
    }

}
