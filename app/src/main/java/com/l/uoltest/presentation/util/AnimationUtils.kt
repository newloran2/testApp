package com.l.uoltest.presentation.util

import androidx.annotation.RawRes
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.hideAnimation() {
    isVisible = false
    cancelAnimation()
}

fun LottieAnimationView.animate(@RawRes res: Int, show: Boolean = true) {
    isVisible = show
    if (show) {
        setAnimation(res)
        playAnimation()
    }
    else {
        cancelAnimation()
    }
}