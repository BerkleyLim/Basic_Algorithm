package author.hyun.sik.lim.sw.expert.academy.Q1225;

import java.util.Scanner;

// 암호 생성기 문제 Q1225

// n개의 수를 처리 시 8개 숫자 입력 받고,
// 첫 번째 숫자를 1 감소 후, 맨 뒤로 보냄
// 다음 첫 번째 수는 2 감소 후 맨 뒤로, 다음 첫번째 수 3 감소
// 다음 첫번째 수 4 감소, 다음 첫번째 수 5 감소
// 1 부터 5까지 감소를 하며 사이클 진행한다.
// 이와 같이 반복 작업을 실행하여 사이클 진행
// 숫자 감소 시 0보다 작아지는 경우 0으로 유지되며 프로그램 종료!
// 마지막 암호 배열은 모두 한 자리 수로 구성!

public class Solution {
    
    // 노드 객체 생성!
    static class Node {
        int data;
        Node next;
    }
    
    // 초기 값! (동적으로 사용 시 createLinkedQueue 메소드 혹은 생성자에 적용)
    static Node front;
    static Node rear;
    
    static final int SIZE = 8;
    // 저장 변수
    //static Node map;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 0;
        
        // 프로그래밍 시작!
        while(T < 10) {
            front = null;
            rear = null;
            T = sc.nextInt();
            //map = new Node();
            
            // 입력
            for (int i = 0; i < SIZE; i++) {
                enQueue(sc.nextInt());
            }
            
            int i = 1;
            // 알고리즘 실행
            while (true) {
                if (i == 6) 
                    i = 1;
                
                int check = logic(i);
                
                if(check <= 0) {
                    rear.data = 0;
                    break;
                }
                
                i++;
            }
            
            System.out.print("#" + T + " ");
            
            for (int x = 0; x < SIZE; x++) {
                System.out.print(deQueue() + " ");
            }
            System.out.println();
        }
    }
    
    public static int logic(int number) {
        Node node = new Node();
        node.data = front.data - number;
        node.next = null;
        
        front = front.next;
        rear.next = node;
        rear = node;
        
        return node.data;
    }
    
    public static void enQueue(int data) {
        // 초기화
        Node node = new Node();
        node.data = data;
        node.next = null;
        
        if (isEmpty()) {
            //map = node;
            front = node;
            rear = node;
        } else {
            //map.next = node;
            rear.next = node;
            rear = node;
        }
    }
    
    // 삭제 연결 리스트 프로그래밍! (연습용)
    public static int deQueue() {
        if(isEmpty()) return -1;
        else {
            Node node = front;
            front = front.next;
            return node.data;
        }
    }
    
    // 연결 리스트! 비어있는지 검사
    public static boolean isEmpty() {
        return (front == null && rear == null);
    }
}
