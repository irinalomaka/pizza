package com.nennos.pizza.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.databinding.FrMainBinding
import com.nennos.pizza.ui.adapters.PizzaListAdapter
import com.nennos.pizza.ui.adapters.listeners.OnPizzaClickedListener
import com.nennos.pizza.ui.adapters.models.PizzaItem
import com.nennos.pizza.ui.viewmodels.MainFragmentViewModel
import kotlinx.android.synthetic.main.fr_main.*


class MainFragment : BaseFragment(), OnPizzaClickedListener {

    private var viewModel: MainFragmentViewModel? = null
    lateinit var binding: FrMainBinding

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fr_main, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = PizzaListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        fabBtn?.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(PizzaFragment.BASE_PRICE_KEY, viewModel?.getBasePrice() ?: 0.0)
            Navigation.findNavController(view).navigate(R.id.pizzaFragment, bundle)
        }
    }

    override fun isShowCounter() = true

    override fun onPizzaAddClicked(pizzaItem: PizzaItem) {
        viewModel?.onPizzaClicked(pizzaItem)
    }

    override fun onPizzaEditClicked(pizzaItem: PizzaItem) {
        val bundle = Bundle()
        bundle.putDouble(PizzaFragment.BASE_PRICE_KEY, viewModel?.getBasePrice() ?: 0.0)
        bundle.putString(PizzaFragment.IMAGE_URL_KEY, pizzaItem.url)
        bundle.putString(PizzaFragment.ID_KEY, pizzaItem.name)
        bundle.putIntegerArrayList(
            PizzaFragment.INGREDIENTS_KEY,
            ArrayList(pizzaItem.ingredientsId)
        )
        Navigation.findNavController(binding.root).navigate(R.id.pizzaFragment, bundle)
    }

}