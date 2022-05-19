package com.example.coursework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework.databinding.ActivityMainBinding
import com.example.coursework.fragments.SignIn
import com.example.coursework.services.NewsService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startService(Intent(this@MainActivity, NewsService::class.java))

        supportFragmentManager.beginTransaction()
            .add(binding.frame.id, SignIn())
            .addToBackStack(null)
            .commit()
    }
}