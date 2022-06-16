package com.konosuba.pay

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog01.*
import kotlinx.android.synthetic.main.dialog02.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    var phoneNum = "0"

    lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music_new_01)
        mediaPlayer.isLooping = true

        val actionBar = supportActionBar as ActionBar
        actionBar.title = "결제"



        //전화번호 받아오는 코드
//        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_SMS
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_PHONE_NUMBERS
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_PHONE_STATE
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//
////            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_NUMBERS), 0)
//
//            //Toast.makeText(this, "권한을 허용하지 않으면 이벤트에 참여할 수 없습니다.", Toast.LENGTH_SHORT).show()
////            return
//        }
//        phoneNum = tm.line1Number





        sharedPreferences = getSharedPreferences("preferences", 0)

        var samsung = sharedPreferences.getBoolean("samsung", false)
        var payco = sharedPreferences.getBoolean("payco", false)
        var npay = sharedPreferences.getBoolean("npay", false)
        var kakao = sharedPreferences.getBoolean("kakaopay", false)




        val fruitList = ArrayList<Fruit>()



        //간편결제 서비스
        if(samsung) fruitList.add(Fruit("samsungpay", "card_aqua_samsungpay", ""))
        if(payco) fruitList.add(Fruit("payco", "card_megumin_payco", ""))
        if(npay) fruitList.add(Fruit("npay", "card_kazuma_npay", ""))
        if(kakao) fruitList.add(Fruit("kakaopay", "card_darkness_kakaopay", ""))




        val gifticonList = sharedPreferences.getString("gifticon", "")!!.split("/")

        for(i in gifticonList)
        {
            println(i)
            if(i != "")
            {
                val gifticonInfo = i.split("★")
                println(gifticonInfo[0])
                println(gifticonInfo[1])

                fruitList.add(Fruit("gifticon", gifticonInfo[0], gifticonInfo[1]))
            }
        }



        fruitList.add(Fruit("add", "icon_add", ""))



        val pagerAdapter = FruitPagerAdapter(fruitList)
        viewPagerMain.adapter = pagerAdapter


        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog02)

        val database = Firebase.database
        val myRef = database.getReference("coupon_list")




        dialog.yesBtn3.setOnClickListener {
            val textCouponNum = dialog.textCouponEdit2.text.toString()
            val textGifticonNum = dialog.textCouponEdit3.text.toString()

            if(textGifticonNum=="" || textCouponNum=="") {
                Toast.makeText(this, "쿠폰번호 및 기프티콘 번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this, CardSelectActivity2::class.java)
                intent.putExtra("textGifticonNum", textGifticonNum)
                intent.putExtra("textCouponNum", textCouponNum)
                startActivity(intent)
            }







        }





        textView2.setOnClickListener {

            dialog.show()


        }

        imageView2.setOnClickListener {
//            startActivity(Intent(this, CardAddActivity::class.java))

        }
        imageView3.setOnClickListener {
//            startActivity(Intent(this, GifticonUseActivity::class.java))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, SettingActivity::class.java))
        return true
    }

    override fun onPause() {
        super.onPause()
        //TODO : 실 배포시 활성화
//        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND)

        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music_new_01)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }


}