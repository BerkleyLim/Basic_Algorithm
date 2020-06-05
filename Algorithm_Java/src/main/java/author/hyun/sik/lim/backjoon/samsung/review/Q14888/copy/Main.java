package author.hyun.sik.lim.backjoon.samsung.review.Q14888.copy;

import java.util.Scanner;

// 연산자 끼워넣기
// 정수 : 최대 갯수 11개
// 연산자 최대 대입 방법 : 11 * 10
// 연산자 갯수 : 정수갯수 - 1

// 여기서 최대값, 최소값 출력
public class Main {
    static int N;
    static int[] A;     // 정수 갯수
    static int[] oper; // 0 : 덧셈, 1 : 뺄셈, 2 : 곱셈, 3 : 나눗셈 개수
    
    static int max;
    static int min;
    static boolean[] isPick;
    static int[] calc; // 임시 배열 생성(연산자)
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        calculator();
        System.out.println(max);
        System.out.println(min);
    }
    
    
    private static void calculator() {
        // TODO Auto-generated method stub
        isPick = new boolean[N-1];
        calc = new int[N-1];
        dfs(0);
    }

    private static void dfs(int count) {
        // TODO Auto-generated method stub
        if (count == N-1) {
            calcprocess();
            return;
        }
        
        for (int i = 0; i < N-1; i++) {
            if (!isPick[i]) {
                isPick[i] = true;
                int temp = oper[i];
                calc[count] = temp;
                dfs(count+1);
                isPick[i] = false;
            }
        }
    }


    private static void calcprocess() {
        // TODO Auto-generated method stub
        int sum = A[0];
        
        for (int i = 0; i < N-1; i++) {
            switch(calc[i]) {
            case 0:
                sum += A[i+1];
                break;
            case 1:
                sum -= A[i+1];
                break;
            case 2:
                sum *= A[i+1];
                break;
            case 3:
                sum /= A[i+1];
                break;
            }
        }
        
        max = Math.max(max, sum);
        min = Math.min(min, sum);
    }


    private static void init() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        A = new int[N];
        oper = new int[N-1];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        
        int temp = 0;
        for (int i = 0; i < 4; i++) {
            int range = sc.nextInt();
            
            for (int r = 0; r < range; r++) {
                oper[temp++] = i;
            }
        }
        
        sc.close();
    }

}
