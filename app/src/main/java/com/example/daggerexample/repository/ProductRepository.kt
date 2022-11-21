package com.example.daggerexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggerexample.models.ProductsItem
import com.example.daggerexample.retrofit.FakerApi
import javax.inject.Inject

class ProductRepository @Inject constructor(private val fakerApi: FakerApi) {

    //no one should able to change data from outside so declare private
    private val _products  =MutableLiveData<List<ProductsItem>>()
    // readonly live data to expose
    val products :LiveData<List<ProductsItem>>
    get() = _products

    suspend fun getProducts(){
        val result = fakerApi.getProducts()
        if(result.isSuccessful && result.body() != null){
            _products.postValue(result.body())
        }
    }
}