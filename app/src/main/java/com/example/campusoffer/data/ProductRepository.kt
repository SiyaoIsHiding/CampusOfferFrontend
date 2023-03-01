package com.example.campusoffer.data

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
    suspend fun getListProducts(queries : Map<String, String>, liveData: MutableLiveData<List<Product?>>){
        MainScope().launch {
            val res1 = remote.getProductsUnderCategory(queries)
            var idList = listOf<String>()
            if (!res1.body()?.productId.isNullOrEmpty()) {
                idList = res1.body()!!.productId
            }
            val runningTasks = idList.map { id ->
                async {
                    val queries = HashMap<String, String>()
                    queries.put(QUERY_ID, id)
                    val res2 = remote.getProductByID(queries)
                    id to res2
                }
            }
            runningTasks.awaitAll()
            val products : List<Product?> = runningTasks.map {
                res -> res.getCompleted().second.body()
            }
            liveData.postValue(products)
        }
    }
}