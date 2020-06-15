package author.hyun.sik.lim;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        //2. 1 ~ N 사이 소수의 개수 반환 함수 테스트
        System.out.println(solution(10));
        System.out.println(solution(5));
        
        // 1. 최대공약수, 최소 공배수 테스트
//        int[] arr = solution(3,12);
//        for (int i = 0; i < arr.length; i++)
//            System.out.print(arr[i] + " ");
//        System.out.println();
//        
//        arr = solution(2,5);
//        for (int i = 0; i < arr.length; i++)
//            System.out.print(arr[i] + " ");
//        System.out.println();
    }
    
    // 2. 1 ~ N 사이 소수의 개수 반환 함수
    public static int solution(int n) {
        int answer = 1;

        for (int i = 3; i <= n; i+=2) {
            int count = 0;
            for (int j = 3; j < i; j +=2) {
                if ((i % j) == 0) {
                    count++;
                    break;
                }
            }
            if (count == 0)
                answer++;
        }  

        return answer;
    }

//    1. 최대공약수, 최소 공배수 풀이
//    private static int[] solution(int n, int m) {
//        // TODO Auto-generated method stub
//        int[] arr = new int[2];
//        int G = 0; // 최대공약수
//        int L = 0; // 최소공배수
//        
//        // 두 자연수의 곱 = 최대공약수 * 최소공배수
//        int gop = n * m;
//        
//        for (int i = 1; i <= Math.min(n,m); i++) {
//            if (n % i == 0 && m % i == 0)
//                G = i;
//        }
//        arr[0] = G;
//        L = gop / G;
//        arr[1] = L;
//        return arr;
//    }

}
