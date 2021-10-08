package com.liyihuanx.logutil

import kotlin.collections.ArrayList

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 类的描述
 */
object LogManager {

    var logConfig: LogConfig = LogConfig.defaultLogConfig
    var printers = ArrayList<LogPrinter>()

    /**
     * 初始化
     */
    fun init(printers: List<LogPrinter>, logConfig: LogConfig = LogConfig.defaultLogConfig) {
        LogManager.logConfig = logConfig
        LogManager.printers.addAll(printers)
    }


    fun addLogPrinter(logPrinter: LogPrinter) {
        printers.add(logPrinter)
    }

    fun removeLogPrinter(logPrinter: LogPrinter) {
        printers.remove(logPrinter)
    }

}