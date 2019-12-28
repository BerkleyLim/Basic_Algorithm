package author.hyun.sik.lim.sw.expert.academy.Q1204;

import java.util.Scanner;

// 고등학교 1000명 수학성적, 각 학생의 점수 0 ~ 100
// 최빈수를 이용해 학생들의 평균 수준 짐작
// 10, 8, 7, 2, 2, 4, 8, 8, 8, 9, 5, 5, 3
// 최빈수 8
// 최빈수 출력 프로그램 : Greedy Algorithm
// 최빈수가 여러개일때 가장 큰 점수 출력
// 최빈수 : 데이터가 자주 나오는 값
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int number = sc.nextInt(); // 테스트 케이스 번호
            int[] count = new int[101];
            
            for(int i = 0; i < 1000; i++) {
                count[sc.nextInt()] += 1;
            }
            
            int answer = -1;
            int temp = -1;
            for(int i = 0; i <= 100; i++) {
                if (count[i] >= temp) {
                    temp = count[i];
                    answer = i;
                }
            }
            
//            if (test_case==3) {
//                for(int i = 0; i <= 100; i++) {
//                    System.out.println(i + " : " + count[i]);
//                }
//            }
            System.out.println("#"+number+" " + answer);
        }
    }
}