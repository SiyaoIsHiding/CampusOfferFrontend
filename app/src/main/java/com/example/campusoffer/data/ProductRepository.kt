package com.example.campusoffer.data

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.Constants.Companion.QUERY_ID
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.*
import javax.inject.Inject

@ActivityRetainedScoped
class ProductRepository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource
    val TAG = "ProductRepository"
    suspend fun getListProducts(queries : Map<String, String>, liveData: MutableLiveData<MutableList<Product?>>){
        MainScope().launch {
            val res1 = remote.getProductsUnderCategory(queries)
            var idList = listOf<String>()
            if (!res1.body()?.productId.isNullOrEmpty()) {
                idList = res1.body()!!.productId
            }
            liveData.value = MutableList(idList.size) {index -> null}
            for( i in idList.indices){
                async {
                    val queries = HashMap<String, String>()
                    queries.put(QUERY_ID, idList.get(i))
                    val res2 = remote.getProductByID(queries)
                    liveData.value?.set(i, res2.body()!!)
                    liveData.value = liveData.value
                }
            }
        }
    }

    suspend fun getImageBytesById(imageId: String): ByteArray? {
        val res = remote.getImageByID(imageId)
        if (res.body() != null && !res.body()!!.image.isNullOrEmpty()) {
            var base64String = res.body()!!.image
            base64String = base64String!!.substring(base64String!!.indexOf(",")  + 1)
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            return imageBytes
        }
        return null
    }
}