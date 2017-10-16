package com.mychat.guopeizhen.mychat.util;

import android.widget.Toast;

import com.mychat.guopeizhen.mychat.app.baseApp.BaseApp;

/**
 * Created by Remember on 2017/10/16.
 */

public class ToastUtil {

    public static void shortToast(String content){
        Toast.makeText(BaseApp.getContext(),content,Toast.LENGTH_SHORT).show();
    }
    public static void longToast(String content){
        Toast.makeText(BaseApp.getContext(),content,Toast.LENGTH_LONG).show();
    }
}
