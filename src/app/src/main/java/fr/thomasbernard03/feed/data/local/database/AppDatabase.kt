package fr.thomasbernard03.feed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.thomasbernard03.feed.data.local.dao.ProductDao
import fr.thomasbernard03.feed.data.local.entities.ProductEntity

@Database(entities = [
    ProductEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}