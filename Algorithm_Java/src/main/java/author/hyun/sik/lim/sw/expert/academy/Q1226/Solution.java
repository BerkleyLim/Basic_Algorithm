package author.hyun.sik.lim.sw.expert.academy.Q1226;

import java.util.Scanner;

// 미로 찾기 문제!
// BFS 문제로 해결!
// map에서 수치 정보
// 0 : 길 / 1 : 벽 / 2 : 출발점 / 3 : 도착점
// 미로 찾기 가능 여부
// 1 : 가능 , 0 : 불가능

public class Solution {
    static class NodeXY {
        int level;
        int x;
        int y;
        NodeXY link;
    }
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    static int[][] map;
    static final int SIZE = 16;
    static boolean[][] visited;
    static NodeXY start;
    static NodeXY end;
    static NodeXY front;
    static NodeXY rear;
    
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int testNumber = 0;
        while(testNumber < 10) {
            // 초기화
            map = new int[SIZE][SIZE];
            visited = new boolean[SIZE][SIZE];
            
            testNumber =sc.nextInt();
            
            // 출발점, 도착점 설정
            start = new NodeXY();
            end = new NodeXY();
            
            // setting
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    map[y][x] = sc.nextInt();
                    
                    if (map[y][x] == 2) {
                        start.x = x;
                        start.y = y;
                        // 시작점 지정
                        enQueue(0,x,y);
                    } else if (map[y][x] == 3) {
                        end.x = x;
                        end.y = y;
                    } else if (map[y][x] == 1) {
                        visited[y][x] = true;
                    } else {
                        visited[y][x] = false;
                    }
                }
            }
            
            int level = 1;
            // 도착점 찾을때까지 찾기!
            while(isEmpty()) {
                
                for(int i = 0; i < 4; i++) {
                    
                }
                
                level++;
            }
        }
        
    }
    
    public static void bfs() {
        enQueue(1,1,1);
        
    }
    
    public static void enQueue(int level, int x, int y) {
        // 큐 생성 시 방문 완료
        visited[y][x] = true;
        
        NodeXY node = new NodeXY();
        
        node.level = level;
        node.x = x;
        node.y = y;
        node.link = null;
        
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.link = node;
            rear = node;
        }
    }
    
    public static void deQueue() {
        
    }
    
    public static boolean isEmpty() {
        return (front == null && rear == null);
    }
    
    public static int qPeek() {
        if (isEmpty()) return -1;
        else {
            
            return front.level;
        }
    }
    
}
