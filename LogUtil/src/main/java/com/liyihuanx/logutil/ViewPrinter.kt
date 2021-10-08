package com.liyihuanx.logutil

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liyihuanx.module_logutil.R

/**
 * @author created by liyihuanx
 * @date 2021/10/8
 * @description: 可视化打印器
 */
class ViewPrinter(activity: Activity) : LogPrinter {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LogAdapter

    init {
        val rootView: FrameLayout = activity.findViewById(android.R.id.content)

        val viewPrinter = LayoutInflater.from(activity).inflate(R.layout.view_printer, null, false)
        initRecyclerView(viewPrinter)
        initViewPrinterLayout(viewPrinter)
        rootView.addView(viewPrinter)
    }

    private fun initViewPrinterLayout(viewPrinter: View) {
        val tvOperate: TextView = viewPrinter.findViewById(R.id.tvOperate)
        val rlLogTitle: RelativeLayout = viewPrinter.findViewById(R.id.rlLogTitle)
        val tvClean: TextView = viewPrinter.findViewById(R.id.tvClean)

        tvOperate.setOnClickListener {
            if (tvOperate.text == "Close") {
                recyclerView.visibility = View.GONE
                rlLogTitle.visibility = View.GONE
                tvOperate.text = "Open"
            } else {
                recyclerView.visibility = View.VISIBLE
                rlLogTitle.visibility = View.VISIBLE
                tvOperate.text = "Close"
            }
        }

        tvClean.setOnClickListener {
            adapter.clearLog()
        }
    }

    private fun initRecyclerView(viewPrinter: View) {
        recyclerView = viewPrinter.findViewById(R.id.rvViewPrinter)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        adapter = LogAdapter(recyclerView.context)
        recyclerView.adapter = adapter
    }


    override fun print(config: LogConfig, level: Int, tag: String?, printString: String) {
        adapter.addItem(LogBean(System.currentTimeMillis(), level, tag, printString))
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }
}




