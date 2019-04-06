package author.hyun.sik.lim.backjoon.backjoon14499;

import java.util.Scanner;

// 문제 : https://www.acmicpc.net/problem/14499
// 다음은 주사위 굴리기 문제입니다.
// 자세한건 여기 홈페이지 참조

public class Q14499 {
    // 주사위 전개도를 각 배열의 방으로 저장한다.
    // ex)
    //    0
    //  1 2 3
    //    4
    //    5
    // 이라 주어졌을 때 고대로 배열 정의 한다!
    // 참고로 윗면이 2번방, 밑면이 5번방
    private static int[] dice;
    private static int x, y; // x : 주사위 수평 좌표, y : 주사위 수직 좌표
    
    // 다음으로는 지도의 크기를 정의한다
    // 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20)
    private static int[][] map;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int testCase = sc.nextInt();
        for (int i =0; i < testCase; i++) {
            dice = new int[6];
            // 알고리즘 작성
            int N, M; // N은 지도 세로 크기, M은 지도 가로 크기
            
            int K;  // 방향 이동 명령어
            
            // 첫째줄 명령어
            N = sc.nextInt();
            M = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            K = sc.nextInt();
            
            // 첫째줄 명령어를 입력 값 검사
            // 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 
            // 주사위를 놓은 곳의 좌표 x y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 
            // 명령의 개수 K (1 ≤ K ≤ 1,000)
            if (N > 20 || M > 20 || 0 > x || x >= N ||
                    0 > y || y >= M || 1 > K || K >= 1000) {
                System.out.println("조건에 일치 하지 않는 숫자를 입력하였습니다.");
                System.out.println("******* 조건 *******");
                System.out.println("(1 ≤ N, M ≤ 20)");
                System.out.println("(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1)");
                System.out.println("(1 ≤ K ≤ 1,000)");
                break;
            }
            
            // 지도 저장
            map = new int[N][M];
            
            // 둘째줄 명령어
            for (int indexY = 0; indexY < N; indexY++) {
                for (int indexX = 0; indexX < M; indexX++) {
                    // 지도 배치
                    map[indexY][indexX] = sc.nextInt();
                    
                    // 10 이하 자연수가 일치 않을때!
                    if (0 > map[indexY][indexX] || map[indexY][indexX] > 10) {
                        System.out.println("10 이하의 자연수로 입력하세요");
                        return;
                    }
                }
            }
            
            // 결과 값 저장용
            int[] resultValue = new int[K];
            // 다음은 알고리즘 수행 후 결과 값 출력
            int index = 0;
            while (index < K) {
                // 명령어 저장 (동, 서, 남, 북)
                int direction = sc.nextInt();
                
                // 결과값 도출
                resultValue[index] = getDiceUp(direction, N, M, index+1);
                
                
                System.out.print((index + 1)  + "번째 주사위 : ");
                for (int dices : dice) {
                    System.out.print(dices + "  ");
                }
                System.out.println();
                
                System.out.println(resultValue[index]);
                index++;
                
            }
            
            System.out.println();
            
            for (int result : resultValue) {
                if (result >= 0) {
                    System.out.println(result);
                }
            }
            
            dice = new int[6];
        }
    }
    
    // 주사위 알고리즘 수행
    public static int getDiceUp(int direction, int N, int M, int index) {
        int temp;

        // 밑면 설정
        if (map[y][x] == 0) {
            map[y][x] = dice[5];
        }
        
        if (dice[5] == 0 || map[y][x] != 0) {
            dice[5] = map[y][x];
        }
        
        
        System.out.print(index + "번째 초기 주사위 값 : ");
        for (int cho : dice) {
            System.out.print(cho + " ");
        }
        System.out.println();
        switch(direction) {
        // 동쪽일때 
        case 1:
            if (x + 1 != M) {
                temp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                
                x++; // 주사위 좌표 증가
                return dice[2];
            }
            break;
            
        // 서쪽일때
        case 2:
            if (x - 1 >= 0) {
                temp = dice[2];
                dice[2] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                


                x--;
                return dice[2];
            }
            break;
            
        // 북쪽일때
        case 3:
            if (y - 1 >= 0) {
                temp = dice[2];
                dice[2] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[0];
                dice[0] = temp;
                
                y--;
                return dice[2];
            }
            break;
            
        // 남쪽일때
        case 4:
            if (y + 1 != N) {
                temp = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;

                y++;
                return dice[2];
            }
            break;
            
        default:
            break;
        }
        return -1;
    }
}
