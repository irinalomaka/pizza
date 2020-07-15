package com.nennos.pizza.ui.viewmodels

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nennos.pizza.db.models.DrinkLocal
import io.reactivex.schedulers.Schedulers

class DrinkFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val handler = Handler()

    private var drinkListLiveData = repository.getDrinkList()
    val addDrinkToCartView = MutableLiveData<Boolean>()

    val drinkList: LiveData<List<DrinkLocal>> = drinkListLiveData

    init {
        addDisposable(
            repository.fetchDrinksList().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()).subscribe(
                    { execute(Runnable { repository.saveDrinkList(it) }) },
                    {})
        )
    }

    fun onDrinkClicked(drinkLocal: DrinkLocal) {
        execute(Runnable { repository.addDrinkToOrder(drinkLocal) })
        handler.removeCallbacksAndMessages(null)
        addDrinkToCartView.value = true
        handler.postDelayed({ addDrinkToCartView.value = false }, DELAY)
    }

    override fun onCleared() {
        handler.removeCallbacksAndMessages(null)
        super.onCleared()
    }
}