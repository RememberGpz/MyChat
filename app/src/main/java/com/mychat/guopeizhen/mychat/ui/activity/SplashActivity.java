package com.mychat.guopeizhen.mychat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mychat.guopeizhen.mychat.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/13.
 */

public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.btn_login)
    Button btnLogin ;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }
}
