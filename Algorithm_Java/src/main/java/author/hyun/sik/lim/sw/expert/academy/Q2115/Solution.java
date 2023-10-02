package author.hyun.sik.lim.sw.expert.academy.Q2115;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    // 벌꿀 체취
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        /*
           여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        */

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int C = sc.nextInt();
            
            // [세로][가로][합] - 합 방이 0 일 때, 기본값
            int[][] boards = new int[N+1][N+1]; 
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    boards[y][x] = sc.nextInt();
                    
                }
            }
            System.out.println("#" + test_case + " " + solution(N, M, C, boards));
        }
    }

    private static int solution(int N, int M, int C, int[][] boards) {
        // TODO Auto-generated method stub
        int max = 0;
        
        int[][][] nodes = new int[N+1][N+1][N+1];
        int[] length = new int[M+1];
        int[] maxY = new int[N+1];
        // N : 11, M : 5 라 가정하면
        // dp식은 dp[y][x][count], dp가 node와 비슷하니 여기서 node라 칭한다
        // y번째 배열에서 x가 m만큼 검사하여 m번째당 count가지로 구성된다
        // 단 x = 0, count = 0에서는 y번재 행에서 하나만 고를때 가정하여 최대값으로 나타낸다
        // count가 2 이상일때 최대값만 추출하여 사람 수 저장
        // 아니 경우 사람 한명이 골랐다고 가정하여 그 값으로 계산한다
        
        for (int y = 1; y <= N; y++) {
            
            for (int x = 1; x <= M; x++) {
                int count = 0;
                int maxPrice = 0;
                
                for (int m = x; m <= N; m += M) {
                    count = 0;
                    int room = 0;
                    length = new int[M+1];
                    
                    for (int t = m; t < m+M; t++) {
//                        System.out.print(y + " " + t + " ");
                        
                        if (t > N)
                            break;
//                        System.out.print(boards[y][t] + " ");
                        length[room] = boards[y][t];
                        room++;
                    }
//                    System.out.println();
                    
                    // N보다 큰 경우 더이상 비교할 필요가 없다
                    if (room != M)
                        break;
                    
//                    for (int i : length) {
//                        System.out.print(i + " ");
//                    }
//                    System.out.println();
                    // 오름차순 정렬 실시
                    Arrays.sort(length);
                    flag = false;
                    maxPrice = Math.max(backtracking(0, 0, length, C, room, M, 0), maxPrice);
                    
//                    System.out.println();
//                    System.out.println(maxPrice);
//                    System.out.println();
                    
                    nodes[y][x][++count] = maxPrice;
//                    System.out.println(nodes[y][x][++count]);
                    maxY[y] = Math.max(maxPrice, maxY[y]); // y 값 최대를 뽑아내기 위한 과정
                    
                }
//                System.out.println();
                
//                System.out.println(count);
                // 오름차순 정렬 실시
                Arrays.sort(nodes[y][x]);
                // 여기서 x번 검사하여 count 가지가 2개 이상 나올시
//                System.out.println(count);
                if (count > 1)
                    max = Math.max(nodes[y][x][count] + nodes[y][x][count-1] , max);
//                System.out.println(max);
            }
//            System.out.println();
        }
        
        Arrays.sort(maxY);
//        for (int i : maxY) {
//            System.out.print(i + " ");
//        }
//        System.out.println(maxY[N] + " " + maxY[N-1]);
//        System.out.println(max);
        max = Math.max(max, maxY[N] + maxY[N-1]);
        
        return max;
    }
    
    static boolean flag;
    private static int backtracking(int sum, int price, int[] array, int C,  int i, int M, int depth) {
        if (M == depth) {
            flag = true;
            return 0;
        }
        int max = 0;
        for (int x = i; x >= 1; x--) {
            if (sum + array[x]<= C) {
                int hap = sum + array[x];
                int p = price + (array[x] * array[x]);
//                System.out.print(p + " ");
                max = Math.max(max, p);
                max = Math.max(backtracking(hap, p, array, C, x-1, M, depth+1), max);
                
                if (flag)
                    break;
            }
            else {
                continue;
            }
            
        }
        return max;
    }

}
