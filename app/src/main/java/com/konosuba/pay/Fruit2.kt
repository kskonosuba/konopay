package com.konosuba.pay

import android.content.Context

class Fruit2(
    var name: String,
    var image: String,
    var content: String,
    val couponNum: String
) {
    fun getImageId(context: Context): Int {
        return context.resources.getIdentifier(image, "drawable", context.packageName)
    }
}