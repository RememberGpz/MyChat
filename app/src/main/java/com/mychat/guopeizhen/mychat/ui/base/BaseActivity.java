package com.mychat.guopeizhen.mychat.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.widget.CustomDialog;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by guopeizhen on 2017/10/13.
 * 自定圆角Dialog
 *
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity  {

    protected T mPresenter;
    private CustomDialog customDialog;
    private MaterialDialog materialDialog;

    //下面是所有activity中可能出现的控件
    @Bind(R.id.appBar)
    protected AppBarLayout appBarLayout;
    @Bind(R.id.flToolbar)
    public FrameLayout mToolbar;
    @Bind(R.id.ivToolbarNavigation)
    public ImageView mToolbarNavigation;
    @Bind(R.id.vToolbarDivision)
    public View mToolbarDivision;
    @Bind(R.id.llToolbarTitle)
    public AutoLinearLayout mLlToolbarTitle;
    @Bind(R.id.tvToolbarTitle)
    public TextView mToolbarTitle;
    @Bind(R.id.tvToolbarSubTitle)
    public TextView mToolbarSubTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        //判断是否使用MVp
        mPresenter = createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);


    }

    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init(){

    }

    //用于创建presenter 和 判断是否使用MVP模式（由子类实现）
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }
}
