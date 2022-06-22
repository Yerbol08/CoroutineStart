package com.yerbol.coroutinestart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.yerbol.coroutinestart.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadData(){
        binding.buttonLoad.isEnabled = false
        binding.progress.isVisible = true
        loadCity{
            binding.tvLocation.text = it
            loadTemperature {
                binding.tvTemperature.text = it.toString()
                binding.buttonLoad.isEnabled = true
                binding.progress.isVisible = false
            }
        }
    }

    private fun loadCity(callback:(String) ->Unit){
        thread {
            Thread.sleep(5000)
            callback.invoke("Moscow")
        }
    }
    private fun loadTemperature(callback:(Int) ->Unit){
        thread {
            Thread.sleep(5000)
            callback.invoke(17)}
    }
}