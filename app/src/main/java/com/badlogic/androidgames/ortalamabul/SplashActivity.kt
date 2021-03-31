package com.badlogic.androidgames.ortalamabul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var fromDownToUpButton = AnimationUtils.loadAnimation(this, R.anim.buttonfromdowntoup)
        var fromUpToDownImage = AnimationUtils.loadAnimation(this, R.anim.imagefromuptodown)
        var fromUpToDownButton = AnimationUtils.loadAnimation(this, R.anim.buttonfromuptodown)
        var fromDownToUpImage = AnimationUtils.loadAnimation(this, R.anim.imagefromdowntoup)

        btnGiris.animation = fromDownToUpButton
        ivsplash.animation = fromUpToDownImage

        btnGiris.setOnClickListener {
            btnGiris.startAnimation(fromUpToDownButton)
            ivsplash.startAnimation(fromDownToUpImage)

            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {

                    var intent = Intent(applicationContext, OptionActivity::class.java)
                    startActivity(intent)
                }

            }.start()

        }











    }
}