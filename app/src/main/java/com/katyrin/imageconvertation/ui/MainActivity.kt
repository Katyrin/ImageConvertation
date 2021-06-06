package com.katyrin.imageconvertation.ui

import android.os.Bundle
import com.katyrin.imageconvertation.databinding.ActivityMainBinding
import com.katyrin.imageconvertation.presenter.MainPresenter
import com.katyrin.imageconvertation.presenter.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val presenter: MainPresenter by moxyPresenter { MainPresenter() }
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun init() {

    }

    override fun openImage() {

    }

    override fun convertImage() {

    }

    override fun saveImage() {

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}