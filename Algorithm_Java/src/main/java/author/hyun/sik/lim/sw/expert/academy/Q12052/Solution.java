package author.hyun.sik.lim.sw.expert.academy.Q12052;

import java.util.Scanner;

public class Solution {
    // 부서진 타일
    // 부서진 타일을 2 * 2 로 덮는다.
    // 단 주변에 정상 타일일 경우는 붙일 수 없다
    // 부서진 타일 : # / 정상 타일 : .
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            boolean isAnswer = true;
            int N = sc.nextInt();
            int M = sc.nextInt();
            char[][] tile = new char[N][M];
            
            for (int y = 0; y < N; y++) {
                String str = sc.next();
//                sc.nextLine();
                for (int x = 0; x < M; x++) {
                    tile[y][x] = str.charAt(x);
                }
            }
            
            for (int y = 0; y < N; y++) {
                if (y == N-1) {
                    for (int x = 0; x < M; x++) {
                        if (tile[y][x] == '#') {
                            isAnswer = false;
                            break;
                        }
                    }
                }  else {
                    for (int x = 0; x < M -1; x++) {
                        if (tile[y][x] == '#') {
                            if (tile[y+1][x] == '#' && tile[y][x+1] == '#' && tile[y+1][x+1] == '#') {
                                tile[y][x] = '.';
                                tile[y+1][x] = '.';
                                tile[y][x+1] = '.';
                                tile[y+1][x+1] = '.';
                                x++;
                            } else {
                                isAnswer = false;
                                break;
                            }
                        }
                    }
                }
                
                if (tile[y][M-1] == '#') {
                    isAnswer = false;
                    break;
                }
            }
            
            System.out.println("#" + test_case + " " + ((isAnswer)? "YES" : "NO"));
        }
    }

}
