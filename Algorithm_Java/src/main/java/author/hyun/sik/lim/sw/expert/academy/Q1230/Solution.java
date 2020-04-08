package author.hyun.sik.lim.sw.expert.academy.Q1230;

// 0 ~ 999999 사이의 수를 나열한 암호문 존재
// 명령어
// I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자  삽입, s는 덧붙일 숫자
// D(삭제) x, y : 앞에서부터 x의 위치 바로 다음부터 y개의 숫자 삭제
// A(추가) y, s : 암호문 맨 뒤에 y개의 숫자르 덧붙임, s는 덧붙일 숫자

import java.util.LinkedList;
import java.util.Scanner;

class Solution {
    static LinkedList<Integer> crypto;
    static Scanner sc = new Scanner(System.in);
    // 리스트 생성
    public static void main(String args[]) throws Exception {
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            // 초기화
            crypto = new LinkedList<>();
            
            // 알고리즘 시작
            int N = sc.nextInt();
            
            // 입력
            for (int index = 0; index < N; index++) {
                crypto.add(sc.nextInt());
            }
            
            // 명령어 개수 입력
            int length = sc.nextInt();
            
            // 명령어 시작
            for (int i = 0; i < length; i++) {
                char command = sc.next().charAt(0);
                
                switch (command) {
                case 'I': 
                    insert();
                    break;
                case 'D':
                    delete();
                    break;
                case 'A':
                    addtion();
                    break;
                }
            }
            
            System.out.print("#" + test_case + " ");
            
            for (int i = 0; i < 10; i++) {
                System.out.print(crypto.get(i) + " ");
            }
            
            System.out.println();
            
        }
        sc.close();
    }
    
    // 삽입
    public static void insert() {
        int start = sc.nextInt();
        int end = sc.nextInt();
        
        int index = 0;
        while (index < end) {
            crypto.add(start + index, sc.nextInt());
            index++;
        }
    }
    
    // 삽입
    public static void delete() {
        int start = sc.nextInt();
        int end = sc.nextInt();
        
        int index = 0;
        while (index < end) {
            crypto.remove(start);
            index++;
        }
    }
    
    public static void addtion() {
        int num = sc.nextInt();
        
        for (int i = 0; i < num; i++) {
            crypto.add(sc.nextInt());
        }
    }
    
}