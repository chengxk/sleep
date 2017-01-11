package com.sleep.ui

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sleep.App
import com.sleep.R
import com.sleep.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {


    companion object {
        fun startActivity(source: Activity) {
            val intent: Intent = Intent(source, AboutActivity::class.java)
            source.startActivity(intent)
        }
    }


    val binding: ActivityAboutBinding by lazy {
        DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.warning = "针对部分手机设备进入睡眠后会自动杀掉应用进程，为了功能的正常使用，需要加入手机的应用保护！"

        binding.desc = "由于精力有限，暂不支持更新功能,当前版本不能正常使用时可以去应用商店下载最新版（一般会上传到小米应用商店）"
        val app: App = application as App
        binding.version = "当前版本:${app.versionName}"

    }
}
