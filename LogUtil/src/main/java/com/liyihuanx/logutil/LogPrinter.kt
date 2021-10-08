package com.liyihuanx.logutil


/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 类的描述
 */
interface LogPrinter {
    fun print(config: LogConfig, level: Int, tag: String?, printString: String)
}