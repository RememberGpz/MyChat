package com.mychat.guopeizhen.mychat.ui.view;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface RegisterView {
    EditText getEtNick();
    EditText getEtPhone();
    EditText getEtPassword();
    EditText getEtCode();
    Button getBtnCode();
}

