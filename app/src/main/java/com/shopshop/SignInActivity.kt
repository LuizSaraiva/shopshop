package com.shopshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class SignInActivity : AppCompatActivity() {

    lateinit var btnRegiterNow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        listener()
        clicked()
    }

    private fun listener() {
        btnRegiterNow = findViewById(R.id.tv_create_account)

    }

    private fun clicked() {
        btnRegiterNow.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}