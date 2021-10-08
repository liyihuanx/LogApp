package com.liyihuanx.logutil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liyihuanx.module_logutil.R
import java.util.ArrayList

/**
 * @author created by liyihuanx
 * @date 2021/10/8
 * @description: 类的描述
 */
class LogAdapter(private val context: Context) :
    RecyclerView.Adapter<LogAdapter.LogViewHolder>() {
    private val logs: MutableList<LogBean> = ArrayList()
    fun addItem(logItem: LogBean) {
        logs.add(logItem)
        notifyItemInserted(logs.size - 1)
    }

    fun clearLog() {
        logs.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_view_log, parent, false)
        return LogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val logItem = logs[position]
        val color = getHighlightColor(logItem.level)
        holder.tagView.setTextColor(color)
        holder.messageView.setTextColor(color)
        holder.tagView.text = logItem.getFlattened()
        holder.messageView.text = logItem.log
    }

    /**
     * 跟进log级别获取不同的高了颜色
     *
     * @param logLevel log 级别
     * @return 高亮的颜色
     */
    private fun getHighlightColor(logLevel: Int): Int {
        return when (logLevel) {
            LogType.V -> -0x444445
            LogType.D -> -0x1
            LogType.I -> -0x9578a7
            LogType.W -> -0x444ad7
            LogType.E -> -0x9498
            else -> -0x100
        }
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    inner class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tagView: TextView = itemView.findViewById(R.id.tag)
        var messageView: TextView = itemView.findViewById(R.id.message)
    }
}