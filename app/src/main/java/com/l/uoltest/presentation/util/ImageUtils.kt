package com.l.uoltest.presentation.util

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.l.uoltest.R


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
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
        .waitForLayout()
}