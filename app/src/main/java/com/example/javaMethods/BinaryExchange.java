package com.example.javaMethods;


import java.math.BigInteger;
import java.util.Stack;

public class BinaryExchange {
    //任意进制换算
    //转化为10进制，然后再转化为目标进制
    public  String change(String number, int from, int to) {
        //number 要转换的数  ， from 原数的进制，to 要转换成的进制
        //检验输入是否合法，即输入的数不能大于原数的进制
        if(number.equals(check(number))) {
            int j = 0;
            int temp = 0;
            for (int i = number.length(); i > 0; i--) {
                if((number.charAt(j)+"").matches("[0-9]")){//如果为数字，则直接计算
                    temp += Integer.parseInt(number.charAt(j++) + "") * Math.pow(from, i - 1);
                }
                else{//否则为字母，需要转换为数字后才可以继续计算(A的ASCLL值为65，转化为10则需要减去55，依次类推)
                    temp += Integer.parseInt((number.charAt(j++)-55) + "") * Math.pow(from, i - 1);
                }
            }
            return change1(temp,to);
        }
        return check(number);
    }
    private String check(String number){
        boolean flag = number.matches("[a-zA-Z0-9]*") ;//匹配字符串中的所有所有字符
        if(flag){
            return number;
        }
        else
            return "输入有误或者输入进制数大于该进制的最大值";
    }
    //10进制转为任意进制
    private   String change1(int num,int index){
        Stack<String> stack = new Stack<String>();
        while(num!=0){
            int remainder = num % index;//求余
            num = num / index;//求剩余下来的整数
            if(remainder > 9){
                stack.push((char)(remainder+55)+"");
            }
            else
                stack.push(remainder+"");
        }
        String result = "";
        while(!stack.isEmpty()){
            result =result+ stack.pop();
        }
        return result;
    }
    //java自带的转化方法
    public  String change2(String number, int from, int to) {
        //number 要转换的数  ， from 原数的进制，to 要转换成的进制
        return new BigInteger(number, from).toString(to);
    }
}