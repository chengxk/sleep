package com.sleep.ui

import android.support.v7.app.AppCompatActivity
import com.umeng.analytics.MobclickAgent



/**
 * Created by zhuge on 17-1-12.
 */

open class BaseActivity: AppCompatActivity(){

    public override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}