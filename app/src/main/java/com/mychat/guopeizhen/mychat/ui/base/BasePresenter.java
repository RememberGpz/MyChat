package com.mychat.guopeizhen.mychat.ui.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/10/13.
 */

public class BasePresenter<V> {

    public BaseActivity mContext;

    public BasePresenter(BaseActivity mContext){
        this.mContext = mContext;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    public void dettachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView (){
        return mViewRef!=null?mViewRef.get():null;
    }
}
