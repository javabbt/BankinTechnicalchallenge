package com.bankin.challenge.feature.profile

import com.bankin.challenge.feature.profile.data.dataModule
import com.bankin.challenge.feature.profile.domain.domainModule
import com.bankin.challenge.feature.profile.presentation.presentationModule

val featureProfilesModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
