package com.uoltest.rodrigo.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    val profileImage: String = String(),
    val profileLink: String = String(),
    val phone: String = String(),
    val email: String = String(),
    val name: String = String(),
    val id: String = String(),
    val status: String = String()
): Parcelable {

    fun requiredProperties(): Boolean {
        return name.isNotEmpty() && status.isNotEmpty() && email.isNotEmpty() && id.isNotEmpty()
    }

}