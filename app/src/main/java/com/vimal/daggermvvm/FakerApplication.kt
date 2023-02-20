/**
 * Created by Vimal on Feb-2023.
 */
package com.vimal.daggermvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class FakerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}