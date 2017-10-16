package com.mychat.guopeizhen.mychat.model.cache;

import com.mychat.guopeizhen.mychat.app.Constants;
import com.mychat.guopeizhen.mychat.util.SPUtil;
import com.mychat.guopeizhen.mychat.util.UIUtil;

/**
 * Created by Administrator on 2017/10/16.
 */

public class UserCache {

    public static String getID(){
        return SPUtil.getInstance(UIUtil.getContext()).getString(Constants.User.ID,"");
    }

    public static String getPhone(){
        return SPUtil.getInstance(UIUtil.getContext()).getString(Constants.User.PHONE,"");
    }

    public static String getToken(){
        return SPUtil.getInstance(UIUtil.getContext()).getString(Constants.User.TOKEN,"");
    }

    public static void save (String id,String phone ,String token){
        SPUtil.getInstance(UIUtil.getContext()).putString(Constants.User.ID,id);
        SPUtil.getInstance(UIUtil.getContext()).putString(Constants.User.PHONE,phone);
        SPUtil.getInstance(UIUtil.getContext()).putString(Constants.User.TOKEN,token);
    }
}
