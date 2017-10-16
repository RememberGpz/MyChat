package com.mychat.guopeizhen.mychat.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.model.cache.UserCache;
import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;
import com.mychat.guopeizhen.mychat.util.UIUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Administrator on 2017/10/13.
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.btn_login)
    Button btnLogin ;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.rlbtn)
    AutoRelativeLayout rlbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void init() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        //手机通讯录
                        Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.READ_PHONE_STATE,
                        //位置
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        //相机、麦克风
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.CAMERA,
                        //存储空间
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SETTINGS
                ).request();
        super.init();

        if (!TextUtils.isEmpty(UserCache.getToken())){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            jumpToActivity(intent);
        }

    }

    @Override
    public void initView() {
        StatusBarUtil.setColor(this, UIUtil.getColor(R.color.black0));

        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(1000);
        rlbtn.setAnimation(animation);
        super.initView();
    }

    @Override
    public void initDataAndEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(LoginActivity.class);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(RegisterActivity.class);
                finish();
            }
        });
        super.initDataAndEvent();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }
}
