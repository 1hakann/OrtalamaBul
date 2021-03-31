package com.badlogic.androidgames.ortalamabul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        btnDers.setOnClickListener {
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnTip.setOnClickListener {
            var intent = Intent(applicationContext, TipActivity::class.java)
            startActivity(intent)
        }
    }
}