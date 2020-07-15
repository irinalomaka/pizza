package com.nennos.pizza

import android.app.Application
import com.nennos.pizza.repository.RepositoryApi
import com.nennos.pizza.repository.RepositoryImpl

class NennosApp : Application() {


    lateinit var repositoryApi: RepositoryApi

    companion object {
        lateinit var instance: NennosApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        repositoryApi = RepositoryImpl(this)
    }
}