package com.example.jokes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val sharedPreferences = applicationContext.getSharedPreferences("user", Context.MODE_PRIVATE)
            if (sharedPreferences.getString("user", null) == null) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, Category::class.java)
                startActivity(intent)
                finish()
            }
        }, 2500)


    }

}