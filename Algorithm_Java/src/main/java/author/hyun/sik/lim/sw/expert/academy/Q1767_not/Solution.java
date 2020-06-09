package author.hyun.sik.lim.sw.expert.academy.Q1767_not;

import java.util.LinkedList;
import java.util.Scanner;

// 1767. [SW Test 샘플문제] 프로세서 연결하기
// 삼성 개발 : 최신 모바일 프로세서 멕시노스 : 가로 N개 * 세로 N개의 cell로 구성
// 1개 cell에는 1개의 Core 혹은 1개의 전선이 올 수 있음
// 멕시노스의 가장 자리에는 전원이 흐름
// Core와 전원을 연결하는 전선은 직선으로만 설치 가능
// 전선은 절대로 교차하면 안됨
// 멕시노스의 가장 자리에 위치한 Core는 이미 전원이 연결된 것으로 간주
// 전제조건
// 7 <= N <= 12
// Core의 개수 : 1 ~ 12개
// 최대한 많은 Core에 전원을 연결해도 전원이 연결되지 않은 Core가 존재
// 0 : 빈 셀
// 1 : core

// 단 모든 코어를 연결을 하되,
// 연결하지 못하는 경우를 고려를 하라!

// 입력 예시
/* 
1
8
0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 1 0 0 0 0 0 0
0 0 1 1 1 1 1 1
0 0 0 0 0 0 0 0
0 1 1 1 1 1 1 1
*/

// dfs + bfs
// 4초 안에 해결할 것
// 참조 : https://leveloper.tistory.com/81
public class Solution {
    static int map[][];
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static LinkedList<Point> core;
    static int min;
    static int N;
    static int coreCount;
    
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            core = new LinkedList<>(); // 코어 정보
            min = Integer.MAX_VALUE; // 전선 길이(최소값)
            coreCount = 0; // 코어 수
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    map[y][x] = sc.nextInt();
                    
                    if (map[y][x] == 1 && x > 0 && y > 0 && x < N-1 && y < N-1) {
                        core.add(new Point(x,y));
                    }
                }
            }
            
            
            // 정답
            // 여기서 dfs로 했을 경우 코어 하나라도 연결이 되지 않았을 경우도 고려하라
            dfs(0,0,0);
            
            System.out.println("#"+test_case+" "+min);
        }
        sc.close();
    }
    
    // 길이 구하기
    private static void dfs(int count, int c, int line) {
        // TODO Auto-generated method stub
        if (core.size() == count) {
            if (coreCount < c) {
                coreCount = c;
                min = line;
            } else if (coreCount == c) {
                min = Math.min(line, min);
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            if (!isConnect(core.get(count), i)) {
                int sum = fill(core.get(count), i, 2);
                dfs(count+1, c + 1, line + sum);
                sum = fill(core.get(count), i, 0);
            }
        }
        dfs(count + 1, c, line); // 코어 선택 안함
    }

    private static int fill(Point point, int i, int value) {
        // TODO Auto-generated method stub
        int sum = 0;
        int x = point.x + dx[i];
        int y = point.y + dy[i];
        while(!outWall(x,y)) {
            map[y][x] = value;
            sum++;
            x += dx[i];
            y += dy[i];
        }
        return sum;
    }

    private static boolean isConnect(Point point, int i) {
        // TODO Auto-generated method stub
        int x = point.x + dx[i];
        int y = point.y + dy[i];
        
        while (!outWall(x,y)) {
            if (map[y][x] != 0) return false;
            x += dx[i];
            y += dy[i];
        }
        return true;
    }

    private static boolean outWall(int nx, int ny) {
        // TODO Auto-generated method stub
        return (nx < 0 || ny < 0 || nx >= N || ny >= N)? true : false;
    }
    
}
