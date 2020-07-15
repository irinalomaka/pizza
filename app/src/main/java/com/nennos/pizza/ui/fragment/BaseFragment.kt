package com.nennos.pizza.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nennos.pizza.ui.viewmodels.MainActivityViewModel

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        setupOrderCounter()
    }

    private fun setupOrderCounter() {
        activity?.let {
            val viewModel = ViewModelProviders.of(it).get(MainActivityViewModel::class.java)
            viewModel.setCartCounterVisibility(isShowCounter())
        }
    }

    abstract fun isShowCounter(): Boolean

}