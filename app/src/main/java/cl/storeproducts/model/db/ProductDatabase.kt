package cl.storeproducts.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){

    abstract fun getProductDao(): DAOProduct

    companion object{
        //singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ProductDatabase?= null

        fun getDatabase(context: Context): ProductDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java, "product_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}