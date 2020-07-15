package com.nennos.pizza.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.ui.adapters.listeners.OnIngredientClickedListener
import com.nennos.pizza.ui.adapters.models.IngredientItem
import kotlinx.android.synthetic.main.li_ingredient.view.*


class PizzaAdapter(val listener: OnIngredientClickedListener) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private val content = mutableListOf<IngredientItem>()

    fun submitList(list: List<IngredientItem>) {
        content.clear()
        content.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        return PizzaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_ingredient,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = content.size

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(content[position])
    }

    inner class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ingredient: IngredientItem) {
            itemView.ingredientTitle.text = ingredient.name
            itemView.ingredientPrice.text = "$${ingredient.price}"
            itemView.addIngredientCb.isChecked = ingredient.isChecked
            itemView.addIngredientCb.setOnCheckedChangeListener { compoundButton, b ->
                listener.onIngredientClicked(
                    ingredient,
                    b
                )
            }
        }
    }
}