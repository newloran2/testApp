package br.com.testeuol.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.testeuol.R
import br.com.testeuol.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var bindingImpl: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingImpl = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setContentView(bindingImpl.root)
    }
}