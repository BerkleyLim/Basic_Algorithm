package author.hyun.sik.lim.sw.expert.academy.Q1227;

import java.util.Scanner;

// 미로 찾기 문제!
// BFS 문제로 해결!
// map에서 수치 정보
// 0 : 길 / 1 : 벽 / 2 : 출발점 / 3 : 도착점
// 미로 찾기 가능 여부
// 1 : 가능 , 0 : 불가능
// 가로 * 세로 : 100 * 100

public class Solution {
    static class NodeXY {
        int x;
        int y;
        int level;
        NodeXY link;
    }
    
    static NodeXY front;
    static NodeXY rear;
    
    // BFS 사용시 필수!
    static final int[] dx = {0,0,1,-1};
    static final int[] dy = {-1,1,0,0};
    
    static boolean[][] visited;
    
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    
    static int SIZE = 100;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int testNumber = 0;
        while(testNumber < 10) {
            front = null;
            rear = null;
            visited = new boolean[SIZE][SIZE];
            
            testNumber = sc.nextInt();
            sc.nextLine();
            
            
            for (int y = 0; y < SIZE; y++) {
                String str = sc.nextLine();
                
                for (int x = 0; x < SIZE; x++) {
                    if (str.charAt(x) == '1')
                        visited[y][x] = true;
                    else if (str.charAt(x) == '2') {
                        startX = x;
                        startY = y;
                        enQueue(0, x, y);
                    } else if (str.charAt(x) == '3') {
                        endX = x;
                        endY = y;
                    } else {
                        visited[y][x] = false;
                    }
                }
            }
            
            
            System.out.println("#" + testNumber + " " + bfs());
        }
        
    }
    
    public static int bfs() {
        // 초기 값 설정
        int x = startX;
        int y = startY;
        int level = 1;
        
        while(!isEmpty()) {
            // 여기서 정점을 찾은 경우!
            if (x == endX && y == endY)
                return 1;
            
            // 인접 경로 탐색 (노드 검사) - 경로 있을 시 Queue 삽입
            for (int i = 0; i < 4; i++) {
                enQueue(level, x + dx[i], y + dy[i]);
            }
            
            // 탐색 완료 후 다음 경로 노드 검사
            NodeXY node = deQueue();
            
            if (node == null)
                return 0;
            
            
            x = node.x;
            y = node.y;
        }
        
        return 0;
    }
    
    
    public static void enQueue(int level, int x, int y) {
        // 방문 완료시 생성 x 혹은 벽일 시 생성 금지
        if (visited[y][x]) return;
        
        visited[y][x] = true;
        
        NodeXY node = new NodeXY();
        node.x = x;
        node.y = y;
        node.level = level;
        node.link = null;
        
        if(isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.link = node;
            rear = node;
        }
    }
    
    public static NodeXY deQueue() {
        if (isEmpty()) return null;
        else {
            front = front.link;
            return front;
        }
    }
    
    public static boolean isEmpty() {
        return (front == null && rear == null);
    }
    
}
