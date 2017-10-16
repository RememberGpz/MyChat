package com.mychat.guopeizhen.mychat.ui.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;
import com.mychat.guopeizhen.mychat.ui.view.RegisterView;
import com.mychat.guopeizhen.mychat.util.RegularUtil;
import com.mychat.guopeizhen.mychat.util.ToastUtil;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private Context context;


    /**
     * @param mContext
     */
    public RegisterPresenter(BaseActivity mContext) {
        super(mContext);
        this.context = mContext;
    }

    /**
     * 发送验证码
     */
    public void sendCode(){
        String phone = getView().getEtPhone().getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            ToastUtil.shortToast("请输入手机号码");
        }
        if (!RegularUtil.isMobile(phone)){
            ToastUtil.shortToast("手机号码格式不正确");
        }
        mContext.showWaitingDialog("请稍后...");

    }


    public void register(){

    }
}
