package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class getLengthActivity extends AppCompatActivity  {
    private EditText input,equalDM,equalCM;
    double resultDM,resultCM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlength);
        input = findViewById(R.id.inputLength);
        input.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            public void afterTextChanged(Editable arg0) {
                // 文字改变后出发事件
                String content = input.getText().toString();
                System.out.println(content);
                //若输入框内容为空按钮可点击，字体为蓝色
                resultDM = Double.parseDouble(content);
                equalDM.setText(resultDM*100+"");

                resultCM = Double.parseDouble(content);
                equalCM.setText(resultDM*10000+"");
            }
        });
        equalDM = findViewById(R.id.equalsDM);
        equalCM =findViewById(R.id.equalsCM);
    }

}