package author.hyun.sik.lim.backjoon.Q17144;

import java.util.Scanner;

// 미세먼지풀이
// 각칸이 주어짐
// 공기청정기는 항상 1번열에 설치 됨
// 첫째 줄 R, C, T가 주어짐
// 둘째 줄 각 칸마다 공기청정기 위치 및 미세먼지 농도 구하기
// -1 : 공기청정기 위치
// 1이상 : 미세먼지 농도
public class Solution {
    static int[][] map;
    static int R;   // 세로
    static int C;   // 가로
    static int T;   // 시간
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        
        map = new int[R][C];
        
        int[] clearRoom = new int[2];
        int clear = 0;
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                map[y][x] = sc.nextInt();
                
            }
            if (map[y][0] == -1) {
                clearRoom[clear++] = y;
            }
        }
        
        // 문제풀이 순서
        // 1번 : 미세먼지 확산 시킨다
        // 2번 : 공기청정기로 먼지 먹는다.
        for (int i = 0; i < T; i++) {
            // 1번 과정
            map = spread();
            
            
            // 2번과정
            map = run(clearRoom);
        }
        
        // 알고리즘 종료
        int sum = 0;
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if(map[y][x] == -1)
                    continue;
                
                sum += map[y][x];
            }
        }
        
        System.out.println(sum);
    }
    
    // 2번 과정 : 먼지 먹는 작업
    public static int[][] run(int[] clearRoom) {
        int[][] arr = new int[R][C];
        
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                arr[y][x] = map[y][x];
            }
        }
        
        // 윗 작업 시작
        for (int x = 1; x < C - 1; x++) {
            arr[clearRoom[0]][x + 1] = map[clearRoom[0]][x];
        }
        
        for (int y = clearRoom[0]; y > 0; y--) {
            arr[y-1][C-1] = map[y][C-1];
        }
        
        for (int x = C - 1; x > 0; x--) {
            arr[0][x-1] = map[0][x];
        }
        
        for (int y = 0; y < clearRoom[0] - 1; y++) {
            arr[y+1][0] = map[y][0];
        }
        arr[clearRoom[0]][1] = 0;
        
        
        
        // 아랫작업 시작
        for (int x = 1; x < C - 1; x++) {
            arr[clearRoom[1]][x + 1] = map[clearRoom[1]][x];
        }
        
        for (int y = clearRoom[1]; y < R - 1; y++) {
            arr[y+1][C-1] = map[y][C-1];
        }
        
        for (int x = C - 1; x > 0; x--) {
            arr[R-1][x-1] = map[R-1][x];
        }
        
        for (int y = R - 1; y > clearRoom[1] + 1; y--) {
            arr[y-1][0] = map[y][0];
        }
        arr[clearRoom[1]][1] = 0;
        
        return arr;
    }
    
    // 1번 과정 : 먼지 확산 작업
    public static int[][] spread() {
        int[][] arr = new int[R][C];
        
        // version 2 : 시스템 에러 방지 (배열 범위 바깥일 경우), 공기청정기 위치 고려
        // 2-1) y가 0일때
        for (int x = 0; x < C; x++) {
            arr[1][x] += map[0][x] / 5;
            if (x == 0) {
                arr[0][x+1] += map[0][x] / 5;
                arr[0][x] += map[0][x] - (map[0][x] / 5) * 2;
            } else if (x == C -1) {
                arr[0][x-1] += map[0][x] / 5;
                arr[0][x] += map[0][x] - (map[0][x] / 5) * 2;
            } else {
                arr[0][x+1] += map[0][x] / 5;
                arr[0][x-1] += map[0][x] / 5;
                arr[0][x] += map[0][x] - (map[0][x] / 5) * 3;
            }
        }
        
        // 2-2) y가 R-1일때
        for (int x = 0; x < C; x++) {
            arr[R-2][x] += map[R-1][x] / 5;
            if (x == 0) {
                arr[R-1][x+1] += map[R-1][x] / 5;
                arr[R-1][x] += map[R-1][x] - (map[R-1][x] / 5) * 2;
            } else if (x == C -1) {
                arr[R-1][x-1] += map[R-1][x] / 5;
                arr[R-1][x] += map[R-1][x] - (map[R-1][x] / 5) * 2;
            } else {
                arr[R-1][x+1] += map[R-1][x] / 5;
                arr[R-1][x-1] += map[R-1][x] / 5;
                arr[R-1][x] += map[R-1][x] - (map[R-1][x] / 5) * 3;
            }
        }
        
        // 2-3) x = 0 일때
        for (int y = 1; y < R-1; y++) {
            if (map[y][0] == -1) {
                arr[y][0] = -1;
                continue;
            }
            
            arr[y][1] += map[y][0] / 5;
            if (map[y-1][0] == -1) {
                arr[y+1][0] += map[y][0] / 5;
                arr[y][0] += map[y][0] - (map[y][0] / 5) * 2;
            } else if (map[y+1][0] == -1) {
                arr[y-1][0] += map[y][0] / 5;
                arr[y][0] += map[y][0] - (map[y][0] / 5) * 2;
            } else {
                arr[y+1][0] += map[y][0] / 5;
                arr[y-1][0] += map[y][0] / 5;
                arr[y][0] += map[y][0] - (map[y][0] / 5) * 3;
            }
            
        }
        
        // 2-4) x = C - 1 일때
        for (int y = 1; y < R - 1; y++) {
            arr[y][C - 2] += map[y][C-1] / 5;
            arr[y+1][C-1] += map[y][C-1] / 5;
            arr[y-1][C-1] += map[y][C-1] / 5;
            arr[y][C-1] += map[y][C-1] - (map[y][C-1] / 5) * 3;
        }
            
        // version 3 : 간단히 풀기
        for (int y = 1; y < R - 1; y++) {
            for (int x = 1; x < C -1; x++) {
                // version 1 : 먼지 확산 기본 식
                arr[y-1][x] += map[y][x] / 5;
                arr[y+1][x] += map[y][x] / 5;
                arr[y][x+1] += map[y][x] / 5;
                if (map[y][x-1] == -1) {
                    arr[y][x] += map[y][x] - (map[y][x] / 5) * 3;
                } else {
                    arr[y][x-1] += map[y][x] / 5;
                    arr[y][x] += map[y][x] - (map[y][x] / 5) * 4;
                }
            }
        }
        return arr;
    }

}
