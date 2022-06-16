package com.konosuba.pay

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.layout_fruit.view.*

class FruitPagerAdapter2(private val list: ArrayList<Fruit>): PagerAdapter() {



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_fruit, container, false)


        val sharedPreferences = container.context.getSharedPreferences("preferences", 0)

        var gifticonList = sharedPreferences.getString("gifticon", "")!!

        view.ivItem.setImageResource(list[position].getImageId(container.context))

        view.ivItem.setOnClickListener {
            gifticonList = gifticonList + list[position].image + "★" + list[position].content + "/"
            sharedPreferences.edit().putString("gifticon", gifticonList).apply()
            val intent = Intent(container.context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            container.context.startActivity(intent)
        }



        list[position].content


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }
}