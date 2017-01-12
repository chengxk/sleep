package com.sleep

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.Toast
import com.taobao.hotfix.HotFixManager
import com.taobao.hotfix.util.PatchStatusCode
import com.umeng.analytics.MobclickAgent


/**
 * Created by zhuge on 17-1-10.
 */
class App : Application() {

    var versionName: String? = ""
    var versionCode: Int? = 1

    override fun onCreate() {
        super.onCreate()
        val packageInfo = getPackageInfo(this)

        versionName = packageInfo?.versionName
        versionCode = packageInfo?.versionCode


        if (!BuildConfig.DEBUG) {
            val config: MobclickAgent.UMAnalyticsConfig = MobclickAgent.UMAnalyticsConfig(this, "58771aefc62dca59b3000605", "xiaomi")
            config.mIsCrashEnable = true
            MobclickAgent.startWithConfigure(config)
        }

        initHotfix()
    }

    private fun initHotfix() {
        HotFixManager.getInstance().setContext(this)
                .setAppVersion(versionName)
                .setAppId("85904-1")
                .setAesKey(null)
                .setSupportHotpatch(true)
                .setEnableDebug(true)
                .setPatchLoadStatusStub { mode, code, info, handlePatchVersion ->
                    // 补丁加载回调通知
                    if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
                    } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
                        Toast.makeText(this, "重启应用", Toast.LENGTH_SHORT).show()
                    } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
                        // 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
                        HotFixManager.getInstance().cleanPatches(false);
                    } else {
                        // TODO: 10/25/16 其它错误信息, 查看PatchStatusCode类说明
                    }
                }.initialize()

    }

    private fun getPackageInfo(context: Context): PackageInfo? {
        var pi: PackageInfo? = null

        try {
            val pm = context.packageManager
            pi = pm.getPackageInfo(context.packageName,
                    PackageManager.GET_CONFIGURATIONS)

            return pi
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return pi
    }

}