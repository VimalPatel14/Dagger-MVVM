package com.vimal.daggermvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vimal.daggermvvm.databinding.ActivityMainBinding
import com.vimal.daggermvvm.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.progressDialog.visibility = View.VISIBLE

        mainViewModel.productsLiveData.observe(this, Observer {
            binding.progressDialog.visibility = View.GONE
            binding.products.setText(it.joinToString { x -> x.title + "\n\n" })
        })
    }

}