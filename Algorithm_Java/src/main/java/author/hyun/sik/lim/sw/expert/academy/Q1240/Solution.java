package author.hyun.sik.lim.sw.expert.academy.Q1240;

import java.util.Scanner;

// 단순 2진 암호코드
// 암호 찾는 문제
// 0 ~ 9를 주어지는 암호 추가

class Solution {
    // 패스워드 문 추가
    static final String[] password = 
        {"0001101", "0011001", "0010011", "0111101", "0100011",
         "0110001", "0101111", "0111011", "0110111", "0001011"};
    
//    static String[] map;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            // 세로 크기 : N, 가로 크기 : M
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
            
//            map = new String[N];
            
            String row = null;
            for (int i = 0; i < N; i++) {
//                map[i] = sc.nextLine();
                String str = sc.nextLine();
                
                // 1이 포함시! 포함시 부터 진행!
                if (row == null && str.contains("1")) {
                    row = str;
                    //break; // 입력되는 문자열은 항상 같으므로 반복문 돌리는 것을 최소화
                }
            }
            
            // 1이 포함된 위치가 가장 끝에부터 위치 추출
            int lastIndex = row.lastIndexOf('1');
            
            // row, 즉 암호문만 추출
            row = row.substring(lastIndex-55, lastIndex+1);
            
            int[] ca = new int[8];
            for (int i = 0; i < 8; i++) {
                ca[i] = -9999;
                for (int pindex = 0; pindex <=9; pindex++) {
                    String search = row.substring(i*7, i*7+7);
                    if (search.equals(password[pindex])) {
                        ca[i] = pindex;
                        break;
                    }
                }
            }
            
            int result = ((ca[0] + ca[2] + ca[4] + ca[6]) * 3)
                    + (ca[1] + ca[3] + ca[5]) + ca[7];
            
            // 결과물 출력
            if (result % 10 == 0 && result >= 10) {
                int answer = 0;
                for (int i = 0; i < 8; i++) {
                    answer += ca[i];
                }
                System.out.println("#" + test_case + " " + answer);
            } else {
                System.out.println("#" + test_case + " " + 0);
            }
            
        }
    }
}