package com.shopshop.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shopshop.model.Item
import com.shopshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    lateinit var binding: ActivityMainBinding

    companion object{
        fun launch(context: Context){
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

        val listAdapter = listOf<Item>(
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
            Item("Teste","Teste1",1),
        )

        adapter.submitList(listAdapter)
    }
}