package author.hyun.sik.lim.sw.expert.academy.Q9843;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


// 촛불 단 세우기 문제
public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        // 테스트 시작
        for(int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(br.readLine());
            long k = 0;
            
            N *= 2;
            k = (long) Math.sqrt(N);    // 제곱근 구하기(루트)
            
            System.out.println("#" + test_case + " " + ((k * (k + 1) == N) ? k :-1));
        }
        
        br.close();
    }
    
}
