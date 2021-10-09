package com.shopshop.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.shopshop.R
import com.shopshop.databinding.ActivitySignInBinding
import com.shopshop.model.RequestUser
import com.shopshop.network.RemoteDataSource

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    val remoteData by lazy { RemoteDataSource() }

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

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.showProgress {
                progressColor = Color.WHITE
            }

            if (validateLogin())
                doLogin()
        }
    }

    private fun doLogin() {
        val email = this.binding.tieEmail.text.toString()
        val pass = this.binding.tiePass.text.toString()

        val user = RequestUser(null, pass, email)
        remoteData.login(user) { token, throwable ->

            runOnUiThread {
                if (token != null) {
                    MainActivity.launch(this@SignInActivity)
                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        getString(R.string.user_pass_invalid),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.btnLogin.hideProgress(R.string.login)
            }
        }
    }

    private fun validateLogin(): Boolean {

        val email = this.binding.tieEmail.text.toString()
        val pass = this.binding.tiePass.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(
                this@SignInActivity,
                getString(R.string.fill_all_fields),
                Toast.LENGTH_LONG
            )
                .show()

            binding.btnLogin.hideProgress(R.string.login)
            return false
        }
        return true
    }

}