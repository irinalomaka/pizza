package com.nennos.pizza.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.ui.adapters.listeners.OnCartClickedListener
import com.nennos.pizza.ui.adapters.models.CartItem
import kotlinx.android.synthetic.main.li_cart.view.*

class CartListAdapter(val listener: OnCartClickedListener) :
    RecyclerView.Adapter<CartListAdapter.DrinkViewHolder>() {

    private val content = mutableListOf<CartItem>()

    fun submitList(list: List<CartItem>) {
        content.clear()
        content.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = content.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(content[position])
    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CartItem) {
            itemView.ingredientTitle.text = item.name
            itemView.ingredientPrice.text = "$${item.price}"
            itemView.deleteOrderBtn.setOnClickListener {
                listener.onCartClicked(
                    item.id,
                    item.orderType
                )
            }
        }
    }
}