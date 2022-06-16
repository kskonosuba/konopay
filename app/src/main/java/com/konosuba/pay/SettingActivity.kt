package com.konosuba.pay

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: RecyclerAdapter
    val datas = ArrayList<Item>()
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val actionBar = supportActionBar as ActionBar
        actionBar.title = "결제수단 관리"
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter


        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        sharedPreferences = getSharedPreferences("preferences", 0)

        var samsung = sharedPreferences.getBoolean("samsung", false)
        var payco = sharedPreferences.getBoolean("payco", false)
        var npay = sharedPreferences.getBoolean("npay", false)
        var kakao = sharedPreferences.getBoolean("kakaopay", false)


        if(samsung)
        {
            datas.add(Item("card_aqua_samsungpay", "삼성페이", ""))
        }
        if(payco)
        {
            datas.add(Item("card_megumin_payco", "페이코", ""))
        }
        if(npay)
        {
            datas.add(Item("card_kazuma_npay", "네이버페이", ""))
        }
        if(kakao)
        {
            datas.add(Item("card_darkness_kakaopay", "카카오페이", ""))
        }



        val gifticonList = sharedPreferences.getString("gifticon", "")!!.split("/")

        for(i in gifticonList)
        {
            println(i)
            if(i != "")
            {
                val gifticonInfo = i.split("★")
                println(gifticonInfo[0])
                println(gifticonInfo[1])

                datas.add(Item(gifticonInfo[0], "기프티콘", gifticonInfo[1]))
            }
        }


        datas.apply {

            recyclerAdapter.datas = datas
            recyclerAdapter.notifyDataSetChanged()

        }




    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }
}