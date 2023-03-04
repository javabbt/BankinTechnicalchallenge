package com.bankin.challenge.feature.album.data

import androidx.room.Room
import com.bankin.challenge.feature.album.data.datasource.api.service.CategoryRetrofitService
import com.bankin.challenge.feature.album.data.datasource.database.CategoryDatabase
import com.bankin.challenge.feature.album.data.repository.AlbumRepositoryImpl
import com.bankin.challenge.feature.album.domain.repository.AlbumRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<AlbumRepository> { AlbumRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(CategoryRetrofitService::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            CategoryDatabase::class.java,
            "Categories.db",
        ).build()
    }

    single { get<CategoryDatabase>().albums() }
}
