package author.hyun.sik.lim.sw.expert.academy.Q1767;

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


public class Solution {
    static int map[][];
    static class Point {
        int x;
        int y;
        boolean left;
        boolean right;
        boolean up;
        boolean down;
        int leftValue;
        int rightValue;
        int upValue;
        int downValue;
    }
    static LinkedList<Point> point;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            map = new int[N][N];
            point = new LinkedList<>();
            
            // 입력
            for(int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    map[y][x] = sc.nextInt();
                    if (map[y][x] == 1
                            && y > 0 && y < N-1 && x > 0 && x < N-1) {
                        Point temp = new Point();
                        temp.x = x;
                        temp.y = y;
                        temp.left = true;
                        temp.right = true;
                        temp.up = true;
                        temp.down = true;
                        temp.leftValue = 0;
                        temp.rightValue = 0;
                        temp.upValue = 0;
                        temp.downValue = 0;
                        point.add(temp);
                    }
                }
            }
            
            dfs(N);
            
            
            
            // 정답
            System.out.println("#"+test_case+" ");
        }
    }
    
    public static void dfs(int N) {
        int[][] tmap = search(N);
    }
    
    public static int[][] search(int N) {
        int[][] tmap = map;
        for (int i = 0; i < point.size(); i++) {
            int nodeX = point.get(i).x;
            int nodeY = point.get(i).y;
            
            Point temp = new Point();
            temp = point.get(i);
            // 왼쪽경로 검사 및 거리 제시
            for (int x = nodeX; x <= 0; x--) {
                if(map[nodeY][x] == 1) {
                    temp.left = false;
                    temp.leftValue = -9999;
                    break;
                }
                temp.leftValue++;
            }
            
            
            // 오른쪽경로 검사 및 거리 제시
            for (int x = nodeX; x > N; x++) {
                if(map[nodeY][x] == 1) {
                    temp.right = false;
                    temp.rightValue = -9999;
                    break;
                }
                temp.rightValue++;
            }
            
            // 윗쪽경로 검사 및 거리 제시
            for (int y = nodeY; y <= 0; y--) {
                if(map[y][nodeX] == 1) {
                    temp.up = false;
                    temp.upValue = -9999;
                    break;
                }
                temp.upValue++;
            }
            
            // 아래쪽경로 검사 및 거리 제시
            for (int y = nodeY; y > N; y++) {
                if(map[y][nodeX] == 1) {
                    temp.down = false;
                    temp.downValue = -9999;
                    break;
                }
                temp.downValue++;
            }
            
            // 최종 갱신
            point.set(i,temp);
        }
        
        return tmap;
    }
}
