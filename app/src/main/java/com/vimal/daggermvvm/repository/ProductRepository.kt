/**
 * Created by Vimal on Feb-2023.
 */
package com.vimal.daggermvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vimal.daggermvvm.db.FakerDB
import com.vimal.daggermvvm.models.Product
import com.vimal.daggermvvm.retrofit.FakerAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(private val fakerAPI: FakerAPI, private val fakerDB: FakerDB) {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
    get() = _products

    suspend fun getProducts(){
        val result = fakerAPI.getProducts()
        if(result.isSuccessful && result.body() != null){
            fakerDB.getFakerDAO().addProducts(result.body()!!)
            _products.postValue(result.body())
        }
    }

}