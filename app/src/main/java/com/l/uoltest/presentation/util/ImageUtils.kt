package com.l.uoltest.presentation.util

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.l.uoltest.R
import com.stfalcon.imageviewer.StfalconImageViewer

fun RequestManager.loadImage(
    imageUrl: String?,
    imageView: ImageView,
    transformation: BitmapTransformation = CenterCrop(),
    width: Int = 500,
    height: Int = 300
) {
    load(imageUrl)
        .transform(
            transformation.takeIf { it is CenterCrop }
                ?:
                MultiTransformation(CenterCrop(), transformation)
        )
        .override(width, height)
        .error(R.drawable.ic_person)
        .placeholder(R.drawable.ic_person)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
        .waitForLayout()
}

fun ImageView.showImage(context: Context, imageUrl: String) {
    StfalconImageViewer.Builder(context, listOf(imageUrl)) { view, image ->
        Glide.with(context).load(image).error(R.drawable.ic_person).into(view)
    }
        .withBackgroundColor(ContextCompat.getColor(context, R.color.black))
        .allowZooming(true)
        .allowSwipeToDismiss(true)
        .withTransitionFrom(this)
        .show()
}