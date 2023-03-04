package com.bankin.challenge.feature.album.presentation

import coil.ImageLoader
import com.bankin.challenge.feature.album.presentation.screen.categorydetail.SubCategoriesViewModel
import com.bankin.challenge.feature.album.presentation.screen.categorylist.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {

    // AlbumList
    viewModelOf(::CategoriesViewModel)

    single { ImageLoader(get()) }

    // AlbumDetails
    viewModelOf(::SubCategoriesViewModel)
}
