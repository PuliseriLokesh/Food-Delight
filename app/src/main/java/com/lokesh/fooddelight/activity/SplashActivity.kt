package com.lokesh.fooddelight.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lokesh.fooddelight.R


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val startAct = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(startAct)
        },2000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}