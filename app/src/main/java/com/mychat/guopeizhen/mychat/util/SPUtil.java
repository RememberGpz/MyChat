package com.mychat.guopeizhen.mychat.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by guopeizhen    on 2017/10/16.
 * SharePerference工具类(单例模式)
 */

public class SPUtil {
    private final String name = "common";
    private static SPUtil spUtil;
    private Context context;
    private  SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;

    private SPUtil(Context context){
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(name,Context.MODE_APPEND);
        editor = sharedPreferences.edit();
    }

    public static SPUtil getInstance (Context context){
        if (spUtil == null){
            synchronized (SPUtil.class){
                if (spUtil == null){
                    spUtil = new SPUtil(context);
                    return spUtil;
                }
            }
        }

        return spUtil;
    }


    public void putString (String key,String value){
        if (key == null){
            return;
        }
        editor.putString(key,value);
        editor.commit();
    }

    public String getString(String key,String defValue){
        if (key == null){
            return null;
        }
        return sharedPreferences.getString(key,defValue);
    }


}
