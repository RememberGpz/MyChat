package com.mychat.guopeizhen.mychat.app.baseApp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BaseApp extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
