package com.bankin.challenge.app

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.bankin.challenge.BuildConfig
import com.bankin.challenge.appModule
import com.bankin.challenge.base.baseModule
import com.bankin.challenge.feature.album.featureAlbumModules
import com.bankin.challenge.feature.profile.featureProfilesModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class ShowcaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
        initDynamicColorScheme()
    }

    private fun initDynamicColorScheme() {
        // Apply dynamic colors to all Activities, Fragments, Views
        // (Material 3 library helper class)
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@ShowcaseApplication)

            modules(appModule)
            modules(baseModule)
            modules(featureAlbumModules)
            modules(featureProfilesModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
