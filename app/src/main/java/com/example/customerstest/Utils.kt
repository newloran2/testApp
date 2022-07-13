package com.example.customerstest

object Utils {

    fun getGhostImage(brokenImgUrl: String): Int {
        when {
            brokenImgUrl.contains("macaco1") -> return R.drawable.macaco1
            brokenImgUrl.contains("macaco2") -> return R.drawable.macaco2
            brokenImgUrl.contains("macaco3") -> return R.drawable.macaco3
            brokenImgUrl.contains("macaco4") -> return R.drawable.macaco4
            brokenImgUrl.contains("macaco5") -> return R.drawable.macaco5
            brokenImgUrl.contains("macaco6") -> return R.drawable.macaco6
            brokenImgUrl.contains("macaco7") -> return R.drawable.macaco7
            brokenImgUrl.contains("macaco8") -> return R.drawable.macaco8
            brokenImgUrl.contains("macaco9") -> return R.drawable.macaco9
            brokenImgUrl.contains("macaco10") -> return R.drawable.macaco10
        }
        return R.drawable.uol_avatar_default
    }

}