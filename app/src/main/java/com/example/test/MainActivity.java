package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javaMethods.Calculator;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button zero, one, two, three, four, five, six, seven, eight, nine;
    Button clear, delete, div, mul, sub, add, equal, point, sin, cos, tan, ln, factorial, persent, power, left, right;
    TextView result;
    Intent intent;
    //定义锁定装置
    boolean pointLock1 = false;     // 1.防止一个数中有多个小数点
    boolean pointLock2 = false;     // 2.防止在运算符后连接小数点
    boolean opraterLock = false;    // 3.防止两个数之间输入多于两个运算符,这里只将+，-，*，/，^作为运算符，方便对于sin等函数的判断
    private final String tag = "结果观测：";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化按钮
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);//0--9
        clear = findViewById(R.id.clear);//清屏
        delete = findViewById(R.id.delete);//删除
        div = findViewById(R.id.div);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        add = findViewById(R.id.add);//+-*/
        equal = findViewById(R.id.equal);//=
        point = findViewById(R.id.point);//小数点
        persent = findViewById(R.id.persent);//%，百分号
        sin = findViewById(R.id.sin);//求sin
        cos = findViewById(R.id.button_cos);//求cos
        tan = findViewById(R.id.button_tan);//求tan
        ln = findViewById(R.id.button_ln);//求ln
        factorial = findViewById(R.id.button_factorial);//求阶乘
        power = findViewById(R.id.button_power);//求乘方
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);//()
        result = (TextView) findViewById(R.id.result);//结果显示
        // 监听各个按钮及文本
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);//0-9监听
        clear.setOnClickListener(this);//清屏监听
        delete.setOnClickListener(this);//删除监听
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);//+-*/监听
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        ln.setOnClickListener(this);//sin,cos,tan,ln监听
        power.setOnClickListener(this);//乘方监听
        factorial.setOnClickListener(this);//阶乘监听
        equal.setOnClickListener(this);//=监听
        persent.setOnClickListener(this);//%监听
        point.setOnClickListener(this);//小数点监听
        left.setOnClickListener(this);
        right.setOnClickListener(this);//（）括号监听
        result.setOnClickListener(this);//结果的文本监听
        sin.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.getlength://跳转到页面长度换算
                intent = new Intent(MainActivity.this, getLengthActivity.class);
                startActivity(intent);
                break;
            case R.id.getbinary://跳转到进制换算
                intent = new Intent(MainActivity.this, BinaryExchangeActivity.class);
                startActivity(intent);
                break;
            case R.id.getvolume:////跳转到体积换算
                intent = new Intent(MainActivity.this, getVolumeActivity.class);
                startActivity(intent);
                break;
            case R.id.help://跳转帮助
                intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // 用户按键，设置点击感
        Animation alphaAnimation = new AlphaAnimation(0.1f, 0);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(100);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        v.startAnimation(alphaAnimation);

        // 获取显示器上的字符串，实现连续加减
        String str = result.getText().toString();
        Calculator cal = new Calculator();
        Log.e(tag, str);
        //判断那个键被按下
        switch (v.getId()) {
            // 数值类按键
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                //如果显示器上显示错误，摁下任意数字键归零
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText("0");
                }
                pointLock2 = false;
                opraterLock = false;
                if (str.equals("0")&&!((Button) v).getText().equals("0")) {
                    str = "" + ((Button) v).getText();
                } else if(str.equals("0")&&((Button) v).getText().equals("0")){
                    str = "0";
                }
                else {
                    str += ((Button) v).getText();
                }
                result.setText(str);
                break;

            //小数点摁键，如果两个锁都打开，且前面为数字，就可以摁下小数点
            case R.id.point:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                if (!pointLock1 && !pointLock2 && cal.isNumber(str.charAt(str.length() - 1))) {
                    str += ((Button) v).getText();
                    result.setText(str);
                    pointLock1 = true;//锁住pointLock1，防止一个数中出现多个小数点
                }
                break;

            // 运算符按键，如果摁下，运算符后的为另一个数字，则可以打下一个小数点
            //所以锁1打开。而运算符后不能接小数点，所以锁2锁上，等打数字后，锁2解开
            case R.id.add:
            case R.id.sub:
            case R.id.mul:
            case R.id.div:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                if (str.equals("0")&&((Button) v).getText().equals("-")) {
                    str ="-";
                    result.setText(str);
                    opraterLock = true;
                }
                if((str.length()>=1) && str.charAt(str.length() - 1)=='('){
                    str += ((Button) v).getText();
                    result.setText(str);
                }
                //如果前一位不为小数点才能摁下运算符按键，且不为n,s(即sin,cos,tan,ln的后缀)，但是！和%后是可以加运算符的
                else if (str.charAt(str.length() - 1) != '.' && !opraterLock && str.charAt(str.length() - 1) != 'n' && str.charAt(str.length() - 1) != 's') {
                    pointLock1 = false;
                    pointLock2 = true;
                    opraterLock = true;
                    str += ((Button) v).getText();
                    result.setText(str);
                }
                break;
            case R.id.persent:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                //如果前一位不为小数点才能摁下运算符按键，且不为n,s(即sin,cos,tan,ln的后缀)，但是！后是可以加运算符的
                if (str.charAt(str.length() - 1) != '.' && !opraterLock && str.charAt(str.length() - 1) != 'n' && str.charAt(str.length() - 1) != 's') {
                    pointLock1 = false;
                    pointLock2 = true;//此时不锁住运算符，%后可以接运算符
                    str += ((Button) v).getText();
                    result.setText(str);
                }
                break;
            case R.id.button_factorial:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                if (str.equals("0")) {
                    opraterLock = true;
                }
                //如果前一位不为小数点才能摁下运算符按键，且不为n,s(即sin,cos,tan,ln的后缀)，但是！后是可以加运算符的
                if (str.charAt(str.length() - 1) != '.' && !opraterLock && str.charAt(str.length() - 1) != 'n' && str.charAt(str.length() - 1) != 's') {
                    pointLock1 = false;
                    pointLock2 = true;//此时不锁住运算符，！后可以接运算符
                    str += "!";
                    result.setText(str);
                }
                break;

            case R.id.button_power:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                if (str.equals("0")) {
                    opraterLock = true;
                }
                //如果前一位不为小数点才能摁下运算符按键
                if (str.charAt(str.length() - 1) != '.' && !opraterLock) {
                    pointLock1 = false;
                    pointLock2 = true;
                    opraterLock = true;
                    str += "^";
                    result.setText(str);
                }
                break;
            case R.id.button_cos:
            case R.id.sin:
            case R.id.button_tan:
            case R.id.button_ln:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }

                //如果前一位不为小数点，数字，%,!才能摁下sin运算符按键(可以为n,s即嵌套sin，cos等计算)
                else if (str.charAt(str.length() - 1) != '.' && str.charAt(str.length() - 1) != '%'&&str.charAt(str.length() - 1) != '!' ) {
                    pointLock1 = false;
                    pointLock2 = true;
                    opraterLock = true;//按下这些键后，运算锁住，防止直接接运算符影响运算
                    if (str.equals("0")) {
                        str = "";
                    }
                    str += ((Button) v).getText().toString();
                    result.setText(str);
                } else if (str.equals("0")) {
                    str = "" + ((Button) v).getText();
                } else {
                    str += ((Button) v).getText();
                }
                break;
            //括号
            case R.id.left:
            case R.id.right:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                if(str.equals("0")){
                    str = "";
                }
                str += ((Button) v).getText();
                result.setText(str);
                break;
            //删除键
            case R.id.delete:
                if (str.equals("表达式错误")||str.equals("NaN")||
                        str.equals("-Infinity")||str.equals("Infinity")) {
                    str = "0";
                    result.setText(str);
                }
                // 因为锁1只有在摁下运算符后才会打开
                if (str.charAt(str.length() - 1) == '.') {
                    pointLock1 = false;
                    pointLock2 = false;
                }
                //如果运算符被删掉，opraterLock打开，能够再次输入运算
                if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-' ||
                        str.charAt(str.length() - 1) == '*' || str.charAt(str.length() - 1) == '/' ||
                        str.charAt(str.length() - 1) == '^') {
                    opraterLock = false;
                    pointLock1 = true;
                }
                //如果删除的是cos
                if (str.charAt(str.length() - 1) == 's') {
                    str = str.substring(0, str.length() - 3);
                    if (str.length() == 0)
                        str = "0";
                }
                //删除的是sin,ln,tan
                else if (str.charAt(str.length() - 1) == 's') {
                    //删除sin
                    if (str.charAt(str.length() - 1) == 'i') {
                        str = str.substring(0, str.length() - 3);
                        if (str.length() == 0)
                            str = "0";
                    }
                    //删除tan
                    else if (str.charAt(str.length() - 1) == 'a') {
                        if (str.charAt(str.length() - 1) == 'i') {
                            str = str.substring(0, str.length() - 3);
                            if (str.length() == 0)
                                str = "0";
                        }
                    } else {//删除的是ln
                        str = str.substring(0, str.length() - 2);
                        if (str.length() == 0)
                            str = "0";
                    }
                }
                //如果长度大于一，返回去掉最后一个字符的字符串
                else if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                } else {
                    str = "0";
                }
                result.setText(str);
                break;

            //清屏操作所有锁都打开
            case R.id.clear:
                str = "0";
                result.setText(str);
                pointLock1 = false;
                pointLock2 = false;
                opraterLock = false;
                break;
            //求运算结果
            case R.id.equal:
                if (str.equals("表达式错误")) {
                    str = "0";
                    result.setText(str);
                }
                char lastChar = str.charAt(str.length() - 1);
                if (lastChar != '.' && lastChar != '+' && lastChar != '-'
                        && lastChar != '*' && lastChar != '/' && lastChar != '(' && lastChar != 'n' && lastChar != 's') {
                    str += ((Button) v).getText();
                    result.setText(str);
                    System.out.println(str + "---");
                    System.out.println("输入的表达式： " + str);
                    str = cal.Judge(str);
                    if (str.equals("表达式错误")) {
                        str = str;
                    } else {
                        List<String> list = cal.exchange(str);
                        str = cal.doCalculator(list);
                    }
                    result.setText(str);
                } else {
                    str = "表达式错误";
                    result.setText(str);
                }
                break;
        }
    }
}
