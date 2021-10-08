package com.liyihuanx.logutil

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 类的描述
 */
class StackTraceFormatter : LogFormatter<Array<StackTraceElement>> {
    override fun format(data: Array<StackTraceElement>): String {
        val sb = StringBuilder(128)
        return if (data.size == 1) {
            "\t─ " + data[0].toString()
        } else {
            var i = 0
            val len: Int = data.size
            while (i < len) {
                if (i == 0) {
                    sb.append("stackTrace:  \n")
                }
                if (i != len - 1) {
                    sb.append("\t├ ").append(data[i].toString()).append("\n")
                } else {
                    sb.append("\t└ ").append(data[i].toString())
                }
                i++
            }
            sb.toString()
        }
    }
}