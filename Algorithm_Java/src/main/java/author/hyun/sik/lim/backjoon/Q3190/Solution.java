package author.hyun.sik.lim.backjoon.Q3190;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 뱀 관련 문제
// Dummy 도스 게임가 있다.
// 뱀이 기어다니는 환경을 상상한다.
// 사과 섭취 : 뱀길이 증가
// 벽 or 자기자신의 몸가 부딪치면 : 게임 종료
// N*N 정사각 보드위에서 진행,
// 사과가 놓여진 칸이 주어짐
// 보드의 상하좌우 끝에 벽이 주어짐
// 게임 시작 지점 : (1,1) / 뱀길이 : 1
// 뱀은 처음에 오른쪽으로 향함

// 몸길이를 늘려 머리를 다음칸에 위치
// 이동 칸에 사과가 있으면 그 칸에 사과가 없어지고 꼬리는 움직이지 않음.
// 이동 칸에 사과가 없으면, 몸길이를 줄여 꼬리가 위치한 칸을 비워준다.
// 게임이 몇초에 끝나는지 계산!

// 입력 조건
// 첫째줄 : 보드의 크기 N (2 ~ 100)
// 다음줄 : 사과 갯수 : K (0 ~ 100)
// 다음줄 : K개의 줄이 주어지고, 사과의 위치 (x,y) 좌표 (사과 위치는 처음 좌표에 없음)
// 다음 줄 : 뱀위 방향 변환 횟수 L (1 ~ 100)
// 다음 줄 : L개의 줄이 주어지고, 뱀의 방향 변환 정보가 주어지고,
//       정수 x, 문자 c로 이뤄짐, 게임 제한 시간 x초
//       x초만큼 끝난 이후 : 왼쪽 C가 'L', 오른쪽 C가 'D'로 90도 방향으로 회전
//       x 범위 : 1 ~ 10000, 방향 전환 정보는 X가 증가하는 순!

// 참조 : https://heekim0719.tistory.com/367?category=816705
public class Solution {
    // 뱀 움직임 좌표
    static class Location {
        int x;
        int y;
        public Location() {}
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // x초 후 방향 이동 지시
    static class Info {
        int x;
        char l;
        
        public Info() {}
        public Info(int x, char l) {
            this.x = x;
            this.l = l;
        }
    }
    static int N;
    static int K;
    
    static int[][] map;
    static Queue<Info> timeTable;
    
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        /*
         * 문제 솔루선
         * 조건 1 : default 값은 오른쪽으로 시작
         * 조건 2 : 몸길이 늘려 머리를 다음칸에 위치
         * 조건 3 : 이동 칸에 몸길이를 줄여 꼬리 위치칸 비우기
         * */
        
        // 첫번째 값 입력
        N = sc.nextInt();
        K = sc.nextInt();
         
        // 사과 위치 입력 : 사과가 존재시 map에서 1로 두기
        map = new int[N+1][N+1];
        for (int i = 0; i < K; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            map[y][x] = 1;
        }
        
        // 시간이 될 때 뱀의 방향 변환 횟수 입력
        int L = sc.nextInt();
        timeTable = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            
            // x초 후 c로 방향 변환 : 지정
            timeTable.add(new Info(x+1,c));
        }
        
        boolean boom = false;
        
        // 시간
        int time = 0;
        // 방향 순번 : 시계방향으로 시작 <동, 서, 남, 북>
        int direction = 0;
        int snakeSize = 1;
        
        // 벽 충돌 및 머리가 몸통에 부딪치면 종료
        // 뱀의 시작점과 끝점을 담는다.
        
        int x = 1;
        int y = 1;
        int endX = 1;
        int endY = 1;
        map[y][x] = -1;
        
        // 뱀위 차지하는 위치 담는 공간 생성
        Queue<Location> snakeLocation = new LinkedList<>();
        snakeLocation.add(new Location(0,0));
        
        // 부딪칠때까지 진행한다.
        while (!boom) {
            // 먼저 초증가
            time++;
            
            int Ldir = -1;
            // 방향 변환 여부
            if(!timeTable.isEmpty()) {
                Ldir = timeTable.element().x;
            } else 
                Ldir = -1;
            
            // 특정시 방향 변환
            if (Ldir == time) {
                char dirChar = timeTable.element().l;
                
                if (dirChar == 'L' ) {
                    if (direction == 0) direction = 3;
                    else direction -= 1;
                } else {
                    if (direction == 3) direction = 0;
                    else direction += 1;
                }
                timeTable.remove();
            }
            
            // 뱀 머리 이동
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            // 범위 이탈시
            if (nx < 1 || ny < 1 || ny > N || nx > N) {
                x = nx;
                y = ny;
                boom = true;
                
                break;
            }
            
            // 본인 부딪힐 경우
            if (map[ny][nx] == -1) {
                x = nx;
                y = ny;
                boom = true;
                
                break;
            }
            
            // 사과가 있을 경우
            if (map[ny][nx] == 1) {
                snakeLocation.add(new Location(nx, ny));
                map[ny][nx] = -1;
            }
            // 시과가 없을 경우
            else {
                snakeLocation.add(new Location(nx, ny));
                map[ny][nx] = -1;
                map[endY][endX] = 0;
                snakeLocation.remove();
                endX = snakeLocation.element().x;
                endY = snakeLocation.element().y;
            }
            
            // 뱀머리 갱신
            x = nx;
            y = ny;
        }
        
        System.out.println(time);
    }

}
