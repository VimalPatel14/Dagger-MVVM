/**
 * Created by Vimal on Feb-2023.
 */
package com.vimal.daggermvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vimal.daggermvvm.adapter.ProductAdapter
import com.vimal.daggermvvm.databinding.ActivityMainBinding
import com.vimal.daggermvvm.models.Product
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

        val adapter = ProductAdapter(::onItemClicked)

        binding.recyclerview.setHasFixedSize(true)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerview.setLayoutManager(layoutManager)
        binding.recyclerview.adapter = adapter

        mainViewModel.productsLiveData.observe(this, Observer {
            binding.progressDialog.visibility = View.GONE
            adapter.setMovies(it)
//            binding.products.setText(it.joinToString { x -> x.title + "\n\n" })
        })
    }

    private fun onItemClicked(product: Product) {
        Toast.makeText(
            this@MainActivity, product.title,
            Toast.LENGTH_SHORT
        ).show()
    }

}