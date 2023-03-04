package com.bankin.challenge.feature.album.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bankin.challenge.feature.album.data.datasource.api.model.ParentTypeConverter
import com.bankin.challenge.feature.album.data.datasource.database.model.CategoryEntityModel

@Database(entities = [CategoryEntityModel::class], version = 1, exportSchema = false)
@TypeConverters(ParentTypeConverter::class)
internal abstract class CategoryDatabase : RoomDatabase() {

    abstract fun albums(): CategoryDao
}
