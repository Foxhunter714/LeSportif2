package cl.storeproducts.model.remote

import cl.storeproducts.model.remote.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {
    @GET("details/")
    fun listProduct(): Call<Product>
}