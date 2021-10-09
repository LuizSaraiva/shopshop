package com.shopshop.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
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

        binding.btnRegister.setOnClickListener() {

            val name = binding.tieName.text.toString()
            val password = binding.tiePass.text.toString()
            val email = binding.tieEmail.text.toString()

            if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Preencha todos os campos", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            } else {
                val userLogin = RequestUser(name, password, email)
                RemoteDataSource().register(userLogin) { token, throwable ->
                    println(token)
                    println(throwable)
                }
                return@setOnClickListener
            }
        }
    }
}