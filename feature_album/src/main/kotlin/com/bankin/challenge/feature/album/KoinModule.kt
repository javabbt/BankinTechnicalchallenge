package com.bankin.challenge.feature.album

import com.bankin.challenge.feature.album.data.dataModule
import com.bankin.challenge.feature.album.domain.domainModule
import com.bankin.challenge.feature.album.presentation.presentationModule

val featureAlbumModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
