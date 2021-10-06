package com.shopshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {

    lateinit var btnRegiterNow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        listener()
        clicked()
    }

    private fun listener() {
        btnRegiterNow = findViewById(R.id.tv_do_login)

    }

    private fun clicked() {
        btnRegiterNow.setOnClickListener {
            finish()
        }
    }
}