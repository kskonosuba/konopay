package com.konosuba.pay

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.layout_fruit.view.*

class FruitPagerAdapter3(private val list: ArrayList<Fruit2>): PagerAdapter() {



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_fruit, container, false)


        view.ivItem.setImageResource(list[position].getImageId(container.context))


        val database = Firebase.database
        val myRef = database.getReference("coupon_list")

        var value = ""



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                value = snapshot.getValue<String>()!!
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        view.ivItem.setOnClickListener {

           value = value + list[position].couponNum + "★" + list[position].content + "★" + list[position].image + "/"


            myRef.setValue(value)
            Toast.makeText(container.context, "성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
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