package com.liyihuanx.logutil

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 类的描述
 */
class ThreadFormatter : LogFormatter<Thread> {
    override fun format(data: Thread): String = "Thread: ${data.name}"
}