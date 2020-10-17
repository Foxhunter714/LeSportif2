package cl.storeproducts.model.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.storeproducts.model.remote.Product
import cl.storeproducts.model.remote.ProductItem
import cl.storeproducts.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    var productDatabase = ProductDatabase.getDatabase(context)
    var listProduct = productDatabase.getProductDao().getMinimalProduct()


    fun loadApiData() {
        val call = RetrofitClient.retrofitInstance().listProduct()

        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.d("Adapter", "${response.code()}")
                Log.d("Adapter", "${response.body()}")
                saveDatabase(quoteConverter(response.body()!!))
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("Adapter", "Error al cargar productos $t")
            }
        })
    }

    fun quoteConverter(listProduct: List<ProductItem>): List<ProductEntity> {
        return listProduct.map { product -> ProductEntity(product.id, product.name, product.price, product.image) }
    }

    fun saveDatabase(listProductEntity: List<ProductEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDatabase.getProductDao().insertProduct(listProductEntity)

        }
    }

    fun getDetails(param1: String): LiveData<ProductEntity> {
        return productDatabase.getProductDao().getProduct(param1.toInt())
    }
}