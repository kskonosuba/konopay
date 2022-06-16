package com.konosuba.pay

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_select.*


class CardSelectActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_select)
        val actionBar = supportActionBar as ActionBar
        actionBar.title = "카드 선택"

        sharedPreferences = getSharedPreferences("preferences", 0)

        val gifticonNum = sharedPreferences.getString("gifticonNum", "")!!

        println(gifticonNum)



        val fruitList = ArrayList<Fruit>()

        fruitList.add(Fruit("gifticon", "card_amy_gifticon", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_cecily_gs25", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_chris_seveneleven", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_cielo_lottelia", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_dust_cultureland", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_erika_gifticon", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_iris_emart24", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_komekko_mcdonald", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_mel_starbucks", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_melisa_gifticon", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_mia_bhc", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_mitsurugi_goobne", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_ria_momstouch", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_rin_subway", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_vanir_googleplay", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_wiz_br", gifticonNum))
        fruitList.add(Fruit("gifticon", "card_yunyun_gifticon", gifticonNum))


        val pagerAdapter = FruitPagerAdapter2(fruitList)
        viewPagerCardSelect.adapter = pagerAdapter

        viewPagerCardSelect.setOnClickListener {
           // finish()
        }




//        val gifticonList = sharedPreferences.getString("gifticon", "")!!
//
//        sharedPreferences.edit().putString("gifticon", "$gifticonList/card_cecily_gs25").apply()
//
//        Toast.makeText(this, "기프티콘이 성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()



    }
}