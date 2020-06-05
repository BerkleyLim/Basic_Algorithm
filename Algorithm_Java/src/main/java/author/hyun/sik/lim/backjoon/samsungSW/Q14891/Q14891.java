package author.hyun.sik.lim.backjoon.samsungSW.Q14891;

import java.util.Scanner;

// 문제 : https://www.acmicpc.net/problem/14891
// 톱니바퀴 문제!

// 여기서 4개의 톱니바퀴가 존재하고, 톱니바퀴 안에 8개의 내용이 담겨있음
// 여기는 8개 안에 N극, S극이 존재하고 N극은 0, S극은 1이다
// 다음은 원형 연결리스트(다중 연결리스트를 이용하면 풀이하면 될 것 같다.
public class Q14891 {
    static int jumsu = 0;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 다음은 Collection으로 이용한 문제풀이
        int[][] gears = new int[5][8];
        
        // 4개의 톱니바퀴 입력!
        for (int y = 1; y <= 4; y++) {
            String str = sc.next();
            String[] arr = str.split("");
            for (int i = 0; i < 8; i++) {
                gears[y][i] = Integer.parseInt(arr[i]);
            }
        }
        
        jumsu = around(gears, sc);
        System.out.println(jumsu);
    }
    
    static int around(int[][] gears, Scanner sc) {
        int k = sc.nextInt();
        
        int result = 0;
        int number;
        int direction;
        while (k-- > 0) {
            number = sc.nextInt();
            direction = sc.nextInt();
            
            // 톱니바퀴 기준!
            // 왼쪽방향
            left(number -1, -direction, gears);
            // 오른쪽방향
            right(number +1, -direction, gears);
            // 돌리기
            rotation(number, direction, gears);
        }
        
        if (gears[1][0] == 1)
            result += 1;
        if (gears[2][0] == 1)
            result += 2; 
        if (gears[3][0] == 1)
            result += 4; 
        if (gears[4][0] == 1)
            result += 8; 
        
        return result;
    }
    
    // 계속 돌리기
    public static void rotation(int number, int direction, int[][] gears) {
        // 시계방향일때
        if (direction == 1) {
            int temp = gears[number][7];
            for (int i = 7; i > 0; i--) {
                gears[number][i] = gears[number][i-1];
            }
            gears[number][0] = temp;
        
        // 반시계방향일때
        } else {
            int temp = gears[number][0];
            for (int i = 1; i < 8; i++) {
                gears[number][i-1] = gears[number][i];
            }
            gears[number][7] = temp;
        }
    }
    
    // 여기서 N극 S극과 만나는지 검사
    public static boolean check(int a, int b, int[][] gears) {
        if (a > b) {
            // 오른쪽 톱니 바퀴 검사!
            if (gears[a][6] == gears[b][2]) {
                return false;
            } else {
                return true;
            }
        } else {
            // 왼쪽 톱니 바퀴 검사!
            if (gears[a][2] == gears[b][6]) {
                return false;
            } else {
                return true;
            }
        }
    }

    // 오른쪽 
    static void right(int number, int direction, int[][] gears) {
        if (!(1 <= number && number <= 4))
            return;

        if (check(number, number - 1, gears)){
            right(number + 1, -direction, gears);
            rotation(number, direction, gears);
        }
        
    }
    
    // 왼쪽
    static void left(int number, int direction, int[][] gears) {
        if (!(1 <= number && number <= 4))
            return;
        
        if (check(number, number + 1, gears)){
            left(number - 1, -direction, gears);
            rotation(number, direction, gears);
        }
        
    }
}

// 참고자료 : https://mygumi.tistory.com/306