package com.example.trainingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.trainingassistant.databinding.ActivityMainBinding
import com.example.trainingassistant.ui.network.ApiClient

class MainActivity : AppCompatActivity() {

    private val api by lazy {
        ApiClient.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
