package com.liyihuanx.logutil

import android.util.Log

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 最简单的，输出在控制台的打印器
 */
class ConsolePrinter : LogPrinter {
    override fun print(config: LogConfig, level: Int, tag: String?, printString: String) {
        val len = printString.length
        val countOfSub = len / LogConfig.MAX_LEN
        if (countOfSub > 0) {
            val log = StringBuilder()
            var index = 0
            for (i in 0 until countOfSub) {
                log.append(printString.substring(index, index + LogConfig.MAX_LEN))
                index += LogConfig.MAX_LEN
            }
            if (index != len) {
                log.append(printString.substring(index, len))
            }
            Log.println(level, tag, log.toString())
        } else {
            Log.println(level, tag, printString)
        }
    }
}