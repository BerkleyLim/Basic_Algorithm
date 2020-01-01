package author.hyun.sik.lim.sw.expert.academy.Q1208;

import java.util.Arrays;
import java.util.Scanner;

// Flatten
// 높은상자를 낮은 곳에 옮기는 방식, 최고점과 최저점의 간격을 줄이는 작업(덤프)
// 평탄화 수행
// 가장 높은곳과 가장 낮은곳의 차이가 최대 1 이내!
// 상자를 옮기는 작업 횟수에 제한 걸리고 있고, 제한된 횟수만큼 옮긴다
// 따라서 최고점과 최저점의 차이는?
// 덤프 : 가장 높은곳에 있는 상자를 가장 낮은 곳으로 옮기는 작업!

// 필요 조건
// 가로 길이 : 항상 100
// 상자의 높이 : 1 ~ 100
// 덤프 횟수 : 1 ~ 1000
public class Solution {

    private static final int LENGTH = 100; // 가로길이 상수
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            int dump = sc.nextInt();
            int[] countArray = new int[101]; // 카운트 정렬(height)
            // 최대 높이 지정
            int[] height = new int[LENGTH + 1];
            
            for (int i = 1; i < LENGTH + 1; i++) {
                height[i] = sc.nextInt();
            }
            
            Arrays.sort(height);
            
            // 덤프 관련 알고리즘 실행
            for (int i = 1; i <= dump; i++) {
                height[1]++;
                height[LENGTH]--;
                Arrays.sort(height);
            }
            int answer = height[LENGTH] - height[1];
            System.out.println("#"+test_case+" "+answer);
        }
    }

}
