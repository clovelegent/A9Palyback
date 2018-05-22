package com.softsz.a9palyback;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.softsz.a9palyback.BaseActivity;
import com.softsz.a9palyback.ModifyMediaSettingFragment;
import com.softsz.a9palyback.ModifyPasswordFragment;
import com.softsz.a9palyback.R;

public class SettingsActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "SettingsActivity";

    LinearLayout pwdll;
    LinearLayout mediall;
    FragmentManager fragmentManager;
    ModifyPasswordFragment mpFragment;
    ModifyMediaSettingFragment mmsFragment;

    @Override
    void initViewRes() {
        fragmentManager = getFragmentManager();
        pwdll = (LinearLayout) findViewById(R.id.setting_pwd_ll);
        mediall = (LinearLayout) findViewById(R.id.setting_media_ll);
        pwdll.setOnClickListener(this);
        mediall.setOnClickListener(this);
    }

    @Override
    void setMyContentView() {
        setContentView(R.layout.settings_activity);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.setting_pwd_ll){
            mpFragment = new ModifyPasswordFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container,mpFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(v.getId() == R.id.setting_media_ll){
            mmsFragment = new ModifyMediaSettingFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container,mmsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
