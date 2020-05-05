package author.hyun.sik.lim.backjoon.Q17143;

import java.util.Scanner;

// 낚시왕
// 문제 해결 과정
// 1. 낚시 꾼이 왼쪽부터 오른쪽으로 향해 간다!
// 2. 상어 정보는 (x,y) 좌표가 주어지고 상어 크기와 속도, 이동방향이 주어진다.
// 3. 1초당 상어는 속력과 좌표의 따라 이동한다.
// 4. 상어는 자기보다 작은 상어가 존재시 작은 상어를 잡아 먹는다. (같은 칸에 존재시)
// 5. 해당 칸에 상어가 존재시 상어 낚시를 한다(최대 1마리)
// 6. 상어 방향
//    1 : 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽
public class Solution {
    static class Shake implements Comparable<Shake> {
        int y; // 세로
        int x; // 가로
        int s; // 속력
        int d; // 초기 이동방향
        int z; // 크기
        
        @Override
        public int compareTo(Shake o) {
            if (o.x != this.x) {
                return this.x - o.x;
            } else if (o.x == this.x && o.y != this.y) {
                return this.y - o.y;
            }
            return o.z - this.z;
        }
    }
    
    static int[][] map;
    
    // 방향 결정
    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, -1, 1, 0, 0};
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 세로 : R, 가로 : C, 상어수 : M
        int R = sc.nextInt();
        int C = sc.nextInt();
        int M = sc.nextInt();
        
        Shake[] shake = new Shake[M + 1];
        map = new int[R + 1][C + 1];
        
        for (int i = 1; i <= M; i++) {
            shake[i] = new Shake();
            shake[i].y = sc.nextInt();
            shake[i].x = sc.nextInt();
            shake[i].s = sc.nextInt();
            shake[i].d = sc.nextInt();
            shake[i].z = sc.nextInt();
            
            map[shake[i].y][shake[i].x] = i;
        }
        
//        System.out.println("0 : second");
//        for (int y = 1; y <= R; y++) {
//            for (int x = 1; x <= C; x++) {
//                System.out.print(map[y][x]);
//            }
//            System.out.println();
//        }
        
        // 작업 시작!
        int result = 0;
        for (int second = 1; second <= C; second++) {
            // 작업 순서
            // 1. 먼저 낚시를 한다.
            // 2. 상어 이동한다.
            // 3. 상어 이동 중 배열 범위 바깥으로 나갈 경우 방향을 바꾸어 수행
            // 4. 상어 이동 후 같은 배열에 있는 경우 상어 크기를 비교해 상어 크기가 작은 경우 상어 정보 제거
            
            // 1. 상어 낚시 시작
            for (int y = 1; y <= R; y++) {
                if (map[y][second] > 0) {
                    result += shake[map[y][second]].z;
                    shake[map[y][second]] = null;
                    map[y][second] = 0;
                    break;
                }
            }
            
            int[][] arr = new int[R + 1][C + 1];
            // 2. 상어 이동 실시
            for (int i = 1; i <= M; i++) {
                // 상어가 낚시 당했거나 상어가 잡아 먹힌 경우 패스
                if (shake[i] == null) {
                    continue;
                }
                
                int tempY = shake[i].y;
                int tempX = shake[i].x;
                
                // 3. 먼저 상어 이동 좌표만 구하되 배열 범위 벗어나면 방향 바꾸고 작업 실시
                for (int j = 1; j <= shake[i].s; j++) {
                    
                    if (tempY == 1 && shake[i].d == 1) {
                        shake[i].d = 2;
                    } else if (tempY == R && shake[i].d == 2) {
                        shake[i].d = 1;
                    } else if (tempX == 1 && shake[i].d == 4) {
                        shake[i].d = 3;
                    } else if (tempX == C && shake[i].d == 3) {
                        shake[i].d = 4;
                    }
                    tempY += dy[shake[i].d];
                    tempX += dx[shake[i].d];
                }
                
                // 4. 상어 위치를 바꿔주고 이동할려는 상어가 존재시 상어 크기를 비교하여
                //   상어 크기가 작은 상어는 제거한다.
                // arr 배열 : 상어를 새로 배치하기 위해 임시 위치 지정!
                
                if (arr[tempY][tempX] > 0) {
                    if (shake[arr[tempY][tempX]].z > shake[i].z) {
                        shake[i] = null;
                    } else {
                        shake[arr[tempY][tempX]] = null;
                        arr[tempY][tempX] = i;
                        shake[i].y = tempY;
                        shake[i].x = tempX;
                    }
                } else {
                    shake[i].y = tempY;
                    shake[i].x = tempX;
                    arr[tempY][tempX] = i;
                }
                
                map = arr;
            }
            
            // 테스트
//            System.out.println(second +" : second");
//            for (int y = 1; y <= R; y++) {
//                for (int x = 1; x <= C; x++) {
//                    System.out.print(map[y][x]);
//                }
//                System.out.println();
//            }
        }
        // 작업 종료
        
        System.out.println(result);
    }

}
