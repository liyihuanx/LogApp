package com.liyihuanx.logutil

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: log输出格式化
 */
interface LogFormatter<T> {
    fun format(data: T): String
}