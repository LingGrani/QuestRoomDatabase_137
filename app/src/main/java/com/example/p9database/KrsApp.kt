package com.example.p9database

import android.app.Application
import com.example.p9database.depedenciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp
    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}