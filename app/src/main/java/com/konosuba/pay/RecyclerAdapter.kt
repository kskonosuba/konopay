package com.konosuba.pay

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_setting.view.*

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    var datas = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ItemViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {

            //RecyclerView 관련 처리
            //ex) itemView.textView.text = item.trafficDistance
            //"itemView" 사용


            //        view.ivItem.setImageResource(list[position].getImageId(container.context))


            itemView.imageView7.setImageResource(context.resources.getIdentifier(item.image, "drawable", context.packageName))

            itemView.textView3.text = item.name

            val name = item.name

            itemView.textView4.text = item.description

            val sharedPreferences = context.getSharedPreferences("preferences", 0)

            var gifticonList = sharedPreferences.getString("gifticon", "")!!

            itemView.layoutSetting.setOnClickListener {
                when(name)
                {
                    "카카오페이" -> sharedPreferences.edit().putBoolean("kakaopay", false).apply()
                    "네이버페이" -> sharedPreferences.edit().putBoolean("npay", false).apply()
                    "삼성페이" -> sharedPreferences.edit().putBoolean("samsung", false).apply()
                    "페이코" -> sharedPreferences.edit().putBoolean("payco", false).apply()
                    "기프티콘" -> {
                        gifticonList = gifticonList.replace(item.image+"★"+item.description+"/", "")
                        sharedPreferences.edit().putString("gifticon", gifticonList).apply()
                    }
                }

                val intent = Intent(context, SettingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)

                Toast.makeText(context, "결제수단 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }


}