package com.konosuba.pay

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_card_select.*


class CardSelectActivity2 : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_select)
        val actionBar = supportActionBar as ActionBar
        actionBar.title = "카드 선택"

        val gifticonNum = intent.getStringExtra("textGifticonNum")!!
        val couponNum = intent.getStringExtra("textCouponNum")!!



        println(gifticonNum)



        val fruitList = ArrayList<Fruit2>()

        fruitList.add(Fruit2("gifticon", "card_amy_gifticon", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_cecily_gs25", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_chris_seveneleven", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_cielo_lottelia", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_dust_cultureland", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_erika_gifticon", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_iris_emart24", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_komekko_mcdonald", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_mel_starbucks", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_melisa_gifticon", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_mia_bhc", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_mitsurugi_goobne", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_ria_momstouch", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_rin_subway", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_vanir_googleplay", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_wiz_br", gifticonNum, couponNum))
        fruitList.add(Fruit2("gifticon", "card_yunyun_gifticon", gifticonNum, couponNum))


        val pagerAdapter = FruitPagerAdapter3(fruitList)
        viewPagerCardSelect.adapter = pagerAdapter

        viewPagerCardSelect.setOnClickListener {
           // finish()
        }
//
//        var value = ""
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                value = snapshot.getValue<String>()!!
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })


//        val gifticonList = sharedPreferences.getString("gifticon", "")!!
//
//        sharedPreferences.edit().putString("gifticon", "$gifticonList/card_cecily_gs25").apply()
//
//        Toast.makeText(this, "기프티콘이 성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()



    }
}