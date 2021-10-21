package author.hyun.sik.lim.backjoon.dp.Q11066;

import java.util.Scanner;

public class Main {
    // 파일 합치기
    // 소설을 여러장으로 나눠 쓰지만
    // 각 장은 각각 다른 파일로 저장하기도 함
    // 모든 장을 쓴 이후 각 장에 쓰여진 파일을 합쳐 한 개의 파일로 만든다
    // 두개의 파일을 합쳐서 하나의 임시파일을 만들고,
    // 임시파일 or 원래 파일을 계속 두 개씩 합쳐서 소설의 여러장들이 연속이 되도록 파일을 합쳐 나간다.
    // 이후, 하나의 파일로 합친다.
    // 두 개의 파일 합칠 시 필요 시간이 두 파일 크기의 합이고,
    // 최종적인 한 개의 파일을 완성시키는데 필요한 비용의 총 합은? (최소 비용을 구할 것)
    
    /**
     * 참조 : https://pangtrue.tistory.com/302
     * 커누스 최적화
     * A[i][j-1] <= A[i][j] <= A[i+1][j]
     */
    static int[] sum;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        int[] t = new int[T];
        for (int test_case = 1; test_case <= T; test_case++) {
            int K = sc.nextInt();
            int[] fileSize = new int[K+1];
            sum = new int[K+1];
            
            for (int i = 1; i <= K; i++) {
                fileSize[i] = sc.nextInt();
                sum[i] = sum[i-1] + fileSize[i];
            }
            
            t[test_case-1] = soluton(K, fileSize);
        }
        
        for (int i : t) {
            System.out.println(i);
        }
        
    }
    
    static int[][] dp;
    static int[][] knuth;
    private static int soluton(int K, int[] fileSize) {
        // TODO Auto-generated method stub
        int answer = Integer.MAX_VALUE;
        dp= new int[K+1][K+1];
        knuth = new int[K+1][K+1];
        
        for (int i = 1; i <= K; i++) {
            dp[i-1][i] = 0;
            knuth[i-1][i] = i;
        }
        for (int d = 2; d <= K; d++) {
            for (int i = 0; i + d <= K; i++) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = knuth[i][j - 1]; k <= knuth[i + 1][j]; k++) {
                    int v = dp[i][k] + dp[k][j] + (sum[j] - sum[i]);
                    if (dp[i][j] > v) {
                        dp[i][j] = v;
                        knuth[i][j] = k;
                    }
                }
            }
        }
        
        // 이분 탐색부터 시작하기
//        answer = dpSearch(1, K, fileSize);
        // DP 기본 코드
        // 단 최적화가 안됨
        // 시간복잡도 : N^3
//          for (int i = 2; i <= K; i++) {
//              for (int j = i - 1; j > 0; j--) {
//                  dp[j][i] = Integer.MAX_VALUE;
//                  for (int k = j; k <= i; k++) {
//                      dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i]);
//                  }
//                  dp[j][i] += sum[i] - sum[j - 1]; // 마지막에 전체 합을 한 번 더해준다.
//              }
//          }
        
        
        return dp[0][K];
    }
//    
//    private static int dpSearch(int left, int right, int[] fileSize) {
//        if (dp[right][left] != Integer.MAX_VALUE)
//            return dp[right][left];
//        
//        // 노드가 1개일때
//        if (right == left) {
//            return dp[right][left] = 0;
//        }
//        
//        // 노드가 2개일 때
//        if (right == left + 1) {
//            return dp[right][left] = fileSize[left] + fileSize[right];
//        }
//        
//        for (int i = left;  i < right; i++) {
//            int l = dpSearch(left, i, fileSize);
//            int r = dpSearch(i, right, fileSize);
//            
//            // 마지막에 dp공식을 나타낸다
//            dp[right][left] = Math.min(dp[right][left], l + r);
//        }
//        return dp[right][left] += sum[right] - sum[left - 1];
//    }
}
