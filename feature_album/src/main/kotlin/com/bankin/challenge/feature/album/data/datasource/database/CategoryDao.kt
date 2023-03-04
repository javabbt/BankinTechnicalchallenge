package com.bankin.challenge.feature.album.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bankin.challenge.feature.album.data.datasource.database.model.CategoryEntityModel

@Dao
internal interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntityModel>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getSubCategories(id: Int): List<CategoryEntityModel>?

    @Query("SELECT * FROM categories where id = :id")
    suspend fun getCategory(id: Int): CategoryEntityModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntityModel>)
}
