package com.shopshop.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.shopshop.R
import com.shopshop.databinding.ActivitySignUpBinding
import com.shopshop.model.RegisterRequest
import com.shopshop.model.RequestUser
import com.shopshop.network.RemoteDataSource

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

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

        binding.registerTvDoLogin.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener() {

            binding.btnRegister.showProgress {
                progressColor = Color.WHITE
            }

            if (validate())
                doRegister()

        }
    }

    private fun validate(): Boolean {
        val name = binding.registerTieName.text.toString()
        val password = binding.registerTiePass.text.toString()
        val email = binding.registerTieEmail.text.toString()

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this@SignUpActivity, R.string.fill_all_fields, Toast.LENGTH_LONG)
                .show()
            binding.btnRegister.hideProgress(R.string.register)
            return false
        }
        return true
    }

    private fun doRegister(){
        val name = binding.registerTieName.text.toString()
        val password = binding.registerTiePass.text.toString()
        val email = binding.registerTieEmail.text.toString()

        val userLogin = RegisterRequest(name = name, password = password, email = email)
        RemoteDataSource().register(userLogin) { token, throwable ->
            runOnUiThread {
                if (token != null) {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.register_success),
                        Toast.LENGTH_SHORT
                    ).show()

                    MainActivity.launch(this@SignUpActivity)

                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.already_registered), Toast.LENGTH_SHORT
                    ).show()
                    binding.btnRegister.hideProgress(R.string.register)
                }
            }
        }
    }
}