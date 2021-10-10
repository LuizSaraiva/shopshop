package com.shopshop.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shopshop.App
import com.shopshop.databinding.ActivityMainBinding
import com.shopshop.model.Item
import com.shopshop.model.ItemResponse
import com.shopshop.network.RemoteDataSource
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    lateinit var binding: ActivityMainBinding

    private val remoteData by lazy { RemoteDataSource() }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        binding.mainRv.adapter = adapter

        val token = App.getToken()

        if (token == null) {
            SignInActivity.launch(this@MainActivity)
        }
    }

    override fun onStart() {
        super.onStart()

        remoteData.getAll { list, throwable ->
            runOnUiThread {
                list?.let {
                    adapter.submitList(it)
                }
            }
        }
    }
}