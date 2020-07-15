package com.nennos.pizza.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nennos.pizza.R
import com.nennos.pizza.ui.adapters.listeners.OnPizzaClickedListener
import com.nennos.pizza.ui.adapters.models.PizzaItem
import kotlinx.android.synthetic.main.li_pizza.view.*
import kotlinx.android.synthetic.main.btn_pizza_price.view.*


class PizzaListAdapter(val listener: OnPizzaClickedListener?) :
    RecyclerView.Adapter<PizzaListAdapter.PizzaViewHolder>() {

    private val content = mutableListOf<PizzaItem>()

    fun submitList(list: List<PizzaItem>) {
        content.clear()
        content.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        return PizzaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_pizza,
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

        fun bind(pizzaItem: PizzaItem) {
            itemView.pizzaNameTextView.text = pizzaItem.name
            itemView.pizzaIngredientsTextView.text = pizzaItem.ingredientsText
            itemView.pizzaPriceTextView.text = "$${pizzaItem.price}"

            Glide.with(itemView.context)
                .load(pizzaItem.url)
                .placeholder(R.mipmap.custom)
                .into(itemView.pizzaImageView)

            itemView.pizzaPriceBtn.setOnClickListener {
                listener?.onPizzaAddClicked(pizzaItem)
            }

            itemView.pizzaItemHolder.setOnClickListener{listener?.onPizzaEditClicked(pizzaItem)}
        }
    }
}