package com.mychat.guopeizhen.mychat.ui.activity;

import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;

/**
 * Created by Administrator on 2017/10/16.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
}
