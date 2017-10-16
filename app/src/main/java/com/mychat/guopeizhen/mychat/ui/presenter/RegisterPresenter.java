package com.mychat.guopeizhen.mychat.ui.presenter;

import android.content.Context;

import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;
import com.mychat.guopeizhen.mychat.ui.view.RegisterView;
import com.mychat.guopeizhen.mychat.util.RegularUtil;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private Context context;

    public RegisterPresenter(BaseActivity mContext) {
        super(mContext);
        this.context = mContext;
    }

    public void sendCode(){
        String phone = getView().getEtPhone().getText().toString().trim();
        if (RegularUtil.isMobile(phone)){

        }
    }


    public void register(){

    }
}
