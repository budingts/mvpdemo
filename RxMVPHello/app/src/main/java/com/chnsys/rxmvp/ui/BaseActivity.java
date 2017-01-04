package com.chnsys.rxmvp.ui;

import android.app.Activity;
import android.os.Bundle;

import com.chnsys.rxmvp.utils.ZTLUtils;


/**
 *  基类
 *@author:juanqiang
 *created at:2016/12/30 下午2:40
 *
 */
public class BaseActivity extends Activity {
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        new ZTLUtils(mActivity).setTranslucentStatus();
    }
}
