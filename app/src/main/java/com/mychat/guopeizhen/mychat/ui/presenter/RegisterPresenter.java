package com.mychat.guopeizhen.mychat.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.base.BasePresenter;
import com.mychat.guopeizhen.mychat.ui.view.RegisterView;
import com.mychat.guopeizhen.mychat.util.RegularUtil;
import com.mychat.guopeizhen.mychat.util.ToastUtil;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private Context context;
    private String nickName,phone,password,code;

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
    public void sendCode() {
        phone = getView().getEtPhone().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.shortToast("请输入手机号码");
            return;
        }
        if (!RegularUtil.isMobile(phone)) {
            ToastUtil.shortToast("手机号码格式不正确");
            return;

        }
        mContext.showWaitingDialog("请稍后...");
        BmobSMS.requestSMSCode(context, phone, "注册验证码", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                mContext.hideWaitingDialog();
                if (e == null) {  //验证码发送成功
                    ToastUtil.shortToast("验证码发送成功");
                    new Thread(new Timer()).start();
                }else {
                    ToastUtil.shortToast("验证码发送失败");
                    Log.e("Registerpresenter",e.getMessage());
                }
            }
        });
    }

    class Timer implements Runnable{
        private int time = 60;
        @Override
        public void run() {
            while (time > 0){
                time --;
                handler.sendEmptyMessage(time);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(0);
                }
            }
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                getView().getBtnCode().setText("发送验证码");
                getView().getBtnCode().setEnabled(true);
            }else {
                getView().getBtnCode().setText(msg.what + "s");
                getView().getBtnCode().setEnabled(false);
            }
            super.handleMessage(msg);

        }
    };

    /**
     * 注册
     */
    public void register() {
        nickName = getView().getEtNick().getText().toString();
        phone = getView().getEtPhone().getText().toString();
        password = getView().getEtPassword().getText().toString();
        code = getView().getEtCode().getText().toString();
        if (TextUtils.isEmpty(nickName)){
            ToastUtil.shortToast("请输入昵称");
            return;
        }
        if (TextUtils.isEmpty(phone)){
            ToastUtil.shortToast("请输入手机号码");
            return;
        }
        if (password.length()<6||password.length()>15){
            ToastUtil.shortToast("密码必须在6-15位之间");
            return;
        }
        if (TextUtils.isEmpty(code)){
            ToastUtil.shortToast("请输入验证码");
            return;
        }

        mContext.showWaitingDialog("请稍后...");
        BmobSMS.verifySmsCode(context, phone, code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                mContext.hideWaitingDialog();
                if (e == null){
                    ToastUtil.shortToast("注册成功");
                }else {
                    ToastUtil.shortToast("验证码错误");
                }
            }
        });
    }
}
