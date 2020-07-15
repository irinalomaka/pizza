package com.nennos.pizza.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    private val pizzaOrderListLiveData = repository.getPizzaOrderList()
    private val drinkOrderListLiveData = repository.getDrinkOrderList()
    private val isShowCartSizeLiveData = MutableLiveData(false)

    private val cardSizeLiveData = MediatorLiveData<String>().apply {
        var pizzaResult = 0
        var drinkResult = 0

        fun combine() {
            value = (pizzaResult + drinkResult).toString()
        }

        addSource(pizzaOrderListLiveData) {
            pizzaResult = it.size
            combine()
        }
        addSource(drinkOrderListLiveData) {
            drinkResult = it.size
            combine()
        }
    }

    //External params
    val cardSize: LiveData<String> = cardSizeLiveData
    val isShowCartSize: LiveData<Boolean> = isShowCartSizeLiveData

    fun setCartCounterVisibility(isShow: Boolean) {
        isShowCartSizeLiveData.value = isShow
    }

}