package com.mychat.guopeizhen.mychat.util;

import android.content.Context;
import android.content.res.Resources;

import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.app.baseApp.BaseApp;

/**
 * Created by Administrator on 2017/10/16.
 */

public class UIUtil {

    public static Context getContext(){
        return BaseApp.getContext();
    }

    public static Resources getResource(){
        return getContext().getResources();
    }

    public static int getColor(int color){
        return getResource().getColor(color);
    }

}
