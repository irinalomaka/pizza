package com.nennos.pizza.ui.bindings

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.db.models.DrinkLocal
import com.nennos.pizza.ui.adapters.CartListAdapter
import com.nennos.pizza.ui.adapters.DrinkListAdapter
import com.nennos.pizza.ui.adapters.PizzaAdapter
import com.nennos.pizza.ui.adapters.PizzaListAdapter
import com.nennos.pizza.ui.adapters.models.CartItem
import com.nennos.pizza.ui.adapters.models.IngredientItem
import com.nennos.pizza.ui.adapters.models.PizzaItem

@BindingAdapter("app:pizzaList")
fun submitPizzaList(recyclerView: RecyclerView, list: List<PizzaItem>?) {
    (recyclerView.adapter as PizzaListAdapter).submitList(list ?: emptyList())
}

@BindingAdapter("app:drinkList")
fun submitDrinkList(recyclerView: RecyclerView, list: List<DrinkLocal>?) {
    (recyclerView.adapter as DrinkListAdapter).submitList(list ?: emptyList())
}

@BindingAdapter("app:ingredientList")
fun submitIngredientList(recyclerView: RecyclerView, list: List<IngredientItem>?) {
    (recyclerView.adapter as PizzaAdapter).submitList(list ?: emptyList())
}

@BindingAdapter("app:isShowCartSize")
fun orderCounter(textView: TextView, isShow: Boolean) {
    textView.visibility = if (isShow) View.VISIBLE else View.GONE
}

@BindingAdapter("app:orderList")
fun submitCartList(recyclerView: RecyclerView, list: List<CartItem>?) {
    (recyclerView.adapter as CartListAdapter).submitList(list ?: emptyList())
}

@BindingAdapter("app:totalOrderPrice")
fun totalOrderPrice(textView: TextView, price: Double?) {
    textView.text = textView.context.resources.getString(R.string.checkout) + " ($${price ?: 0})"
}

@BindingAdapter("app:totalPizzaPrice")
fun totalPizzaPrice(textView: TextView, price: Double?) {
    textView.text = textView.context.resources.getString(R.string.add_to_card) + " ($${price ?: 0})"
}
