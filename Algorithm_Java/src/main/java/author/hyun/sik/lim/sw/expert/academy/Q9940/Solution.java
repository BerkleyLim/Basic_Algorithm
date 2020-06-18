package author.hyun.sik.lim.sw.expert.academy.Q9940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 : 순열 1

// 길이 N의 순열 (N = 100,000 less than)
// (2, 3, 4, 4, 5) 순열 아님!
// 순열은 적당한 자연수를 순서를 섞어 만든 수열, 1 ~ N 까지 일치한 경우를 말함
// 범위 초과시 혹은 중복이 있을 경우 순열 아님을 증명
public class Solution {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            boolean[] isPermutation = new boolean[N+1];
            
            boolean isTrue = true;
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                int value = Integer.parseInt(st.nextToken());
                if (isPermutation[value]) {
                    isTrue = false;
                    break;
                } else {
                    isPermutation[value] = true;
                }
            }
            
            System.out.println("#" + i + " " + ((isTrue)? "Yes": "No" ));
        }
    }

}
