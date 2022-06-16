package com.konosuba.pay

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_card_add.*
import kotlinx.android.synthetic.main.dialog01.*


class CardAddActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    var value = ""

    var list = ArrayList<String>()

    var couponList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_add)

        val actionBar = supportActionBar as ActionBar
        actionBar.title = "카드 / 기프티콘 등록"

        sharedPreferences = getSharedPreferences("preferences", 0)

        var samsung = sharedPreferences.getBoolean("samsung", false)
        var payco = sharedPreferences.getBoolean("payco", false)
        var npay = sharedPreferences.getBoolean("npay", false)
        var kakao = sharedPreferences.getBoolean("kakaopay", false)

        val database = Firebase.database
        val myRef = database.getReference("coupon_list")




        imageView.setOnClickListener {
            if(!samsung)
            {
                samsung = true
                sharedPreferences.edit().putBoolean("samsung", true).apply()
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "이미 등록되어 있습니다.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        }


        imageView2.setOnClickListener {
            if(!payco)
            {
                payco = true
                sharedPreferences.edit().putBoolean("payco", true).apply()
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "이미 등록되어 있습니다.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        }


        imageView3.setOnClickListener {
            if(!kakao)
            {
                kakao = true
                sharedPreferences.edit().putBoolean("kakaopay", true).apply()
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "이미 등록되어 있습니다.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
        imageView4.setOnClickListener {
            if(!npay)
            {
                npay = true
                sharedPreferences.edit().putBoolean("npay", true).apply()
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "이미 등록되어 있습니다.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        }


        imageView5.setOnClickListener {

            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
            finish()

        }

        val sharedPreferences = getSharedPreferences("preferences", 0)

        var gifticonList = sharedPreferences.getString("gifticon", "")!!



        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog01)




        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)


                couponList = ArrayList(value!!.split("/") )

                println(couponList)


                dialog.yesBtn.setOnClickListener {
                    val text = dialog.textCouponEdit.text.toString()


                    if(text == "")
                    {
                        Toast.makeText(applicationContext, "입력값을 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        var codeSuccess = false
                        println(couponList)
                        for(i in couponList)
                        {
                            println(i)
                            val content = i.split("★")
                            if(content[0] == text)
                            {
                                println(content[0])
                                println(content[1])
                                gifticonList = gifticonList + content[2] + "★" + content[1] + "/"
                                sharedPreferences.edit().putString("gifticon", gifticonList).apply()

                                codeSuccess = true
                                Toast.makeText(applicationContext, "쿠폰이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
                                break
                            }
                        }

                        if(!codeSuccess)
                        {
                            Toast.makeText(applicationContext, "입력값을 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            dialog.cancel()
                        }


                    }
                }



            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })


        imageView15.setOnClickListener {
            dialog.show()

        }


//        imageView.setOnClickListener {
//
//
//            try{
//                val intent = packageManager.getLaunchIntentForPackage("com.samsung.android.spay")
//                startActivity(intent)
//
//            }catch (e : Exception)
//            {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.addCategory(Intent.CATEGORY_DEFAULT)
//                intent.data = Uri.parse("market://details?id=com.samsung.android.spay")
//                startActivity(intent)
//                Toast.makeText(this, "삼성페이 어플리케이션이 설치되어 있지 않습니다. 스토어로 이동합니다.", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        imageView4.setOnClickListener {
//            val intent = packageManager.getLaunchIntentForPackage("com.nhnent.payapp")
//            startActivity(intent)
//        }
//
//        imageView3.setOnClickListener {
//            val intent = packageManager.getLaunchIntentForPackage("com.naverfin.payapp")
//            startActivity(intent)
//        }
//
//        imageView2.setOnClickListener {
//            val intent = packageManager.getLaunchIntentForPackage("com.kakaopay.app")
//            startActivity(intent)
//        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}