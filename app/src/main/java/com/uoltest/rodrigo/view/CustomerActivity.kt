package com.uoltest.rodrigo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.uoltest.rodrigo.databinding.CustomerActivityBinding
import com.uoltest.rodrigo.model.repository.CustomerRepository
import com.uoltest.rodrigo.viewmodel.CustomerViewModel
import com.uoltest.rodrigo.viewmodel.CustomerViewModelFactory

class CustomerActivity : AppCompatActivity() {

    lateinit var viewModel: CustomerViewModel
    private lateinit var binding: CustomerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        binding = CustomerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()

        val viewModelFactory =
            CustomerViewModelFactory(app = application, repository = CustomerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[CustomerViewModel::class.java]

    }
    private fun initToolbar() {
        val navHostFragment = (supportFragmentManager.findFragmentById(binding.customerNavHostFragment.id)) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }
}