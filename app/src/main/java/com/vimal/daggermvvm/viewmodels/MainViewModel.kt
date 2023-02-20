/**
 * Created by Vimal on Feb-2023.
 */
package com.vimal.daggermvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimal.daggermvvm.models.Product
import com.vimal.daggermvvm.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    val productsLiveData : LiveData<List<Product>>
    get() = repository.products

    init {
        viewModelScope.launch {
            delay(2000)
            repository.getProducts()
        }
    }

}