package author.hyun.sik.lim.sw.expert.academy.Q9843;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


// 촛불 단 세우기 문제
// 이 문제는 계차 수열이다.
public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        //HashMap<Long, Integer> hash = new HashMap<>();
        
        // 정석 풀이
//        long sum = 0;
//        int i = 0;
//        for(i = 0; sum < Math.pow(10,18); i++) {
//            sum += i;
//            //hash.put(sum, i);
//            
//        }
        // 정석 풀이 끝
        
        // 하지만 여기서는 수열의 공식을 푼다.
        // ex) 1을 대입하면 (1 * 2)/2 = 1
        // ex) 10을 대입하면 (10 * 11) / 2 = 55
        
        // 계차 수열
        // an = (a1 - a2)(1/2)^(n-2)
        
        // 테스트 시작
        for(int test_case = 1; test_case <= T; test_case++) {
            long result = -1;
            long N = Long.parseLong(br.readLine());
            
            if (hash.containsKey(N)) {
                result = hash.get(N);
            }
            
            System.out.println(result);
        }
        
        br.close();
    }
    
}
