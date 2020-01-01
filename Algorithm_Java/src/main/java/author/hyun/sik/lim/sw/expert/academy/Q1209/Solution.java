package author.hyun.sik.lim.sw.expert.academy.Q1209;

import java.util.Scanner;

// 100 * 100의 2차원 배열이 주어지고
// 각 행, 열, 대각선 합을 구한후 최댓값 구하는 프로그램 작성

// 조건 : 10개의 테스트 케이스 주어짐
// 배열 크기 : 항상 100 * 100
// 각 행의 합 : integer 범위 넘어가면 안됨
// 동일 최댓값이 주어지면 하나의 값만 출력
public class Solution {

    private static final int DEGREE = 100;
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            int num = sc.nextInt();
            
            int[][] map = new int[DEGREE][DEGREE];
            
            for (int y = 0; y < DEGREE; y++) {
                for (int x = 0; x < DEGREE; x++) {
                    map[y][x] = sc.nextInt();
                }
            }
            
            // 최댓값 나타내는 숫자
            int max = 0;
            
            // 대각선 1
            for (int i = 0; i < DEGREE; i++) {
                max += map[i][i];
            }
            
            int temp = 0;
            for (int i = 0; i < DEGREE; i++) {
                temp += map[i][DEGREE-1];
            }
            
            max = Math.max(max, temp);
            
            // 먼저 기준점 잡기
            for (int i = 0; i < DEGREE; i++) {
                int rowSum = 0;
                int colSum = 0;
                
                // 행우선, 열우선 순으로 더하기
                for (int sub = 0; sub < DEGREE; sub++) {
                    rowSum += map[i][sub];
                    colSum += map[sub][i];
                }
                
                max = Math.max(max,rowSum);
                max = Math.max(max,colSum);
            }
            
            System.out.println("#"+num+" "+max);
        }
    }

}
