package com.example.ucp2_075

import android.app.Application
import com.example.ucp2_075.dependeciesinjection.ContainerApp

class TokoApp : Application() {

    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)
    }
}