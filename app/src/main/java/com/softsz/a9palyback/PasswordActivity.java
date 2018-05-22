package com.softsz.a9palyback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softsz.a9palyback.BaseActivity;
import com.softsz.a9palyback.ModifyPasswordFragment;
import com.softsz.a9palyback.R;

public class PasswordActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = "PasswordActivity";
    TextView confirmBtn = null;
    EditText passwordEt = null;
    private String defaultPassword = "888888";
    SharedPreferences sharedPreferences = null;
    String myPwd = null;

    @Override
    void initViewRes() {
        sharedPreferences = getSharedPreferences(ModifyPasswordFragment.PWD_SP_NAME, Context.MODE_PRIVATE);
        myPwd = sharedPreferences.getString(ModifyPasswordFragment.MY_PASSWORD, null);
        confirmBtn = (TextView) findViewById(R.id.confirm_view);
        confirmBtn.setOnClickListener(this);
        passwordEt = (EditText) findViewById(R.id.password_view);

    }


    @Override
    void setMyContentView() {
        setContentView(R.layout.password_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent scanIntent = new Intent(this, ScanFileService.class);
        startService(scanIntent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.confirm_view:
                if (passwordEt.getText() != null) {
                    String inputPwd = passwordEt.getText().toString();
                    passwordEt.setText("");
                    if (myPwd != null) {
                        if (!myPwd.trim().equals("")) {
                            if (inputPwd.equals(myPwd)) {
                                startActivity(new Intent(this, MediaClassActivity.class));
                                finish();
                            }
                        }
                    } else if (inputPwd.equals(defaultPassword)) {
                        startActivity(new Intent(this, MediaClassActivity.class));
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        passwordEt.setText("");
    }
}
