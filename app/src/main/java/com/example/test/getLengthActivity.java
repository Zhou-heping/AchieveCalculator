package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class getLengthActivity extends AppCompatActivity  {
    private EditText inputM,inputDM,inputCM;
    double resultM,resultDM,resultCM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlength);
        //对文本框编辑
        inputM = findViewById(R.id.inputLengthM);
        inputDM = findViewById(R.id.inputLengthDM);
        inputCM = findViewById(R.id.inputLengthCM);

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