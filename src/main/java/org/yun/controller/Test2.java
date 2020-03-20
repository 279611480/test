package org.yun.controller;

import java.io.IOException;

/**
 * @ClassName Test2
 * @Author 芸
 * @Date 2020/2/28 13:57
 * @Description 出、入栈
 **/
public class Test2 {

    private String input;
    private String output;
    public Test2(String in) {
        input = in;
    }
    public String doRev() {
        int stackSize = input.length();
        Stack theStack = new Stack(stackSize);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);//charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。  从索引0开始输出全部
            theStack.push(ch);//出栈
        }
        output = "";
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }
    public static void main(String[] args)
            throws IOException {
        String input = "www.w3cschool.cc";
        String output;
        Test2 theReverser =
                new Test2(input);
        output = theReverser.doRev();
        System.out.println("反转前： " + input);
        System.out.println("反转后： " + output);
    }
    class Stack {
        private int maxSize;
        private char[] stackArray;
        private int top;
        public Stack(int max) {
            maxSize = max;
            stackArray = new char[maxSize];
            top = -1;
        }
        public void push(char j) {
            stackArray[++top] = j;
        }
        public char pop() {
            return stackArray[top--];
        }
        public char peek() {
            return stackArray[top];
        }
        public boolean isEmpty() {
            return (top == -1);
        }
    }

}
