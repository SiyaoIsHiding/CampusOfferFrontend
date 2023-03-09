package com.example.campusoffer.viewmodels

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.requests.NewProduct
import com.example.campusoffer.models.responses.ImageIdList
import com.example.campusoffer.models.responses.SingleImage
import com.example.campusoffer.ui.MainActivity
import com.example.campusoffer.ui.fragments.SellFragment
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.USER_TEST_ID
import com.example.campusoffer.util.NetworkResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class SellViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    application: Application
) : AndroidViewModel(application) {

    private val TAG = "SellViewModel"
    val postProductRes: MutableLiveData<NetworkResult<ImageIdList>> = MutableLiveData()
    val imageUriList: MutableList<Uri> = mutableListOf()

    lateinit var fragment : SellFragment
    fun postNewProduct(
        title: String,
        description: String,
        price: Double,
        imageNum: Int
    ){
        val body = NewProduct(CATEGORY_ROOT_ID, description, imageNum, price, USER_TEST_ID, title) //TODO: fix the hardcode
        viewModelScope.launch {
            val res = productRepository.remote.postNewProduct(body)
            Log.v(TAG, res.body()?.idList.toString())
            if(res.isSuccessful){
                postImages(res.body()!!, imageUriList.toList()) // deep copy
            }
            postProductRes.value = handleNewProductResponse(res)
        }

    }

    private fun handleNewProductResponse(res: Response<ImageIdList>): NetworkResult<ImageIdList> {

        when {
            res.code() >= 500 -> {
                return NetworkResult.Error("Server Internal Error")
            }

            res.code() >= 400 -> {
                return NetworkResult.Error("Server Error")
            }

            res.isSuccessful -> {
                return NetworkResult.Success(res.body()!!)
            }

            else -> {
                return NetworkResult.Error(res.message())
            }
        }

    }

    private fun postImages(idRes: ImageIdList, imageUriList: List<Uri>){
        val idList = idRes.idList
        if (idList.isEmpty() || idList.size != imageUriList.size){
            Log.v(TAG, "size difference")
            return
        }
        viewModelScope.launch {
            for (i in idList.indices){
                async {
                    val image = SingleImage(imageUriToStr(imageUriList.get(i)))
                    productRepository.remote.postImage(idList.get(i),image)
                }
            }
        }
    }
    private fun imageUriToStr(imageUri: Uri) : String{
        val imageStream: InputStream = fragment.requireContext().contentResolver.openInputStream(imageUri)!!
        val bm = BitmapFactory.decodeStream(imageStream)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val encImage = Base64.encodeToString(b, Base64.DEFAULT)
        return encImage
    }
}