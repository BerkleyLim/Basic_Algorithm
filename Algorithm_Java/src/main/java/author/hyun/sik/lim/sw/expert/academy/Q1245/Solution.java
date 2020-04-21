package author.hyun.sik.lim.sw.expert.academy.Q1245;

import java.util.Scanner;

// 각 균형점을 찾는 문제!
// 좌표값의 오차범위 : 10^(-12) * (1e - 12)
// 각각의 자성체가 2 ~ 10개 존재

class Solution {
    static int N;           // 자성체 개수
    static double[] x;         // 자성체 위치 좌표
    static double[] m;         // 자성체 질량
    static double[] answers;    // 균형점 정답
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            
            x = new double[N];
            m = new double[N];
            
            // 좌표 입력
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
            }
            
            // 질량 입력
            for (int i = 0; i < N; i++) {
                m[i] = sc.nextInt();
            }
            
            // 알고리즘 시작
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < N-1; i++) {
                double answer = solve(i);
                System.out.printf("%.10f ", answer);
            }
            System.out.println();
        }
    }
    
    public static double solve(int index) {
     // 오차범위 설정
        double rangeDot = 
                Math.abs(Math.pow(10,-12) * (Math.exp(1) - 12));
        
        double left = x[index];
        double right = x[index + 1];
//        double result = Math.abs(left - right);
        
        double answer = 0;
        int k = 0;
        while(k < 50) {
            double mid = (left + right) / 2;
            
            double sum = 0;
            
            for (int i = 0; i <= index; i++) {
                sum += m[i] / ((x[i] - mid) * (x[i] - mid));
            }
            
            for (int i = index + 1; i < N; i++) {
                sum -= m[i] / ((x[i] - mid) * (x[i] - mid));
            }
            
            if (sum > rangeDot) {
                left = mid;
            } else {
                right = mid;
                answer = mid;
            }
            k++;
//            double mid = (left + right) / 2;
//            
//            double Dl = Math.abs(x[index] - mid);
//            double Dr = Math.abs(mid - x[index+1]);
//            double Fl = m[index] / (Dl * Dl);
//            double Fr = m[index+1] / (Dr * Dr);
//            
//            // 왼쪽 인력가 작으면 아니면 오른쪽 거리가 작으면 (오차범위 벗어났을때도 포함)
//            if (Fl < Fr && !(rangeDot > result)) {
//                right = mid;
//            } else if (Fl > Fr && !(rangeDot > result)){
//                left = mid;
//            } else {
//                return mid;
//            }
//            
//            result = Math.abs(Fl -  Fr);
            
        }
        return answer;
    }
}