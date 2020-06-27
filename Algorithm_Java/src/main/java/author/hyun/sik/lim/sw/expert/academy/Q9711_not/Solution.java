package author.hyun.sik.lim.sw.expert.academy.Q9711_not;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 제곱수의 합 구하기
// n, k가 주어지면 f(n,k) 구하기
// 문제 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXDNHJ-6aiIDFAVX&categoryId=AXDNHJ-6aiIDFAVX&categoryType=CODE

// 솔루션 공식
// 다음과 같이 참고한다
// 출처 : https://mathbang.net/628

// 유도법칙 
// (x + 1)^(k+1) - x^(k+1)
// 결론 : N^(k+1) - 1
// 여기서 나올 수 있는 공식!

// 단 출력값은 
// f(n,k) mods 1,000,000,007 값으로 표시

public class Solution {
    static int N;
    static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            System.out.println("#"+test+" "+solution());
        }
        br.close();
    }

    private static int solution() {
        // return ((int) Math.pow(((N*(N+1))/2),K)) % 1000000007;
        return ((int) Math.pow(N, (K+1)) - 1) % 1000000007;
    }
}
