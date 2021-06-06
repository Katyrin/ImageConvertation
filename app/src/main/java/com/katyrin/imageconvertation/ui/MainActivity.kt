package com.katyrin.imageconvertation.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.katyrin.imageconvertation.R
import com.katyrin.imageconvertation.databinding.ActivityMainBinding
import com.katyrin.imageconvertation.presenter.MainPresenter
import com.katyrin.imageconvertation.presenter.MainView
import com.katyrin.imageconvertation.utils.showAlertDialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var disposable: Disposable? = null
    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter(ImageConverter(this), AndroidSchedulers.mainThread())
    }
    private var binding: ActivityMainBinding? = null
    private val activityLauncher = registerForActivityResult(MainActivityContract()) { data ->
        getImage(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun setImage(imageBitmap: Bitmap?) {
        if (imageBitmap != null)
            binding?.imageView?.setImageBitmap(imageBitmap)
    }

    override fun init() {
        binding?.chooseButton?.setOnClickListener {
            presenter.convertImage()
        }
        binding?.saveButton?.setOnClickListener {
            presenter.savePngImage()
        }
    }

    override fun openImage() {
        activityLauncher.launch(null)
    }

    private fun getImage(data: Intent?) {
        val imagePicker = ImagePicker()
        disposable = imagePicker.pick(data, contentResolver)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ bitmap ->
                presenter.setImage(bitmap)
            }, { throwable ->
                showAlertDialog(getString(R.string.error), throwable.localizedMessage)
            })
    }

    override fun showProgressConversion() {

    }

    override fun showSuccess() {
        println("showSuccess")
    }

    override fun hideProgressConversion() {

    }

    override fun showError() {
        println("Error")
    }

    override fun onDestroy() {
        binding = null
        if (disposable != null)
            disposable?.dispose()
        disposable = null
        super.onDestroy()
    }
}