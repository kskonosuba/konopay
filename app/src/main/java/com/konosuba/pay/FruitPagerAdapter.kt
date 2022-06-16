package com.konosuba.pay

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.layout_fruit.view.*

class FruitPagerAdapter(private val list: ArrayList<Fruit>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_fruit, container, false)


        view.ivItem.setImageResource(list[position].getImageId(container.context))



        list[position].content

        if(list[position].name == "add")
        {
            view.ivItem.setOnClickListener {
//                Toast.makeText(container.context, "클릭", Toast.LENGTH_SHORT).show()
                val intent = Intent(container.context, CardAddActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                container.context.startActivity(intent)
            }
        }
        else if(list[position].name == "samsungpay")
        {
            view.ivItem.setOnClickListener {
                try{
                val intent = container.context.packageManager.getLaunchIntentForPackage("com.samsung.android.spay")
                container.context.startActivity(intent)

            }catch (e : Exception)
            {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("market://details?id=com.samsung.android.spay")
                container.context.startActivity(intent)
                Toast.makeText(container.context, "삼성페이 어플리케이션이 설치되어 있지 않습니다. 스토어로 이동합니다.", Toast.LENGTH_SHORT).show()
            }
            }
        }
        else if(list[position].name == "npay")
        {
            view.ivItem.setOnClickListener {
                try{
                    val intent = container.context.packageManager.getLaunchIntentForPackage("com.naverfin.payapp")
                    container.context.startActivity(intent)

                }catch (e : Exception)
                {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.data = Uri.parse("market://details?id=com.naverfin.payapp")
                    container.context.startActivity(intent)
                    Toast.makeText(container.context, "네이버페이 어플리케이션이 설치되어 있지 않습니다. 스토어로 이동합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if(list[position].name == "payco")
        {
            view.ivItem.setOnClickListener {
                try{
                    val intent = container.context.packageManager.getLaunchIntentForPackage("com.nhnent.payapp")
                    container.context.startActivity(intent)

                }catch (e : Exception)
                {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.data = Uri.parse("market://details?id=com.nhnent.payapp")
                    container.context.startActivity(intent)
                    Toast.makeText(container.context, "페이코 어플리케이션이 설치되어 있지 않습니다. 스토어로 이동합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if(list[position].name == "kakaopay")
        {
            view.ivItem.setOnClickListener {
                try{
                    val intent = container.context.packageManager.getLaunchIntentForPackage("com.kakaopay.app")
                    container.context.startActivity(intent)

                }catch (e : Exception)
                {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.data = Uri.parse("market://details?id=com.kakaopay.app")
                    container.context.startActivity(intent)
                    Toast.makeText(container.context, "카카오페이 어플리케이션이 설치되어 있지 않습니다. 스토어로 이동합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            view.ivItem.setOnClickListener {
                val intent = Intent(container.context, GifticonUseActivity::class.java)
                intent.putExtra("num", list[position].content)
                container.context.startActivity(intent)
            }

        }



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