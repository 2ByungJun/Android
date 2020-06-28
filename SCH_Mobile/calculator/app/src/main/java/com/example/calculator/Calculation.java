package com.example.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Calculation {

    /*****************************************************************
     * calPostfix : 후위연산식 계산
     *****************************************************************/
    public static String calPostfix(String input) {
        ArrayList<String> postfix = toPostfix(input);

        Stack<String> stack = new Stack<>();
        double front, back;
        String result;

        for (int i = 0; i < postfix.size(); i++) {
            switch (postfix.get(i)) {
                case "+":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front + back) + "");
                    break;
                case "-":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front - back) + "");
                    break;
                case "*":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front * back) + "");
                    break;
                case "/":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front / back) + "");
                    break;
                case "%":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push((front % back) + "");
                    break;
                case "&":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front & (int)back) + "");
                    break;
                case "|":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front | (int)back) + "");
                    break;
                case "^":
                    back = Double.parseDouble(stack.pop());
                    front = Double.parseDouble(stack.pop());
                    stack.push(((int)front ^ (int)back) + "");
                    break;
                case "~":
                    back = Double.parseDouble(stack.pop());
                    stack.push((~(int)back) + "");
                    break;
                default:
                    stack.push(postfix.get(i));
            }
        }
        result = stack.pop();
        return result;
    }


    /*****************************************************************
     * toPostfix : 중위표기법 -> 후위표기법 전환
     *****************************************************************/
    private static ArrayList<String> toPostfix(String str) {
        ArrayList<String> result = new ArrayList<>();
        String[] calcTarget = str.split(" ");
        Stack<String> stack = new Stack<>();
        String forPrint = "";

        for (int i = 0; i < calcTarget.length; i++) {
            switch (calcTarget[i]) {
                case "(":
                    stack.push(calcTarget[i]);
                    break;
                case ")":
                    forPrint = stack.pop();
                    while (!forPrint.equals("(")) {
                        result.add(forPrint);
                        forPrint = stack.pop();
                    }
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                case "%":
                case "&":
                case "|":
                case "^":
                case "~":
                    while (!stack.isEmpty() && priority(calcTarget[i]) <= priority(stack.peek())) {
                        forPrint = stack.pop();
                        result.add(forPrint);
                    }
                    stack.push(calcTarget[i]);
                    break;
                default:
                    result.add(calcTarget[i]);
                    break;
            }
        }

        while (!stack.isEmpty()){
            forPrint = stack.pop();
            result.add(forPrint);
        }

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