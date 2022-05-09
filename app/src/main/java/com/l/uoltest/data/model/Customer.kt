package com.l.uoltest.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    val status: String = "",
    val profileImage: String? = null,
    val profileLink: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val name: String? = null,
    val id: String = ""
) : Parcelable
