package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    EditText edt;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edt = findViewById(R.id.edt1);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        txt = findViewById(R.id.txtV);
    }

    public void onClick1(View view) {
        String str = edt.getText().toString();
        double flo =Double.parseDouble(str)*1.8+32;
        txt.setText("转换为华氏度为："+(int)(flo*100)/100.0);
    }

    @Override
    public void onClick(View v) {
        txt.setText(txt.getText().toString()+"hello");
    }
}