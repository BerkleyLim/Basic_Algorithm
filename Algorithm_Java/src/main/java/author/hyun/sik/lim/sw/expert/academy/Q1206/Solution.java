package author.hyun.sik.lim.sw.expert.academy.Q1206;

import java.util.Scanner;

// 강변, 빌딩들이 옆으로 빽빽하게 밀집한 지역
// 강변을 보면 빌딩들이 너무 좌우로 밀집하여, 강에 대한 조망은 좋고, 왼쪽 오른쪽 창문 열시 바로 앞 옆건물 보임
// 왼쪽 오른쪽 창문 열시, 양쪽 모두 거리 2이상 공간이 확보되면 조망권이 확보
// 조망권 확보된 세대 수 반환!
// 가로길이 : 항상 1000이하
// 각 빌딩 높이 : 최대 255
// 가장 왼쪽 두칸, 가장 오른쪽 두칸은 건물이 지어지지 않음 
// 총 10개 테스트 케이스
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        
        for(int test_case = 1; test_case <= T; test_case++) {
            int length = sc.nextInt(); // 건물 가로 길이
            
            int[] height = new int[length]; // 건물 높이
            
            for(int i = 0; i < length; i++) {
                height[i] = sc.nextInt();
            }
            
            int answer = 0;
            
            // searching
            for(int i = 2; i < length-2; i++) {
                int temp = 9999;
                temp = Math.min(temp,height[i]-height[i-1]);
                temp = Math.min(temp,height[i]-height[i-2]);
                temp = Math.min(temp,height[i]-height[i+1]);
                temp = Math.min(temp,height[i]-height[i+2]);
                
                if(temp > 0) 
                    answer += temp;
            }
            
            System.out.println("#" + test_case + " " + answer);
        }
    }
}