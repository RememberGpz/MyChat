package com.mychat.guopeizhen.mychat.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Administrator on 2017/10/13.\
 * 自定义圆角Dialog
 */

public class CustomDialog extends Dialog {

    public CustomDialog(Context context, View layout,int style){
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    //设置宽高的构造函数
    public CustomDialog(Context context,int width,int height,View layout,int style){
        super(context,style);
        setContentView(layout);

        //设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        float density = getDes(context);
        params.width = (int)(width*density);
        params.height = (int)(height*density);
        window.setAttributes(params);

    }

    /**
     * 获取显示密度
     *
     * @param context
     * @return
     */
    private float getDes(Context context){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.density;
    }
}
