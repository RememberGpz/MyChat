package com.mychat.guopeizhen.mychat.ui.activity;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.ui.base.BaseActivity;
import com.mychat.guopeizhen.mychat.ui.presenter.RegisterPresenter;
import com.mychat.guopeizhen.mychat.ui.view.RegisterView;
import com.mychat.guopeizhen.mychat.util.UIUtil;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/16.
 */

public class RegisterActivity extends BaseActivity<RegisterView,RegisterPresenter> implements RegisterView {
    @Bind(R.id.etNickName)
    EditText etNickName;
    @Bind(R.id.vNick)
    EditText vNick;

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.vPhone)
    View vPhone;

    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.ivSeePwd)
    ImageView ivSeePwd;
    @Bind(R.id.vPassword)
    View vPassword;

    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.btnGetCode)
    Button btnGetCode;
    @Bind(R.id.vCode)
    View vCode;

    @Bind(R.id.btnRegister)
    Button btnRegister;

    private boolean canSee = false;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            btnRegister.setEnabled(canRegister());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void init() {
        setToolbarTitle("注册");
        super.init();
    }

    @Override
    public void initDataAndEvent() {
        etNickName.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etCode.addTextChangedListener(textWatcher);
        etPhone.addTextChangedListener(textWatcher);

        etNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    vNick.setBackgroundColor(UIUtil.getColor(R.color.green0));
                }else {
                    vNick.setBackgroundColor(UIUtil.getColor(R.color.line));
                }
            }
        });

        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    vPhone.setBackgroundColor(UIUtil.getColor(R.color.green0));
                }else {
                    vPhone.setBackgroundColor(UIUtil.getColor(R.color.line));
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    vPassword.setBackgroundColor(UIUtil.getColor(R.color.green0));
                }else {
                    vPassword.setBackgroundColor(UIUtil.getColor(R.color.line));
                }
            }
        });

        etCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    vCode.setBackgroundColor(UIUtil.getColor(R.color.green0));
                }else {
                    vCode.setBackgroundColor(UIUtil.getColor(R.color.line));
                }
            }
        });

        ivSeePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!canSee){
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendCode();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnRegister.isEnabled()){
                    mPresenter.register();
                }
            }
        });

        super.initDataAndEvent();
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }

    private boolean canRegister(){
        int nickNameLength = etNickName.getText().toString().trim().length();
        int pwdLength = etPassword.getText().toString().trim().length();
        int phoneLength = etPhone.getText().toString().trim().length();
        int codeLength = etCode.getText().toString().trim().length();
        if (nickNameLength > 0 && pwdLength > 0 && phoneLength > 0 && codeLength > 0) {
            return true;
        }
        return false;
    }

    @Override
    public EditText getEtNick() {
        return etNickName;
    }

    @Override
    public EditText getEtPhone() {
        return etPhone;
    }

    @Override
    public EditText getEtPassword() {
        return etPassword;
    }

    @Override
    public EditText getEtCode() {
        return etCode;
    }

    @Override
    public Button getBtnCode() {
        return btnGetCode;
    }
}
