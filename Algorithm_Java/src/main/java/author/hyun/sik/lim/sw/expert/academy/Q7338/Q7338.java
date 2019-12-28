package author.hyun.sik.lim.sw.expert.academy.Q7338;

import java.util.Scanner;

// 문제 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWmYcWyqAcQDFAV3
// 7338. 현규의 연봉계산법
// 2016년도 기준부터 1년을 12개월이라면 1년을 13개월로 바꾸기

public class Q7338 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        
        // 2016년도 기준으로부터 시작!
        long startYear = 2016;

        for(int test_case = 1; test_case <= T; test_case++) {
            // 년도, 달 입력!
            long y = sc.nextLong();
            long m = sc.nextLong();
            
            if (y < 2016 && m <= 0 && m > 12) {
                System.out.println("2016년도 이상, 1~12월까지 입력");
                continue;
            }
            
            y -= startYear;
            
            // 2016년도 부터 년도와 달을 이용하여 총 달까지 구하기
            long totalMonth = (y * 12) + m;
            
            long changeYear = (totalMonth / 13) + startYear;
            
            long changeMonth = 0;
            if ((totalMonth % 13) == 0) {
                changeYear -= 1;
                changeMonth = 13;
            } else {
                changeMonth = totalMonth % 13;
            }
            
            // 여기서 부터 결과 값 추출! (1년의 13개월)
            System.out.println("#" + test_case + " " +
                    changeYear + " " + changeMonth);
        }
    }
}
