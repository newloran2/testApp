package br.com.testeuol.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customers(
    @SerializedName("customers") val customers : ArrayList<Customer> = arrayListOf()
): Parcelable


@Parcelize
data class Customer(
    @SerializedName("status") val status: String? = "",
    @SerializedName("profileImage") val profileImage: String? = "",
    @SerializedName("profileLink") val profileLink: String? = "",
    @SerializedName("phone") val phone: String? = "",
    @SerializedName("email") val email: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("id") val id: String? = ""
) : Parcelable