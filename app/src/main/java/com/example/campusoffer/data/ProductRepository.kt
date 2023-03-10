package com.example.campusoffer.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.ETC1.encodeImage
import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.responses.ImageIdList
import com.example.campusoffer.util.Constants.Companion.QUERY_ID
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject


@ActivityRetainedScoped
class ProductRepository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource
    val TAG = "ProductRepository"

    suspend fun getListProducts(queries : Map<String, String>, liveData: MutableLiveData<MutableList<Product?>>, liveImages: MutableLiveData<MutableList<Drawable?>>? = null, callBack : (index: Int, product : Product?) -> Unit){
        MainScope().launch {
            val res1 = remote.getProductsUnderCategory(queries)
            var idList = listOf<String>()
            if (!res1.body()?.productId.isNullOrEmpty()) {
                idList = res1.body()!!.productId
            }
            liveData.value = MutableList(idList.size) {index -> null}
            if(liveImages != null && liveImages.value != null){
                while(liveImages.value!!.size < idList.size){
                    liveImages.value!!.add(null)
                }
                liveImages.value = liveImages.value
            }
            for( i in idList.indices){
                async {
                    val queries = HashMap<String, String>()
                    queries.put(QUERY_ID, idList.get(i))
                    val res2 = remote.getProductByID(queries)
                    liveData.value?.set(i, res2.body())
                    liveData.value = liveData.value
                    callBack.invoke(i, res2.body()) // Used for retrieve cover image
                }
            }
        }
    }

    suspend fun getSavedProducts(queries : Map<String, String>, liveData: MutableLiveData<MutableList<Product?>>, liveImages: MutableLiveData<MutableList<Drawable?>>? = null, callBack : (index: Int, product : Product?) -> Unit){
        MainScope().launch {
            val res1 = remote.getSavedProducts(queries)
            var idList = listOf<String>()
            if (!res1.body()?.savedProducts.isNullOrEmpty()) {
                idList = res1.body()!!.savedProducts
            }
            liveData.value = MutableList(idList.size) {index -> null}
            if(liveImages != null && liveImages.value != null){
                while(liveImages.value!!.size < idList.size){
                    liveImages.value!!.add(null)
                }
                liveImages.value = liveImages.value
            }
            for( i in idList.indices){
                async {
                    val queries = HashMap<String, String>()
                    queries.put(QUERY_ID, idList.get(i))
                    val res2 = remote.getProductByID(queries)
                    liveData.value?.set(i, res2.body())
                    liveData.value = liveData.value
                    callBack.invoke(i, res2.body()) // Used for retrieve cover image
                }
            }
        }
    }

    suspend fun getImageBytesById(imageId: String): Drawable? {
        val res = remote.getImageByID(imageId)
        if (res.body() != null && !res.body()!!.image.isNullOrEmpty()) {
            var base64String = res.body()!!.image
            val start = if (base64String!!.indexOf(",") == -1)  0 else base64String!!.indexOf(",")  + 1
            base64String = base64String!!.substring(start)
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            val d = BitmapFactory.decodeByteArray(imageBytes, 0 , imageBytes.size)
            return BitmapDrawable(d)
        }
        return null
    }



}