package author.hyun.sik.lim.sw.expert.academy.Q9659;

import java.util.Scanner;

// 다항식!
// N + 1개 다항식 존재
// f0(x) = 1;
// f1(x) = x;
// i >= 2 인 fi는 세정수 ti, ai, bi에 의해 결정
// ti = 1 : fi(x) = fai(x) + fbi(x)
// ti = 2 : fi(x) = ai * fbi(x)
// ti = 3 : fi(x) = fai(x) * fbi(x)
// M개의 수 x1, x2, ---, xM이 주어지면
// fN(x1), fN(x2), .... fN(xM) 계산하는 프로그램 작성


public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            // N 값 입력
            int N = sc.nextInt();
            
            
        }
    }
}
