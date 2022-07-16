package com.example.fradelete.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fradelete.repository.ProductsRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class MainViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel(){

    val product: LiveData<Result<JSONObject>>
    get() = productsRepository.products

    init {
        viewModelScope.launch {
            productsRepository.getProducts()
        }
    }

}