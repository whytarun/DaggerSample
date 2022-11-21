package com.example.daggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggerexample.viewmodel.MainViewModel
import com.example.daggerexample.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val products :TextView
    get() = findViewById(R.id.products)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as FakerApplication).applicationComponent.inject(this)
        mainViewModel =ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
        mainViewModel.productsLiveData.observe(this, Observer {
            products.text= it.joinToString { x -> x.title +"\n\n" }
        })
        mainViewModel.errorMutableLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}