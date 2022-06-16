package com.konosuba.pay

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import kotlinx.android.synthetic.main.activity_gifticon_use.*
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception
import java.util.*


class GifticonUseActivity : AppCompatActivity() {

    private val WHITE: Int = 0xFFFFFFFF.toInt()
    private val BLACK: Int = 0xFF000000.toInt()

    lateinit var sharedPreferences: SharedPreferences
    lateinit var mediaPlayer : MediaPlayer
    lateinit var timerTask: TimerTask

    lateinit var cameraManager : CameraManager

    lateinit var vibrator: Vibrator
    lateinit var timer : Timer

    var cameraId = ""

    var lightOn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifticon_use)

        val actionBar = supportActionBar as ActionBar
        actionBar.title = "기프티콘 결제"


        val random = Random()
        when(random.nextInt(7))
        {
            0 -> imageView6.setImageResource(R.drawable.gifticon_use_erika)
            1 -> imageView6.setImageResource(R.drawable.gifticon_use_aqua)
            2 -> imageView6.setImageResource(R.drawable.gifticon_use_chris)
            3 -> imageView6.setImageResource(R.drawable.gifticon_use_iris)
            4 -> imageView6.setImageResource(R.drawable.gifticon_use_yunyun)
            5 -> imageView6.setImageResource(R.drawable.gifticon_use_wiz)
            6 -> imageView6.setImageResource(R.drawable.gifticon_use_rin)
        }


          window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraManager.cameraIdList[0])
        cameraId = cameraManager.cameraIdList[0]
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {

                when(lightOn)
                {
                    3 ->{
                        cameraManager.setTorchMode(cameraId, false)
                        lightOn = 0
                        vibrator.vibrate(VibrationEffect.createOneShot(10, 100))
                    }
                    0, 1, 2 ->{
                        cameraManager.setTorchMode(cameraId, true)
                        lightOn+=1
                    }
                }

            }

        }

        timer.schedule(timerTask, 300, 300)






//        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music_new_01)
//        mediaPlayer.isLooping = true
//        mediaPlayer.start()



        sharedPreferences = getSharedPreferences("preferences", 0)

        val getString = intent.getStringExtra("num")!!

       // Toast.makeText(this, getString, Toast.LENGTH_SHORT).show()



        val barcode = createBarcode(getString)

        imageBarcode.setImageBitmap(barcode)


        buttonGifticonUse.setOnClickListener {
            //TODO

            Toast.makeText(this, "기프티콘 사용처리가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonExit.setOnClickListener{
            finish()
        }


    }



    fun createBarcode(code: String) : Bitmap {
        val widthPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 390f,
            resources.displayMetrics
        )
        val heightPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 111f,
            resources.displayMetrics
        )
        val format: BarcodeFormat = BarcodeFormat.CODE_128
        val matrix: BitMatrix = MultiFormatWriter().encode(code, format, widthPx.toInt(), heightPx.toInt())
        val bitmap = createBitmap(matrix)
        return bitmap
    }

    fun createBitmap(matrix: BitMatrix): Bitmap {
        val width = matrix.width
        val height = matrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (matrix.get(x, y)) BLACK else WHITE)
            }
        }
        return bitmap
    }


    override fun onPause() {
        super.onPause()
//        mediaPlayer.stop()
        timerTask.cancel()
        cameraManager.setTorchMode(cameraId, false)
    }

    override fun onRestart() {
        super.onRestart()
//        mediaPlayer.start()
        //TODO

    }

    override fun onDestroy() {
        super.onDestroy()
//        mediaPlayer.stop()
        timer.cancel()
        cameraManager.setTorchMode(cameraId, false)

    }
}