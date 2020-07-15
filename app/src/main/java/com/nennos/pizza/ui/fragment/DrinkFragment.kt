package com.nennos.pizza.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.databinding.FrDrinkBinding
import com.nennos.pizza.db.models.DrinkLocal
import com.nennos.pizza.ui.adapters.DrinkListAdapter
import com.nennos.pizza.ui.adapters.listeners.OnDrinkClickedListener
import com.nennos.pizza.ui.viewmodels.DrinkFragmentViewModel


class DrinkFragment : BaseFragment(), OnDrinkClickedListener {

    private var viewModel: DrinkFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FrDrinkBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_drink, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = DrinkListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun isShowCounter() = false

    override fun onDrinkClicked(drinkLocal: DrinkLocal) {
        viewModel?.onDrinkClicked(drinkLocal)
    }
}