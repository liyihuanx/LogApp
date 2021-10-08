package com.liyihuanx.logutil

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author created by liyihuanx
 * @date 2021/10/8
 * @description: 类的描述
 */
data class LogBean(
    var timeMillis: Long = 0,
    var level: Int = 0,
    var tag: String? = null,
    var log: String? = null,
) {

    private val sdf = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA)

    fun flattenedLog(): String {
        return "${getFlattened()} \n $log"
    }

    fun getFlattened(): String {
        return format(timeMillis) + "--" + level + "--" + tag + "|:"
    }

    private fun format(timeMillis: Long): String {
        return sdf.format(timeMillis)
    }
}