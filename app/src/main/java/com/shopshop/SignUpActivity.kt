package com.shopshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.shopshop.databinding.ActivitySignUpBinding
import com.shopshop.model.RequestUser
import com.shopshop.network.RemoteDataSource

class SignUpActivity : AppCompatActivity() {

    lateinit var btnRegiterNow: TextView
    lateinit var binding: ActivitySignUpBinding
    val dataSource by lazy { RemoteDataSource() }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.tvDoLogin.setOnClickListener {
            finish()
        }
    }

    val user = RequestUser("teste", "123")

}