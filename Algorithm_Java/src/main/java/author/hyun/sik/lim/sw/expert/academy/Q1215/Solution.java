package author.hyun.sik.lim.sw.expert.academy.Q1215;

import java.util.Scanner;

//8 * 8 맵이 주어짐
//회문의 길이 : 최대 8
//가로 세로만 회문 수행
//회문 검색시 길이별로 검사
class Solution {
     private static final int SIZE = 8; // 맵의 길이 : 정사각형
     public static void main(String args[]) throws Exception {
         Scanner sc = new Scanner(System.in);
         int T = 10;
         for(int test_case = 1; test_case <= T; test_case++) {
             int searchRange = sc.nextInt();
             sc.nextLine();
             int[][] map = new int[SIZE][SIZE];
             
             for (int y = 0; y < SIZE; y++) {
                 String str = sc.nextLine();
                 for (int x = 0; x < SIZE; x++) {
                     map[y][x] = str.trim().charAt(x);
                 }
             }
             
             int count = 0;
             
             for (int initial = 0; initial < SIZE; initial++) {
                 for (int index = 0; index <= SIZE - searchRange; index++) {
                     // 행 우선 검사
                     boolean length = true;
                     int row = index;
                     for (int subIndex = searchRange + index - 1; subIndex >= index + (searchRange / 2); subIndex--) {
                         if (map[initial][row++] != map[initial][subIndex]) {
                             length = false;
                             break;
                         } 
                     }
                     if(length) count++;
                     
                     // 열 우선 검사
                     boolean width = true;
                     int col = index;
                     for (int subIndex = searchRange + index - 1; subIndex >= index + (searchRange / 2); subIndex--) {
                         if (map[col++][initial] != map[subIndex][initial]) {
                             width = false;
                             break;
                         } 
                     }
                     
                     if(width) count++;
                 }
             }
             
             System.out.println("#"+test_case+" "+count);
         }
     }
}
