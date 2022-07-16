package com.example.fradelete.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fradelete.retrofit.FakerAPI
import org.json.JSONObject
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val fakerAPI: FakerAPI) {

    private val products_: MutableLiveData<Result<JSONObject>> = MutableLiveData()
    val products : LiveData<Result<JSONObject>>
    get() = products_

    suspend fun getProducts(){
        val x = fakerAPI.getProducts()
        if(x.isSuccess){
            products_.postValue(x)
        }
    }

}