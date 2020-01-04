package author.hyun.sik.lim.sw.expert.academy.Q1216;

import java.util.Scanner;

// 100 * 100 맵이 주어짐
// 가로 세로 중 가장 긴 회문의 길이를 구하는 문제
class Solution {
     private static final int SIZE = 100; // 맵의 길이 : 정사각형
     public static void main(String args[]) throws Exception {
         Scanner sc = new Scanner(System.in);
         int T = 10;
         for(int test_case = 1; test_case <= T; test_case++) {
             int num = sc.nextInt();
             sc.nextLine();
             
             int[][] map = new int[SIZE][SIZE];
             
             for (int y = 0; y < SIZE; y++) {
                 String str = sc.next();
                 for (int x = 0; x < SIZE; x++) {
                     map[y][x] = str.trim().charAt(x);
                 }
             }
             
             
             int max = 0;
             // 탐색 시작
             for (int start = 0; start < SIZE; start++) {
                 // 조건문에서 최대 길이(max)가 구해졌을 때 자동으로 반복문 종료
                 for (int end = SIZE - 1; end > start + max -1 ; end--) {
                     // 길이 구하기, 초기 값 부터 끝깞까지의 길이
                     int length = end - start + 1;
                     int binarySearch = length / 2;
                     
                     // 열 우선 검사
                     for (int index = 0; index < SIZE; index++) {
                         boolean isRow = true;
                         for(int subIndex = 0; subIndex < binarySearch; subIndex++) {
                             if (map[start+subIndex][index] != map[end-subIndex][index]) {
                                 isRow = false;
                                 break;
                             }
                             
                         }
                         
                         if(!isRow) continue;
                         if(length > max) max = length;
                     }
                     
                     // 행 우선 검사
                     for (int index = 0; index < SIZE; index++) {
                         boolean isCol = true;
                         for (int subIndex = 0; subIndex < binarySearch; subIndex++) {
                             if (map[index][start+subIndex] != map[index][end-subIndex]) {
                                 isCol = false;
                                 break;
                             }
                         }
                         
                         if(!isCol) continue;
                         if(length > max) max = length;
                     }
                 }
             }
             
             System.out.println("#"+num+" "+max);
         }
     }
}
