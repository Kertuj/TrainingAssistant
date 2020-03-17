package com.example.trainingassistant.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.trainingassistant.R
import com.example.trainingassistant.databinding.ActivityMainBinding
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        //TODO ładny kalendarzyk
        binding.date.text = DateTime.now().toString()



    }
}
