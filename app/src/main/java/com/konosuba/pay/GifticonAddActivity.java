package com.konosuba.pay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class GifticonAddActivity extends Activity implements DecoratedBarcodeView.TorchListener{

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private ImageButton setting_btn,switchFlashlightButton;
    private Boolean switchFlashlightButtonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifticon_add);

        Button button = findViewById(R.id.buttonGifticonAddNum);
        EditText editText = findViewById(R.id.textGifticonNum);

        button.setOnClickListener(view -> {

            if(editText.getText().toString().equals(""))
            {
                Toast.makeText(this, "바코드를 인식하거나 빈칸에 바코드 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences preferencesBarcodeResult = getSharedPreferences("preferences", 0);
                SharedPreferences.Editor editorBarcodeResult = preferencesBarcodeResult.edit();
                editorBarcodeResult.putString("gifticonNum", editText.getText().toString());
                editorBarcodeResult.apply();

                Intent intent1 = new Intent(getApplicationContext(), CardSelectActivity.class);
                startActivity(intent1); //새 창(상품정보 띄울 창) 띄우기
                finish();
            }

        });
        //TODO



        switchFlashlightButtonCheck = true;


        if (!hasFlash()) {
            switchFlashlightButton.setVisibility(View.GONE);
        }


        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }



    public void switchFlashlight(View view) {
        if (switchFlashlightButtonCheck) {
            barcodeScannerView.setTorchOn();
        } else {
            barcodeScannerView.setTorchOff();
        }
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }
}