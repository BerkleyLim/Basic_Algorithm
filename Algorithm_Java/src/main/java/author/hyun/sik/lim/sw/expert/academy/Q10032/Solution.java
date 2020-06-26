package author.hyun.sik.lim.sw.expert.academy.Q10032;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 과자 분배
// 세정이가 N개의 과자를 K명에게 사람들에게 분배한다.
// 과자 쪼개기 불가능하고 반드시 모든 과자를 분배한다.

// 세정이가 공평한 분배를 위해 과자를 가장 많이 받은 사람과 적게 받은 사람의 과자 수 차이를 최소화!
// 최소 차이를 구하는 프로그램을 작성한다.


public class Solution {
    static int N;
    static int K;
    
    public static void main(String[] args) throws Exception {
        init();
    }

    private static void init() throws Exception{
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        if ((N % K) == 0)
            return 0;
        else
            return 1;
    }
}
