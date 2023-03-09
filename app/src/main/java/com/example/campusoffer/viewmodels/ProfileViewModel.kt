package com.example.campusoffer.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Category
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.User
import com.example.campusoffer.models.responses.ProductsIdList
import com.example.campusoffer.models.responses.SubCategory
import com.example.campusoffer.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {
    var currentUser : MutableLiveData<User?> = MutableLiveData()

    fun getUserProfile(queries: Map<String, String>) = viewModelScope.launch {
        val res = productRepository.remote.getUserByID(queries)
        if(res.isSuccessful && res.body() != null){
            currentUser.postValue(res.body()!!)
        }
    }

    fun updateProfile(user: User) = viewModelScope.launch {
        productRepository.remote.updateProfile(user.id!!, user)
    }

}