package com.liyihuanx.logapp

import android.app.Application
import com.liyihuanx.logutil.ConsolePrinter
import com.liyihuanx.logutil.FilePrinter
import com.liyihuanx.logutil.LogManager

/**
 * @author created by liyihuanx
 * @date 2021/10/8
 * @description: 类的描述
 */
class LogApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        // log输出器
        LogManager.init(
            arrayListOf(
                ConsolePrinter(),
                FilePrinter.getInstance(applicationContext.cacheDir.absolutePath, 0)
            )
        )
    }
}