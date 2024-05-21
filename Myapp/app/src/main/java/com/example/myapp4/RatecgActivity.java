package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RatecgActivity extends AppCompatActivity {

    EditText dolRate, euroRate, wonRate;
    Intent config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratecg);
        dolRate = findViewById(R.id.new_dol_rate);
        euroRate = findViewById(R.id.new_euro_rate);
        wonRate = findViewById(R.id.new_won_rate);
        config = getIntent();
        Bundle bdl = config.getExtras();
        assert bdl != null;
        dolRate.setText(String.valueOf(bdl.getFloat("oldDollorRate")));
        euroRate.setText(String.valueOf(bdl.getFloat("oldEuroRate")));
        wonRate.setText(String.valueOf(bdl.getFloat("oldWonRate")));
    }
    public void rateSubmit(View v){
        Button rateBtn = findViewById(R.id.rate_submit);

        try {
            float newDollorRate = Float.parseFloat(dolRate.getText().toString());
            float newEuroRate = Float.parseFloat(euroRate.getText().toString());
            float newWonRate = Float.parseFloat(wonRate.getText().toString());
            config.putExtra("newDollorRate", newDollorRate);
            config.putExtra("newEuroRate", newEuroRate);
            config.putExtra("newWonRate", newWonRate);
            setResult(3, config);
            finish();

        }catch (Exception e){
            Toast.makeText(this, "Input right rate", Toast.LENGTH_SHORT).show();
        }
    }
}