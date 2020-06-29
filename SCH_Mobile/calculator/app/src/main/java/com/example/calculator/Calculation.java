package com.example.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculation {

    /*****************************************************************
     * calPostfix : 후위연산식 계산
     * @Param  > String input : 중위표기식의 수식
     * @return > String result : 계산된 결과 값
     *****************************************************************/
    public static String calPostfix(String input) {

        /***** toPostfix : 중위표기식 -> 후위표기식 *****/
        ArrayList<String> postfix = toPostfix(input);

        /***** Variable *****/
        Stack<String> stack = new Stack<>(); // Stack
        double front, back; // 전, 후
        String result; // return 값

        /***** 연산부 *****/
        for (int i = 0; i < postfix.size(); i++) {
            switch (postfix.get(i)) {
                /*****************************************************************
                 * Operator, % : 실수들간 비교
                 *****************************************************************/
                /***** + : "1 + 2" *****/
                case "+":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front + back) + "");
                    break;
                /***** - : "1 - 2" *****/
                case "-":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front - back) + "");
                    break;
                /***** * : "1 * 2" *****/
                case "*":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front * back) + "");
                    break;
                /***** / : "1 / 2" *****/
                case "/":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front / back) + "");
                    break;
                /***** % : "1 % 2" *****/
                case "%":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front % back) + "");
                    break;
                /*****************************************************************
                 * Bit Operator : 정수들간 비교
                 *****************************************************************/
                /***** & : "1 & 2" *****/
                case "&":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front & (int)back) + "");
                    break;
                /***** | : "1 | 2" *****/
                case "|":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front | (int)back) + "");
                    break;
                /***** ^ : "1 ^ 2" *****/
                case "^":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front ^ (int)back) + "");
                    break;
                /***** ~ : "~ 1" *****/
                case "~":
                    back = Double.parseDouble(stack.pop());
                    stack.push((~(int)back) + "");
                    break;
                default:
                    stack.push(postfix.get(i));
            }
        }
        /***** result : 결과 값 *****/
        result = stack.pop();
        return result;
    }


    /*****************************************************************
     * toPostfix : 중위표기법 -> 후위표기법 전환
     *****************************************************************/
    private static ArrayList<String> toPostfix(String str) {
        /***** Variable *****/
        ArrayList<String> result = new ArrayList<>(); // List
        String[] calcTarget = str.split(" ");  // 공백 split
        Stack<String> stack = new Stack<>();         // Stack
        String forPrint = ""; // 임시저장

        /***** 전환식 *****/
        for (int i = 0; i < calcTarget.length; i++) {
            switch (calcTarget[i]) {
                /***** "(" push *****/
                case "(":
                    stack.push(calcTarget[i]);
                    break;
                /***** ")" push : "("에 따라 forPrint 에 임시 저장 후 add *****/
                case ")":
                    forPrint = stack.pop();
                    while (!forPrint.equals("(")) {
                        result.add(forPrint);
                        forPrint = stack.pop();
                    }
                    break;
                /***** 연산부 *****/
                case "+":
                case "-":
                case "/":
                case "*":
                case "%":
                case "&":
                case "|":
                case "^":
                case "~":
                    /***** 우선순위에 따른 순서 *****/
                    while (!stack.isEmpty() && priority(calcTarget[i]) <= priority(stack.peek())) {
                        forPrint = stack.pop();
                        result.add(forPrint);
                    }
                    stack.push(calcTarget[i]);
                    break;
                /***** 숫자인 경우 *****/
                default:
                    result.add(calcTarget[i]);
                    break;
            }
        }
        /***** 비어있는 경우 *****/
        while (!stack.isEmpty()){
            forPrint = stack.pop();
            result.add(forPrint);
        }
        /***** ArrayList 형태의 후위표기식 *****/
        return result;
    }

    /*****************************************************************
     * priority : 우선순위
     *****************************************************************/
    private static int priority(String ch) {
        switch (ch) {
            case "&":
            case "|":
            case "^":
            case "~":
            case "%":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            case "(":
            case ")":
                return 0;
        }
        return -1;
    }
}