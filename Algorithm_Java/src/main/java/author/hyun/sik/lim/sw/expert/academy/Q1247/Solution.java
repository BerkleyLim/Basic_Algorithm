package author.hyun.sik.lim.sw.expert.academy.Q1247;

import java.util.Scanner;

// 김대리가 회사 출발 후 N명의 고객을 방문 후 자신의 집으로 간다.
// 회사, 집 위치 와 각 고객의 위치를 이차원 정수 좌표 (x,y)로 주어진다.
// 0 <= x <= 100, 0 <= y <= 100
// (x1, y1) 와 (x2, y2) 사이 거리 : |x1 - x2| + |y1 - y2|
// 고객 수 2 <= N <= 10
// 가장 짧은 경로의 이동거리는?

// 테케별 입력 조건
// 고객수
// 회사의 좌표, 집의 좌표, N명의 고객의 좌표 : 각각 x, y로 차례대로 입력

public class Solution {
    static int clientNumber;
    
    
    static int[] NodeX;
    static int[] NodeY;
    
    static int[] distance;
    static boolean[][] visited;
    
    static int minDistance;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            minDistance = 9999999;
            visited = new boolean [clientNumber + 2][clientNumber + 2];
            // 고객의 수 입력
            clientNumber = sc.nextInt();
            
            distance = new int[clientNumber + 2];
            NodeX = new int[clientNumber + 2];
            NodeY = new int[clientNumber + 2];
            
            
            // 시작점, 도착점 좌표 입력
            NodeX[0] = sc.nextInt();
            NodeY[0] = sc.nextInt();
            NodeX[clientNumber + 1] = sc.nextInt();
            NodeY[clientNumber + 1] = sc.nextInt();
            
            
            for (int i = 1; i <= clientNumber; i++) {
                NodeX[i] = sc.nextInt();
                NodeY[i] = sc.nextInt();
            }
            
            int[] arr = new int[clientNumber + 1];
            for(int i = 1; i <= clientNumber; i++) {
                arr[i] = i;
            }
            
            // 인덱스 순열 만들기
            backTracking(arr, 1);
            
            System.out.println("#" + test_case + " " + minDistance);
            
        }
    }
    
    // 솔루션 프로젝트 용 함수 (백트래킹) - 순열 만들기 응용
    public static void backTracking(int[] arr, int size) {
        if (size == clientNumber) {
            total(arr, size); // 순열 응용으로 한 세트 마다 total로
            return;
        }
        for (int x = size; x <= clientNumber; x++) {
            swap(arr, x, size);
            backTracking(arr, size + 1);
            swap(arr, x, size);
        }
    }
    
    private static void swap(int[] arr, int i, int size) {
        int temp = arr[i];
        arr[i] = arr[size];
        arr[size] = temp;
    }
    
    public static void total(int[] arr, int size) {
        int sum = 0;
        int tempX = NodeX[0];
        int tempY = NodeY[0];
        
        // 순열 한 세트가 올때마다 합을 구해줌
        for (int i = 1; i <= clientNumber; i++) {
            sum += Math.abs(tempX - NodeX[arr[i]]) 
                    + Math.abs(tempY - NodeY[arr[i]]);
            // 출발 위치 갱신
            tempX = NodeX[arr[i]];
            tempY = NodeY[arr[i]];
        }
        
        // 모든 회원 집 마친 후  도착점 계산
        sum += Math.abs(tempX - NodeX[clientNumber + 1])
                + Math.abs(tempY - NodeY[clientNumber + 1]);
        
        // 최소 거리 값 계속
        if (minDistance > sum)
            minDistance = sum;
    }
}
