package com.nennos.pizza.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.db.models.DrinkLocal
import com.nennos.pizza.ui.adapters.listeners.OnDrinkClickedListener
import kotlinx.android.synthetic.main.li_drink.view.*

class DrinkListAdapter(val listener: OnDrinkClickedListener) :
    RecyclerView.Adapter<DrinkListAdapter.DrinkViewHolder>() {

    private val content = mutableListOf<DrinkLocal>()

    fun submitList(list: List<DrinkLocal>) {
        content.clear()
        content.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_drink,
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

        fun bind(drinkLocal: DrinkLocal) {
            itemView.drinkTitle.text = drinkLocal.name
            itemView.drinkPrice.text = "$${drinkLocal.price}"

            itemView.addOrderBtn.setOnClickListener { listener.onDrinkClicked(drinkLocal) }
        }

    }
}