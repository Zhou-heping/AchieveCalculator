package com.example.javaMethods;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
   /* public static void main(String[] args) {
        String express;
        System.out.println("请输入表达式：");
        Scanner input = new Scanner(System.in);
        express = input.next();
        System.out.println("输入表达式为：" + express);
        String result;
        express = Judge(express);
        if (express.equals("表达式错误")) {
            result = express;
        } else {
            List<String> list = exchange(express);
            result = doCalculator(list);
        }
        System.out.println("结果为：" + result);
        // 4.计算的结果（实现计算器）
    }*/

    public static Map<Character, Integer> priority = new HashMap<Character, Integer>();// 设置符号优先级别
    static {
        priority.put('(', 0);
        priority.put(')', 0);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('%', 3);
        priority.put('^', 3);
        priority.put('s', 3);
        priority.put('c', 3);
    }

    public static String Judge(String express) {
        // 表达式标准化
        express = changeToStandardFormat(express);
        System.out.println("标准化后结果为：" + express);
        // 检查格式
        if (!checkFormat(express)) {
            return "表达式错误";
        }
        return express;

    }

    public static boolean checkFormat(String str) {
        // 校验开头是否为数字或者“(”或者为s,c等
        if (!(isNumber(str.charAt(0)) || str.charAt(0) == '(' || str.charAt(0) == 's' || str.charAt(0) == 'c')) {
            return false;
        }
        char c;
        // 校验
        for (int i = 1; i < str.length() - 1; i++) {
            c = str.charAt(i);
            if (!(isNumber(c))) {// 如果为运算符则进行判断
                if (c == '.') {// 校验 . 或 ^ 的前后是否为数字
                    if (!isNumber(str.charAt(i - 1)) || !isNumber(str.charAt(i + 1))) {
                        return false;
                    }
                }
                else if(c == '('||c == ')'){
                    continue;
                }
                else {//不为小数点的运算符
                    if(str.charAt(i) == 'c' || str.charAt(i) == 's'){//sin,cos前面可以有运算符
                        if(!(str.charAt(i - 1) == '+' || str.charAt(i - 1) == '-' || str.charAt(i - 1) == '*'
                                || str.charAt(i - 1) == '/'||isNumber(str.charAt(i - 1)) || str.charAt(i - 1) == ')'))
                            return false;
                    }
                    else{//其他的运算符前面不能有运算符，除开%
                        if (!(isNumber(str.charAt(i - 1)) || str.charAt(i - 1) == ')' || str.charAt(i - 1) == '%')) {// 若符号前一个不是数字或者“）”时
                            return false;
                        }
                    }
                }
            }
        }
        return isBracketCouple(str);// 校验括号是否配对
    }

    // 括号是否匹配
    public static boolean isBracketCouple(String str) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {// 遇到一个右括号，即移除一个左括号
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // 处理表达式格式为标准格式
    public static String changeToStandardFormat(String str) {
        // 开头为负数，如-1，改为0-1
        if ('-' == str.charAt(0)) {
            str = 0 + str;
        }
        str = str.replaceAll("sin", "s");
        str = str.replaceAll("cos", "c");
        System.out.println("str is :" + str);
        StringBuilder s = new StringBuilder();// 可变字符序列
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (i != 0 && c == '(' && (isNumber(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {
                s.append("*(");
                continue;
            }
            if (i != 0 && str.charAt(i - 1) == '(' && c == '-') {
                s.append("0-");
                continue;
            }
            if (i != 0 && str.charAt(i - 1) == ')'&&(c == 's' || c == 'c'|| isNumber(c))) {// 如果)括号后面接的是数字或者s，或者c，则变为*c
                s.append("*");
                s.append(c);
                continue;
            }

            s.append(c);
        }
        return s.toString();
    }

    public static boolean isNumber(char c) {
        if (c >= '0' && c <= '9')
            return true;
        else
            return false;
    }

    // 实现转变为后缀表达式
    public static List<String> exchange(String express) {
        Stack<Character> stack = new Stack<Character>();
        List<String> list = new ArrayList<String>();
        String temp = "";
        char op;
        int i = 0;
        while (i < express.length()) {
            if (express.charAt(i) == '=') {
                break;
            }
            if (isNumber(express.charAt(i))) {// 如果是数字，则继续判断，直到不为小数点或者数字为止
                temp = temp + express.charAt(i++);
                while (express.charAt(i) == '.' || isNumber(express.charAt(i))) {
                    temp = temp + express.charAt(i++);
                }
                list.add(temp);
                temp = "";
            } else {// 否则为运算符
                // 由于栈为空，所以第一个运算符直接入栈
                if (stack.isEmpty()) {
                    stack.push(express.charAt(i++));
                } else {
                    if (express.charAt(i) == '(') {// 如果是左括号，入栈
                        stack.push(express.charAt(i++));
                    } else if (express.charAt(i) == ')') {// 如果是右括号，弹栈，直到弹出左括号为止
                        op = stack.pop();
                        while (op != '(') {
                            list.add(op + "");
                            op = stack.pop();
                        }
                        i++;
                    } else {
                        op = stack.peek();
                        if (priority.get(op) < priority.get(express.charAt(i))) {
                            stack.push(express.charAt(i++));
                        } else {
                            op = stack.pop();
                            list.add(op + "");

                        }

                    }
                }
            }

        }
        while (!stack.isEmpty()) {
            list.add(stack.pop() + "");
        }
        return list;
    }

    public static String doCalculator(List<String> list) {
        String result = null;
        Stack<String> stack = new Stack<>();
        double num1 = 0, num2 = 0;
        for (String str : list) {
            // 如果不为运算符则为数字，将其入栈
            if (!(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%")
                    || str.equals("^") || str.equals("c") || str.equals("s"))) {
                stack.push(str);
            } else {
                num1 = Double.parseDouble(stack.pop());
                if (str.equals("s")) {// 求sin运算
                    stack.push(Math.sin(Math.PI * num1 / 180) + "");

                } else if (str.equals("c")) {// 求cos运算
                    stack.push(Math.cos(Math.PI * num1 / 180) + "");
                } else if (str.equals("%")) {// 求%运算
                    stack.push(num1 / 100 + "");
                } else {
                    num2 = Double.parseDouble(stack.pop());
                    switch (str) {
                        case "+":
                            stack.push(num1 + num2 + "");
                            break;
                        case "-":
                            stack.push(num2 - num1 + "");
                            break;
                        case "*":
                            stack.push(num1 * num2 + "");
                            break;
                        case "/":
                            if (num1 == 0) {
                                result = "表达式错误";
                                stack.push(0 + "");
                            } else {
                                stack.push(num2 / num1 + "");
                            }
                            break;
                        case "^":
                            stack.push(Math.pow(num2, num1) + "");
                            break;
                    }
                }
            }

        }
        if (result != null)
            return result;
        return stack.pop();
    }
}