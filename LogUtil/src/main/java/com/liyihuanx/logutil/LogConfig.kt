package com.liyihuanx.logutil

import com.google.gson.GsonBuilder

/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: log的一些配置
 */
interface LogConfig {

    /**
     * 配置你的解析器，比如Gson，fastJson等
     */
    fun injectJsonParser(): JsonParser? {
        return null
    }

    /**
     * 输出日志的TAG
     */
    fun getGlobalTag(): String {
        return "LogUtil"
    }

    /**
     * 能否输出日志,通过这个变量可以做开关
     */
    fun enable(): Boolean {
        return true
    }

    /**
     * 是否需要当前log语句输出的执行线程
     */
    fun includeThread(): Boolean {
        return true
    }

    /**
     * 获取输出时，堆栈深度
     */
    fun stackTraceDepth(): Int {
        return 5
    }

    /**
     * 所有的输出器
     */
    fun printers(): List<LogPrinter>? {
        return null
    }

    interface JsonParser {
        fun toJson(src: Any): String
    }


    companion object {
        const val MAX_LEN = 512
        val STACK_TRACE_FORMATTER = StackTraceFormatter()
        val THREAD_TRACE_FORMATTER = ThreadFormatter()

        val defaultLogConfig = object : LogConfig {
            override fun injectJsonParser(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        // 这样可以直接格式化好
                        return GsonBuilder().setPrettyPrinting().create().toJson(src)
                    }
                }
            }
        }
    }

}