package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]
        binding.subscriberViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribes.observe(this, Observer {
            Log.i("MyTag", it.toString())
        })
    }
}