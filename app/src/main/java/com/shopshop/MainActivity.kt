package com.shopshop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.shopshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    lateinit var binding: ActivityMainBinding

    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
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