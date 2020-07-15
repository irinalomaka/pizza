package com.nennos.pizza.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nennos.pizza.db.models.DrinkOrderLocal
import com.nennos.pizza.db.models.PizzaOrderLocal
import com.nennos.pizza.ui.adapters.models.CartItem
import com.nennos.pizza.utils.convertDrinkToCartItem
import com.nennos.pizza.utils.convertPizzaToCartItem
import com.nennos.pizza.utils.convertToIds
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val pizzaListLiveData = repository.getPizzaOrderList()
    private val drinkListLiveData = repository.getDrinkOrderList()

    val showProgressBarLiveData = MutableLiveData<Boolean>()
    val enableSentCartLiveData = MutableLiveData<Boolean>()
    val showThanksViewLiveData = MutableLiveData(false)

    private val orderItemListLiveData = MediatorLiveData<List<CartItem>>().apply {
        val pizzaResult = mutableListOf<PizzaOrderLocal>()
        val drinkResult = mutableListOf<DrinkOrderLocal>()

        fun combine() {
            val cartItems = mutableListOf<CartItem>()
            cartItems.addAll(pizzaResult.convertPizzaToCartItem())
            cartItems.addAll(drinkResult.convertDrinkToCartItem())
            value = cartItems
            enableSentCartLiveData.value = cartItems.size == 0
        }

        addSource(pizzaListLiveData) {
            pizzaResult.clear()
            pizzaResult.addAll(it)
            combine()
        }
        addSource(drinkListLiveData) {
            drinkResult.clear()
            drinkResult.addAll(it)
            combine()
        }
    }

    val orderList: LiveData<List<CartItem>> = orderItemListLiveData

    var totalOrderPrice = Transformations.map(orderList) {
        var price = 0.0
        for (item in it) {
            price += item.price
        }

        price
    }

    fun onCartClicked(id: Long, type: Int) {
        when (type) {
            0 -> execute(Runnable { repository.removePizzaFromOrderList(id) })
            1 -> execute(Runnable { repository.removeDrinkFromOrderList(id) })
        }
    }

    fun sendCartClicked() {
        showProgressBarLiveData.value = true
        addDisposable(
            repository.createOrder(
                drinkListLiveData.value?.convertToIds() ?: emptyList(),
                pizzaListLiveData.value ?: emptyList()
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    execute(Runnable { repository.removeAllOrders() })
                    showProgressBarLiveData.value = false
                    showThanksViewLiveData.value = true
                }, {
                    showProgressBarLiveData.value = false
                    showThanksViewLiveData.value = false
                })
        )
    }
}