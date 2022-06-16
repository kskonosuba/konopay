package com.konosuba.pay

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var mediaPlayer : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val random = Random()
        val num = random.nextInt(3)
        when(num)
        {
            0 -> imageView9.setImageResource(R.drawable.konopay_splash_01)
            1 -> imageView9.setImageResource(R.drawable.konopay_splash_02)
            2 -> imageView9.setImageResource(R.drawable.konopay_splash_03)
        }



        val random2 = Random()
        val num2 = random2.nextInt(4)
        when(num2)
        {
            0 ->  mediaPlayer = MediaPlayer.create(this, R.raw.konosuba_aqua)
            1 ->  mediaPlayer = MediaPlayer.create(this, R.raw.konosuba_kazuma)
            2 ->  mediaPlayer = MediaPlayer.create(this, R.raw.konosuba_megumin)
            3 ->  mediaPlayer = MediaPlayer.create(this, R.raw.konosuba_darkness)
        }



        mediaPlayer.isLooping = false
        mediaPlayer.start()

        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()

                timer.cancel()
            }

        }


        timer.schedule(timerTask, 1000, 1000)



    }
}