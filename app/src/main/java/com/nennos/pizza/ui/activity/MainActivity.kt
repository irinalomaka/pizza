package com.nennos.pizza.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nennos.pizza.R
import com.nennos.pizza.databinding.AcMainBinding
import com.nennos.pizza.ui.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.ac_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val binding: AcMainBinding =
            DataBindingUtil.setContentView(this, R.layout.ac_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                return true
            }

            R.id.action_card -> {
                navController.navigate(R.id.cartFragment)
                return true
            }

            R.id.action_drink -> {
                navController.navigate(R.id.drinkFragment)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
