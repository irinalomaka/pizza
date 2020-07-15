package com.nennos.pizza.ui.viewmodels

import android.app.Application
import android.os.Handler
import androidx.lifecycle.*
import com.nennos.pizza.db.models.IngredientLocal
import com.nennos.pizza.db.models.PizzaLocal
import com.nennos.pizza.ui.adapters.models.PizzaItem
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val handler = Handler()

    private var basePrice = 0.0
    private val pizzaListLiveData = repository.getPizzaList()
    private val ingredientsListLiveData = repository.getIngredientList()

    private val pizzaItemListLiveData = MediatorLiveData<List<PizzaItem>>().apply {
        val pizzaResult = mutableListOf<PizzaLocal>()
        val ingredientResult = mutableListOf<IngredientLocal>()

        fun combine() {
            val pizzaItems = mutableListOf<PizzaItem>()
            for (pizza in pizzaResult) {
                val pizzaIngredients = mutableListOf<IngredientLocal>()
                for (ingredientId in pizza.ingredientIds) {
                    for (ingredient in ingredientResult) {
                        if (ingredient.id == ingredientId) {
                            pizzaIngredients.add(ingredient)
                        }
                    }
                }
                pizzaItems.add(PizzaItem(pizza, pizzaIngredients))
            }

            if (pizzaItems.size > 0) {
                basePrice = pizzaItems[0].basePrice
            }

            value = pizzaItems
        }

        addSource(pizzaListLiveData) {
            pizzaResult.clear()
            pizzaResult.addAll(it)
            combine()
        }
        addSource(ingredientsListLiveData) {
            ingredientResult.clear()
            ingredientResult.addAll(it)
            combine()
        }
    }

    private val addToCartLiveData = MutableLiveData<Boolean>()

    val pizzaItemList: LiveData<List<PizzaItem>> = pizzaItemListLiveData
    val addToCartView: LiveData<Boolean> = addToCartLiveData


    init {
        addDisposable(
            repository.fetchPizzasList().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()).subscribe(
                    { execute(Runnable { repository.savePizzaList(it) }) },
                    {})
        )

        addDisposable(
            repository.fetchIngredientList().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({ execute(Runnable { repository.saveIngredientList(it) }) }, {})
        )
    }

    fun getBasePrice(): Double {
        return basePrice
    }

    fun onPizzaClicked(pizzaItem: PizzaItem) {
        execute(Runnable { repository.addPizzaToOrder(pizzaItem) })
        handler.removeCallbacksAndMessages(null)
        addToCartLiveData.value = true
        handler.postDelayed({ addToCartLiveData.value = false }, DELAY)
    }

    override fun onCleared() {
        handler.removeCallbacksAndMessages(null)
        super.onCleared()
    }
}