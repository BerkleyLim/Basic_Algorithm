package author.hyun.sik.lim.sw.expert.academy.Q1218;

import java.util.Scanner;

// 괄호 검사 알고리즘

public class Solution {
    static char[] stack;
    static int top = -1;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        for(int test_case = 1; test_case <= 10; test_case++) {
            top = -1;
            
            // 알고리즘 시작
            int length = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            
            char[] ch = new char[length];
            ch = str.toCharArray();
            
            stack = new char[length];
            
            // 검사 시작
            for (int index = 0; index < length; index++) {
                if (ch[index] == '(') {
                    push(ch[index],length);
                } else if (ch[index] == '{') {
                    push(ch[index],length);
                } else if (ch[index] == '[') {
                    push(ch[index],length);
                } else if (ch[index] == '<') {
                    push(ch[index],length);
                } else if (ch[index] == ')') {
                    if (stack[top] == '(') pop();
                    else break;
                } else if (ch[index] == '}') {
                    if (stack[top] == '{') pop();
                    else break;
                } else if (ch[index] == ']') {
                    if (stack[top] == '[') pop();
                    else break;
                } else if (ch[index] == '>') {
                    if (stack[top] == '<') pop();
                    else break;
                } else {
                    top = 100;
                    break;
                }
            }
            
            System.out.println("#" + test_case + " " + isEmpty());
        }
    }
    
    // 생성
    public static void push(char ch, int length) {
        if (top >= length) return;
        else {
            top++;
            stack[top] = ch;
        }
    }
    
    // 삭제
    public static void pop() {
        if (top < 0) {
            return;
        } else {
            top--;
            return;
        }
    }
    
    // 유효성 확인
    public static int isEmpty() {
        if (top < 0) return 1;
        else return 0;
    }
    
    
}
