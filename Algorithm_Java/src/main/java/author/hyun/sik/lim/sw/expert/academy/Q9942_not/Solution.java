package author.hyun.sik.lim.sw.expert.academy.Q9942_not;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 : 순열 1

// 길이 N의 순열 (N = 100 less than)
// (2, 3, 4, 4, 5) 순열 아님!
// 순열은 적당한 자연수를 순서를 섞어 만든 수열, 1 ~ N 까지 일치한 경우를 말함
// 또한 순서쌍이 존재한다.
// 길이 A1, A2, .., AN / B1, B2, .., BN 이 주어지고 순서상은 s(A,B)
// 각 인덱스의 두 수의 최댓값의 합으로 정의한다.
// Max(A1,B1) + Max(A2, B2) + .... MAX(AN, BN)
// 모든 길이 : N, 순열의 순서쌍 (N!)^2가지의 대해서 수열의 점수가 K이상인 순서쌍의 갯수룰 찾는다.
public class Solution {
    static int[] permutation;
    static boolean[] isPick;
    static int[] tempPermutation;
    static int max;
    static int stackIndex = 1;
    static int N;
    static int K;
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        
        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            permutation = new int[N+1];
            isPick = new boolean[N+1];
            tempPermutation = new int[N+1];
            max = 0;
            
            for (int x = 1; x <= N; x++) {
                permutation[x] = x;
            }
            dfs(1);
            System.out.println("#" + i + " " + max);
        }
    }

    private static void dfs(int size) {
        // TODO Auto-generated method stub
        if (size == permutation.length) {
            int sum = 0;
            for (int i = 1; i < permutation.length; i++) {
                sum += Math.max(tempPermutation[i], permutation[i]);
            }
            if (K >= sum)
                max += (permutation.length * 2);
            return;
        }
        
        for (int i = 1; i < permutation.length; i++) {
            if (!isPick[i]) {
                isPick[i] = true;
                tempPermutation[stackIndex++] += permutation[i];
                dfs(size+1);
                tempPermutation[--stackIndex] -= permutation[i];
                isPick[i] = false;
            }
        }
    }

}
