package com.loicteyssierdev.bigburger.api

import android.util.Log
import com.loicteyssierdev.bigburger.model.Product
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val TAG = "ApiInstance"

object ApiInstance {

    private val api: ProductsApi by lazy {
        Retrofit.Builder().baseUrl("https://uad.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    suspend fun getProducts(onSuccess: (List<Product>) -> Unit) {
        callApi({ api.getProducts() }) {
            onSuccess(it)
        }
    }

    private suspend fun <T> callApi(apiCall: suspend () -> Response<T>, onSuccess: (T) -> Unit) {
        val resp = try {
            apiCall()
        } catch (e: IOException) {
            Log.e(TAG, "IOException$e")
            return
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException")
            return
        }
        if (resp.isSuccessful && resp.body() != null) {
            resp.body()?.let {
                onSuccess(it)
            }
        } else {
            Log.e(TAG, "Response not successful")
        }
    }
}