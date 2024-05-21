package com.example.myapp4;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView score_A,score_B;
    Button reset, get3A,get2A, get1A, get3B, get2B, get1B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score_A = findViewById(R.id.score);
        score_B = findViewById(R.id.scoreB);
        reset = findViewById(R.id.btn_reset);
        get3A = findViewById(R.id.btn_3);
        get2A = findViewById(R.id.btn_2);
        get1A = findViewById(R.id.btn_1);
        get3B = findViewById(R.id.btnB_3);
        get2B = findViewById(R.id.btnB_2);
        get1B = findViewById(R.id.btnB_1);
    }
    public void add3A(View btn_3){
        showScore(score_A, 3);
    }
    public void add2A(View btn_2){
        showScore(score_A, 2);
    }
    public void add1A(View btn_1){
        showScore(score_A, 1);
    }
    public void add3B(View btnB_3){
        showScore(score_B, 3);
    }
    public void add2B(View btnB_2){
        showScore(score_B, 2);
    }
    public void add1B(View btnB_1){
        showScore(score_B, 1);
    }
    public void setReset(View btn_reset){
        score_A.setText("0");
        score_B.setText("0");
    }
    private void showScore(TextView v, int num){
        String oldScore = v.getText().toString();
        int newScore = Integer.parseInt(oldScore) + num;
        v.setText("" +newScore);
    }
}