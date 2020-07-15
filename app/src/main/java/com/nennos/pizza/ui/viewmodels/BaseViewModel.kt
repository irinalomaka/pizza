package com.nennos.pizza.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nennos.pizza.NennosApp
import com.nennos.pizza.repository.RepositoryApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.Executors

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val repository: RepositoryApi = NennosApp.instance.repositoryApi

    private val compositeDisposable = CompositeDisposable()
    private val executor = Executors.newFixedThreadPool(5)

    companion object{
        const val DELAY : Long = 3000
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposable() {
        compositeDisposable.dispose()
    }

    fun execute(runnable: Runnable) {
        executor.execute(runnable)
    }

    override fun onCleared() {
        super.onCleared()
        clearDisposable()
    }
}