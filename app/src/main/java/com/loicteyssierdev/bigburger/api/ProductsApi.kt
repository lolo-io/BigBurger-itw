package com.loicteyssierdev.bigburger.api

import com.loicteyssierdev.bigburger.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("/bigburger")
    suspend fun getProducts(): Response<List<Product>>

}