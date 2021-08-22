package com.iremdogan.fooddeliveryproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeliveryApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}