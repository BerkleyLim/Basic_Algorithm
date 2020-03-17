package author.hyun.sik.lim.sw.expert.academy.Q1217;

import java.util.Scanner;

// 거듭제곱 구하는 알고리즘

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {
            int t = sc.nextInt();
            // 알고리즘 시작
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println("#" + test_case + " " + recursive(n,m));
        }
    }
    
    // n^m 알고리즘 구현
    public static int recursive(int n, int m) {
        if(m <=1) return n;
        return n * recursive(n, m-1);
    }
}