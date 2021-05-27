package com.tocaan.middleman.ui.activities

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tocaan.middleman.R
import com.tocaan.middleman.ui.broadcasts.EmitterReceiver


class MainActivity : AppCompatActivity() {
    private var MyReceiver: EmitterReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter("com.emitter.emit")
        MyReceiver = EmitterReceiver()
        registerReceiver(MyReceiver, intentFilter)

    }
}