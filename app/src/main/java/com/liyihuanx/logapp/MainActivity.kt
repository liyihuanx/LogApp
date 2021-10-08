package com.liyihuanx.logapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liyihuanx.logutil.LogManager
import com.liyihuanx.logutil.MLog
import com.liyihuanx.logutil.ViewPrinter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLog.setOnClickListener {
            MLog.v("6789")
            MLog.d("6789")
            MLog.i("6789")
            MLog.w("6789")
            MLog.e("6789")
            MLog.a("6789")
        }
        val viewPrinter = ViewPrinter(this)
        LogManager.addLogPrinter(viewPrinter)
    }
}