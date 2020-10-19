package com.example.test;

import android.os.Bundle;

import com.example.javaMethods.BinaryExchange;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BinaryExchangeActivity extends AppCompatActivity {
    private TextView result_view;
    private EditText input;
    private Spinner spinner1,spinner2;
    private int binary1,binary2;//用于计算验证输入的进制是否合理
    private String inputNumber="";
    private ArrayAdapter<CharSequence> adapter;
    BinaryExchange be = new BinaryExchange();
    private String tag = "结果观测： ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_exchange);

        input = findViewById(R.id.inputNumber);
        result_view = findViewById(R.id.result_view);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        adapter = ArrayAdapter.createFromResource(this,R.array.items,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//实现下拉选择事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position >= 0){
                    Toast.makeText(BinaryExchangeActivity.this,adapterView.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                }
                if(!inputNumber.isEmpty()){
                    binary1 = Integer.parseInt(spinner1.getSelectedItem().toString());
                    binary2 = Integer.parseInt(spinner2.getSelectedItem().toString());
                    result_view.setText(be.change(inputNumber,binary1,binary2));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position >= 0){
                    Toast.makeText(BinaryExchangeActivity.this,adapterView.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                }
                if(!inputNumber.isEmpty()){
                    binary1 = Integer.parseInt(spinner1.getSelectedItem().toString());
                    binary2 = Integer.parseInt(spinner2.getSelectedItem().toString());
                    result_view.setText(be.change(inputNumber,binary1,binary2));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        input.addTextChangedListener(new TextWatcher() {//为输入框设置监听器，当输入框内容变化的时候，存储输入框的内容
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                inputNumber = input.getText().toString();
                binary1 = Integer.parseInt(spinner1.getSelectedItem().toString());
                binary2 = Integer.parseInt(spinner2.getSelectedItem().toString());
                //Log.e(tag,inputNumber);
                result_view.setText(be.change(inputNumber,binary1,binary2));
            }
        });
    }
}