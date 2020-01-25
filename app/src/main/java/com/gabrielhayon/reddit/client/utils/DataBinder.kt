package com.gabrielhayon.reddit.client.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class DataBinder {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageFromUrl(imageView: ImageView, imageUrl: String?) {
            Picasso.get().
                load(imageUrl)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("textColorResId")
        fun setTextColorFromResId(textView: TextView, textColorResId: Int) {
            textView.setTextColor(textView.context.getColor(textColorResId))
        }
    }
}