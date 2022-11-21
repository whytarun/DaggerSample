package com.example.daggerexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerexample.models.ProductsItem
import com.example.daggerexample.repository.ProductRepository
import com.example.daggerexample.utils.NoInternetException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(val repository: ProductRepository) :ViewModel() {

    val productsLiveData :LiveData<List<ProductsItem>>
    get() = repository.products
    val errorMutableLiveData =MutableLiveData<String>()
    private val handler = CoroutineExceptionHandler {
            context, exception ->
        run {
            println("Caught $exception")
            errorMutableLiveData.value = "Error Due to $exception"
        }
    }

    init {
        viewModelScope.launch(handler) {
                repository.getProducts()
        }
    }
}