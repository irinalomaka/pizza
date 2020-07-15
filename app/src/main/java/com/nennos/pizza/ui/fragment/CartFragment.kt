package com.nennos.pizza.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nennos.pizza.R
import com.nennos.pizza.databinding.FrCartBinding
import com.nennos.pizza.ui.activity.ThanksActivity
import com.nennos.pizza.ui.adapters.CartListAdapter
import com.nennos.pizza.ui.adapters.listeners.OnCartClickedListener
import com.nennos.pizza.ui.viewmodels.CartFragmentViewModel
import kotlinx.android.synthetic.main.fr_cart.*

class CartFragment : BaseFragment(), OnCartClickedListener {

    private var viewModel: CartFragmentViewModel? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_card, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CartFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FrCartBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_cart, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = CartListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        sendCartHolder.setOnClickListener { viewModel?.sendCartClicked() }

        viewModel?.showThanksViewLiveData?.observe(
            this,
            Observer<Boolean> { showThanksScreen(it) })
    }

    override fun onCartClicked(id: Long, type: Int) {
        viewModel?.onCartClicked(id, type)
    }

    override fun isShowCounter() = false

    private fun showThanksScreen(isShow: Boolean) {
        if (isShow) startActivity(Intent(context, ThanksActivity::class.java))
    }
}