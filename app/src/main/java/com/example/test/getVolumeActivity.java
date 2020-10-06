package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class getVolumeActivity extends AppCompatActivity {
    private EditText inputM,inputDM,inputCM;//输入的体积
    double resultM,resultDM,resultCM;//暂存结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_volume);
        //对文本框编辑
        inputM = findViewById(R.id.inputVolumeM);
        inputDM = findViewById(R.id.inputVolumeDM);
        inputCM = findViewById(R.id.inputVolumeCM);

        inputM.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            public void afterTextChanged(Editable arg0) {
                // 文字改变后出发事件
                resultM = Double.parseDouble(inputM.getText().toString());
                inputDM.setText(resultM*10+"");
                inputCM.setText(resultM*100+"");
            }
        });
        inputDM.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            public void afterTextChanged(Editable arg0) {
                // 文字改变后出发事件
                resultDM = Double.parseDouble(inputDM.getText().toString());
                inputM.setText(resultDM/10.0+"");
                inputCM.setText(resultDM*10+"");
            }
        });
        inputCM.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            public void afterTextChanged(Editable arg0) {
                // 文字改变后出发事件
                resultCM = Double.parseDouble(inputCM.getText().toString());
                inputDM.setText(resultCM/10.0+"");
                inputM.setText(resultCM/100.0+"");
            }
        });
    }
}