package author.hyun.sik.lim.backjoon.Q17142;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 연구소 3문제!
// 바이러스 연구 관련 문제
// 바이러스 유출 여부 : 활성화 및 비활성화
// 처음 : 모든 바이러스 비활성화
// 활성상태 : 바이러스 상하좌우 인접한 모든 빈칸으로 동시 복제되고 1초 걸림
// 바이러스 M개를 활성 상태로 변경할려고 함
// 연구소 크기 : N*N
// 1*1 크기의 정사각형으로 나눠진다.
// 연구소 구조 : 빈칸, 벽, 바이러스
// 벽은 칸 하나를 가득 차지!
// 0 : 빈칸, 1 : 벽, 2 : 바이러스
// 연구소의 상태를 주어지고 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간을 구하기!

// 입력 조건
// 첫째줄 : 연구소의 크기(N), 놓을 수 있는 바이러스 개수 M
// 둘째줄 : N개의 줄에 연구소의 상태가 주어짐

// 출력 조건
// 연구소의 모든 빈 칸에 대한 바이러스가 있게 되는 최소 시간!
// 단, 모든 빈칸에 바이러스를 퍼뜨릴 수 없을 경우 -1 출력
public class Main {
    static class VirusLocation {
        int x;
        int y;
    }
    static int N;
    static int M;
    static int[][] map;
    static int[][][] virusMap;
    
    static Queue<VirusLocation> virus;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        N = sc.nextInt();
        M = sc.nextInt();
        
        map = new int[N][N];
        
        int virusCount = 0;
        virus = new LinkedList<>();
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
                
                if(map[y][x] == 2) {
                    virusCount++;
                    VirusLocation vl = new VirusLocation();
                    vl.x = x;
                    vl.y = y;
                    virus.add(vl);
                }
            }
        }
        virusMap = new int[virusCount][N][N];
        
        logic(virusCount);
        
        // 알고리즘 종료
        
    }
    
    public static void logic(int virusCount) {
        // 각각 바이러스 좌표를 만들어보자
        for (int z = 0; z < virusCount; z++) {
            int tmpY = virus.get(0).y;
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    
                }
            }
        }
        
    }
}
