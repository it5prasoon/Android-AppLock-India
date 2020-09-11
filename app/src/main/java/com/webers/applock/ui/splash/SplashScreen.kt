package com.webers.applock.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.webers.applock.R
import com.webers.applock.ui.main.MainActivity

class SplashScreen : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2700) //delaying 3 secs to open main activity
    }
}