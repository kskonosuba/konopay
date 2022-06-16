package com.konosuba.pay

import android.content.Context

class Fruit(
    var name: String,
    var image: String,
    var content: String
) {
    fun getImageId(context: Context): Int {
        return context.resources.getIdentifier(image, "drawable", context.packageName)
    }
}