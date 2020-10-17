package cl.storeproducts.model

import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {
    @GET("details/")
    fun listProduct(): Call<Product>
}