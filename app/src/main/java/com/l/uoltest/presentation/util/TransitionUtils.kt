package com.l.uoltest.presentation.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView

const val TRANSITION_NAME = "TRANSITION_NAME"
const val TRANSITION_EMAIL = "TRANSITION_EMAIL"
const val TRANSITION_PHONE = "TRANSITION_PHONE"
const val TRANSITION_PICTURE = "TRANSITION_PICTURE"
const val TRANSITION_BACKGROUND = "TRANSITION_BACKGROUND"

data class SharedViews(
    val tvName: TextView,
    val tvEmail: TextView,
    val tvPhone: TextView,
    val imgProfile: ImageView,
    val background: View
)
