package com.myapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.myapplication.R
import com.myapplication.network.ApiService


/**
 * This class's method will be used for adapter class.
 */


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(ApiService.BASE_URL + url){
            error(R.drawable.ic_default)
        }
    }
}

