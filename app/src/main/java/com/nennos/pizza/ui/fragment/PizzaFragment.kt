package com.nennos.pizza.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nennos.pizza.R
import com.nennos.pizza.databinding.FrPizzaBinding
import com.nennos.pizza.ui.adapters.PizzaAdapter
import com.nennos.pizza.ui.adapters.listeners.OnIngredientClickedListener
import com.nennos.pizza.ui.adapters.models.IngredientItem
import com.nennos.pizza.ui.viewmodels.PizzaFragmentViewModel
import kotlinx.android.synthetic.main.fr_pizza.*


class PizzaFragment : BaseFragment(), OnIngredientClickedListener {

    companion object {
        const val BASE_PRICE_KEY = "basePriceKey"
        const val INGREDIENTS_KEY = "ingredientsKey"
        const val IMAGE_URL_KEY = "imageUrlKey"
        const val ID_KEY = "idKey"
    }

    private var viewModel: PizzaFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PizzaFragmentViewModel::class.java)
        viewModel?.setPizzaData(
            arguments?.getString(ID_KEY) ?: "Custom Pizza",
            arguments?.getDouble(BASE_PRICE_KEY) ?: 0.0,
            arguments?.getString(IMAGE_URL_KEY) ?: "",
            arguments?.getIntegerArrayList(INGREDIENTS_KEY) ?: ArrayList<Int>()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FrPizzaBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_pizza, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = PizzaAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        addCustomPizzaToCart.setOnClickListener { viewModel?.addPizza() }

        Glide.with(view.context).load(viewModel?.imageUrl).placeholder(R.mipmap.custom)
            .into(pizzaImage)
    }

    override fun isShowCounter() = false

    override fun onIngredientClicked(ingredient: IngredientItem, isAdd: Boolean) {
        viewModel?.updateIngredients(ingredient, isAdd)
    }
}