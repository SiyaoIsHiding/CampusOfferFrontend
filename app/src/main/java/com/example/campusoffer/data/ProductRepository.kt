package com.example.campusoffer.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductRepository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource
}