package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText weightEditText, heightEditText;
    Button calculateButton;
    TextView bmiResultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        Log.i("main", "onCreate: ");
    }
    public void onClick(View calculateButton) {
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());

        double bmi = weight / (height * height);
        bmi = Math.round(bmi * 100.0) / 100.0; // 保留两位小数

        String bmiResult = "您的BMI指数为：" + bmi + "\n";

        if (bmi < 18.5) {
            bmiResult += "您的体重过轻，需要增加营养。";
        } else if (bmi >= 18.5 && bmi < 24) {
            bmiResult += "您的体重属于正常范围，继续保持哦亲mua。";
        } else if (bmi >= 24 && bmi < 28) {
            bmiResult += "您的体重过重，请注意控制饮食。";
        } else {
            bmiResult += "您存在肥胖现象，请积极采取减重措施。";
        }
        bmiResultTextView.setText(bmiResult);
    }
}
