package com.mychat.guopeizhen.mychat.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.mychat.guopeizhen.mychat.widget.CustomDialog;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by guopeizhen on 2017/10/13.
 * 自定圆角Dialog
 *
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity  {

    protected T mPresenter;
    private CustomDialog customDialog;
    private MaterialDialog materialDialog;



}
