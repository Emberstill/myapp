package com.example.myapp4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class RateActivity extends AppCompatActivity{


    float dollorRate = 7.2357f, euroRate = 7.8055f,wonRate = 0.0054f;
    Handler handler;
    TextView result;
    ActivityResultLauncher<Intent> launcher;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rate);
        result = findViewById(R.id.targmoney);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == 3){
                        Intent data = result.getData();
                        assert data != null;
                        dollorRate = data.getFloatExtra("newDollorRate", 1.11f);
                        euroRate = data.getFloatExtra("newEuroRate", 2.22f);
                        wonRate = data.getFloatExtra("newWonRate", 3.33f);
                    }

                });

    }

    public void rateConvert(View v){
        EditText inprmb = findViewById(R.id.rmb);
        try {
            String rmb = inprmb.getText().toString();
            float rmbNum = Float.parseFloat(rmb);

            if(v == findViewById(R.id.dollor)) {
                result.setText(String.valueOf(rmbNum * dollorRate));
            } else if(v == findViewById(R.id.euro)) {
                result.setText(String.valueOf(rmbNum * euroRate));
            } else {
                result.setText(String.valueOf(rmbNum * wonRate));
            }
        }catch(Exception e){
            Toast.makeText(this, "请输入正确人民币数值", Toast.LENGTH_SHORT).show();
        }
    }
    public void config(View v){
        Intent config = new Intent(this, RatecgActivity.class);
        Bundle bdl = new Bundle();
        bdl.putFloat("oldDollorRate", dollorRate);
        bdl.putFloat("oldEuroRate", euroRate);
        bdl.putFloat("oldWonRate", wonRate);
        config.putExtras(bdl);

        launcher.launch(config);
//        startActivityForResult(config, 2);
    }
     

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 2 && resultCode == 3){
//            assert data != null;
//            dollorRate = data.getFloatExtra("newDollorRate", 1.11f);
//            euroRate = data.getFloatExtra("newEuroRate", 2.22f);
//            wonRate = data.getFloatExtra("newWonRate", 3.33f);
//        }
//    }


}