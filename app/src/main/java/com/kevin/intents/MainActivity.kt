package com.kevin.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.nextScreenBtn.setOnClickListener {
            val switcher = Intent(this, SecondActivity::class.java)
            startActivity(switcher)
        }

        binding.webBrowserBtn.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(browser)
        }
    }
}