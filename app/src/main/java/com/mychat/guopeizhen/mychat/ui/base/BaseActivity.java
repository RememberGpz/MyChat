package com.mychat.guopeizhen.mychat.ui.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mychat.guopeizhen.mychat.R;
import com.mychat.guopeizhen.mychat.widget.CustomDialog;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
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
    @BindView(R.id.ivToolbarNavigation)
    ImageView ivToolbarNavigation;
    @BindView(R.id.vToolbarDivision)
    View vToolbarDivision;
    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;
    @BindView(R.id.tvToolbarSubTitle)
    TextView tvToolbarSubTitle;
    @BindView(R.id.llToolbarTitle)
    AutoLinearLayout llToolbarTitle;
    @BindView(R.id.ibToobarAdd)
    ImageButton ibToobarAdd;
    @BindView(R.id.vLine)
    View vLine;
    @BindView(R.id.llToolbarAddFriend)
    AutoLinearLayout llToolbarAddFriend;
    @BindView(R.id.etToolbarSearch)
    EditText etToolbarSearch;
    @BindView(R.id.llToolbarSearch)
    AutoLinearLayout llToolbarSearch;
    @BindView(R.id.btnToolbarSend)
    Button btnToolbarSend;
    @BindView(R.id.ibToolbarMore)
    ImageButton ibToolbarMore;
    @BindView(R.id.flToolbar)
    AutoFrameLayout flToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

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

        setupAppbarAndToolbar();

        initView();
        initDataAndEvent();


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init(){

    }

    public void initView(){

    }

    public void initDataAndEvent(){

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

    /**
    设置appbar和toolbar
     */
    private void setupAppbarAndToolbar(){
        //如果该应用运行在android 5.0以上设备，设置标题栏的z轴高度
        if (appBar!=null && Build.VERSION.SDK_INT>21){
            appBar.setElevation(10.6f);
        }
        //如果界面中有使用toolbar，则使用toolbar替代actionbar
        //默认不是使用NoActionBar主题，所以如果需要使用Toolbar，需要自定义NoActionBar主题后，在AndroidManifest.xml中对指定Activity设置theme
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            if (isToolbarCanBack()) {
//                ActionBar actionBar = getSupportActionBar();
//                if (actionBar != null) {
//                    actionBar.setDisplayHomeAsUpEnabled(true);
//                }
//            }
//        }

        ivToolbarNavigation.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
        vToolbarDivision.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
        ivToolbarNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        llToolbarTitle.setPadding(isToolbarCanBack() ? 0 : 40, 0, 0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.dettachView();
        }
    }

    public void jumpToActivity(Intent intent){
        startActivity(intent);
    }

    public void jumpToActivity(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    //弹出等待dialog
    public Dialog showWaitingDialog(String content){
        hideWaitingDialog();
        View view = View.inflate(this,R.layout.dialog_custom,null);
        if (content!=null){
            ((TextView)view.findViewById(R.id.tvTips)).setText(content);
        }
        customDialog = new CustomDialog(this,view,R.style.MyDialog);
        customDialog.show();
        return customDialog;
    }

    //隐藏等待dialog
    public void hideWaitingDialog(){
        if (customDialog!=null){
            customDialog.dismiss();
            customDialog = null;
        }
    }
    /*------------------ toolbar的一些视图操作 ------------------*/
    public void setToolbarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    public void setToolbarSubTitle(String subTitle) {
        tvToolbarSubTitle.setText(subTitle);
        tvToolbarSubTitle.setVisibility(subTitle.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
