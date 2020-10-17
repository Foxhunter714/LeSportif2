package cl.storeproducts.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOProduct {
    //Guarda y recupera informacion desde la entidad
    @Query("SELECT * FROM product_table")
    fun getAllProduct() : LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(quoteList: List<ProductEntity>)

    @Query("SELECT id, name, price, image FROM product_table")
    fun getMinimalProduct() : LiveData<List<ProductMini>>

    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getProduct(id: Int) : LiveData<ProductEntity>
}