package author.hyun.sik.lim.backjoon;

//백준 10828
import java.util.Scanner;

public class backjoon10828{
    public static void main(String[] args){
         Scanner sc = new Scanner(System.in);         
         
         int N = sc.nextInt();
         
         Stacks stack = new Stacks();
         for (int i = 0; i < N; i++) {
             String str = sc.next();
             
             if (str.equals("push")) {
                 int X = sc.nextInt();
                 stack.push(X);
             } else if (str.equals("pop")) {
                 System.out.println(stack.pop());
             } else if (str.equals("size")) {
                 System.out.println(stack.size());
             } else if (str.equals("empty")) {
                 System.out.println(stack.empty());
             } else if (str.equals("top")) {
                 System.out.println(stack.top());
             } else {
             }
         }
    }
}

class Stacks {
 protected class Bucket {
     public int value;
     public Bucket next;
     public Bucket prev; 
 }

 protected Bucket head;
 protected Bucket tail;

 // 먼저 객체 선언시
 Stacks() {
     // 먼저 객체 준비한다.
     head = new Bucket();
     tail = head;
 }

 public void push(int value) {
     tail.value = value;

     Bucket bucket = new Bucket();
     tail.next = bucket;

     bucket.prev = tail;

     tail = bucket;
 }

 public int pop() {
     if(head == tail)
         return -1;

     int value = tail.prev.value;

     tail = tail.prev;

     return value;
 }

 public int size() {
     Bucket cursor = head;
     int count = 0;

     while (cursor != tail) {
         count++;
         cursor = cursor.next;
     }

     return count;
 }

 public int empty() {
     if (head == tail) return 1;
     else return 0;
 }

 public int top() {
     if (head == tail) return -1;
     else return tail.prev.value;
 }
}