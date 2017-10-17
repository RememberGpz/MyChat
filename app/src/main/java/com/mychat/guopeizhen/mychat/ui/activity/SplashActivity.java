package com.mychat.guopeizhen.mychat.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.model.cache.UserCache;
import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;
import com.mychat.guopeizhen.mychat.util.UIUtil;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.sms.BmobSMS;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Administrator on 2017/10/13.
 */

public class SplashActivity extends BaseActivity {



    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.rlbtn)
    AutoRelativeLayout rlbtn;

    @Override
    public void init() {
        BmobSMS.initialize(this,"cfbf1ec371ed270f91166ff6c59391d9");
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
        if (!TextUtils.isEmpty(UserCache.getToken())) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            jumpToActivity(intent);
        }

    }

    @Override
    public void initView() {
        StatusBarUtil.setColor(this, UIUtil.getColor(R.color.black0));

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1000);
        rlbtn.setAnimation(animation);
    }

    @Override
    public void initDataAndEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(LoginActivity.class);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(RegisterActivity.class);
            }
        });
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
