package com.benhurqs.actions

import android.content.Context
import android.content.Intent

object Actions {
    private fun internalIntent(context: Context?, action: String) = Intent(action).setPackage(context?.packageName)

    fun openDetail(context: Context,
                   status: String?,
                   profileImage: String?,
                   profileLink: String?,
                   phone: String?,
                   email: String?,
                   name: String?,
                   id: String?
    ) {
        val intent = internalIntent(context, "uol.action.customerdetail.open")
        intent.putExtra(SharedDefs.STATUS_COD, status)
        intent.putExtra(SharedDefs.PROFILE_IMG_COD, profileImage)
        intent.putExtra(SharedDefs.PROFILE_LINK_COD, profileLink)
        intent.putExtra(SharedDefs.PHONE_COD, phone)
        intent.putExtra(SharedDefs.EMAIL_COD, email)
        intent.putExtra(SharedDefs.NAME_COD, name)
        intent.putExtra(SharedDefs.ID_COD, id)
        context.startActivity(intent)
    }
}