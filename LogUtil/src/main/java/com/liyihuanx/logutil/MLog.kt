package com.liyihuanx.logutil


/**
 * @author created by liyihuanx
 * @date 2021/9/29
 * @description: 暴露给用户使用的入口
 */
object MLog {
    private val className: String = this.javaClass.name
    private val packageName = className.substring(0, className.lastIndexOf('.') + 1);

    private fun myLog(@LogType.TYPE type: Int, tag: String?, contents: Any?) {
        realLog(LogManager.logConfig, type, tag, contents)
    }


    private fun realLog(
        config: LogConfig,
        @LogType.TYPE type: Int,
        tag: String?,
        contents: Any?
    ) {
        if (!config.enable()) {
            return
        }
        val stringBuilder = StringBuilder()
        // 打印线程信息
        if (config.includeThread()) {
            val threadInfo = LogConfig.THREAD_TRACE_FORMATTER.format(Thread.currentThread())
            stringBuilder.append(threadInfo).append("\n")
        }
        // 打印堆栈信息
        if (config.stackTraceDepth() > 0) {
            val stackTrace = LogConfig.STACK_TRACE_FORMATTER.format(
                StackTraceUtil.getCroppedRealStackTrack(
                    Throwable().stackTrace,
                    packageName,
                    config.stackTraceDepth()
                )
            )
            stringBuilder.append(stackTrace).append("\n")
        }
        // replace替换转义字符\
        var body = parseBody(contents, config).toString().replace("\\\"", "\"")
        stringBuilder.append(body)
        val printers = (config.printers() ?: LogManager.printers) as List<LogPrinter>
        printers.takeIf {
            it.isNotEmpty()
        }?.forEach {
            it.print(config, type, tag, stringBuilder.toString())
        }
    }


    private fun parseBody(contents: Any?, config: LogConfig): Any {
        if (contents == null) {
            return "输出内容为空"
        }

        if (config.injectJsonParser() == null) {
            return "JsonParser或输出内容为空"
        }

//        if (isPrimitive(contents)) {
//            return contents
//        }

        return config.injectJsonParser()!!.toJson(contents)
    }


    /**
     * 判断是否是基本数据类型
     */
    private fun isPrimitive(value: Any?): Boolean {
        try {
            if (value != null) {
                val file = value.javaClass.getField("TYPE")
                val clazz = file.get(null) as Class<*>

                if (clazz.isPrimitive)
                    return true
            }

        } catch (e: NoSuchFieldError) {
            e.printStackTrace()
        } catch (e: IllegalAccessError) {
            e.printStackTrace()
        }

        return false
    }


    fun v(contents: Any?) {
        myLog(LogType.V, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun vTag(tag: String, contents: Any?) {
        myLog(LogType.V, tag, contents)
    }

    fun d(contents: Any?) {
        myLog(LogType.D, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun dTag(tag: String, contents: Any?) {
        myLog(LogType.D, tag, contents)
    }

    fun i(contents: Any?) {
        myLog(LogType.I, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun iTag(tag: String, contents: Any?) {
        myLog(LogType.I, tag, contents)
    }

    fun w(contents: Any?) {
        myLog(LogType.W, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun wTag(tag: String, contents: Any?) {
        myLog(LogType.W, tag, contents)
    }


    fun e(contents: Any?) {
        myLog(LogType.E, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun eTag(tag: String, contents: Any?) {
        myLog(LogType.E, tag, contents)
    }


    fun a(contents: Any?) {
        myLog(LogType.A, LogManager.logConfig.getGlobalTag(), contents)
    }

    fun aTag(tag: String, contents: Any?) {
        myLog(LogType.A, tag, contents)
    }
}