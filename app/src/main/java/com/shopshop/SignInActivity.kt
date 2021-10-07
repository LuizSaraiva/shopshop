package com.shopshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.shopshop.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    lateinit var btnRegiterNow: TextView
    lateinit var binding: ActivitySignInBinding

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.tvCreateAccount.setOnClickListener {
            SignUpActivity.launch(this)
        }

    }
}