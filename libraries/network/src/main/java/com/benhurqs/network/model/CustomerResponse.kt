package com.benhurqs.network.model

import java.io.Serializable

class CustomerResponse: Serializable {
    val status: String? = null
    val profileImage: String? = null
    val profileLink: String? = null
    var phone: String? = null
        get() = manageEmptyField(field)
    var email: String? = null
        get() = manageEmptyField(field)
    var name: String? = null
        get() = manageEmptyField(field)
    val id: String? = null

    private fun manageEmptyField(field: String?): String?{
        if(field.isNullOrEmpty()){
            return "---"
        }

        return field
    }
}