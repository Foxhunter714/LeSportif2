package cl.storeproducts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.storeproducts.model.db.ProductEntity
import cl.storeproducts.model.db.Repository


class ProductViewModel(application: Application): AndroidViewModel(application) {
    private var repository: Repository = Repository(application)
    var listProduct = repository.listProduct

    init {
        repository.loadApiData()

    }

    fun getDetails(param1: String): LiveData<ProductEntity> {
        return repository.getDetails(param1)
    }
}