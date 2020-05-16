package author.hyun.sik.lim.backjoon.Q17142;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

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
    
    static Queue<VirusLocation> queue;
    static Stack<VirusLocation> virus, pick;
    static int minSpend;    // 결과 나타낼 변수
    
    // 바이러스 확산시간 및 방문여부
    static int[][] second;
    
    // 방향 순회용
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        // 초기화
        N = sc.nextInt();
        M = sc.nextInt();
        
        map = new int[N][N];
        virus = new Stack<>();
        queue = new LinkedList<>();
        pick = new Stack<>();
        minSpend = 999999999;
        
        second = new int[N][N];
        
        // 입력
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
                
                if(map[y][x] == 2) {
                    VirusLocation vl = new VirusLocation();
                    vl.x = x;
                    vl.y = y;
                    virus.push(vl);
                }
            }
        }
        
        // 로직 시작!
        backTracking(0);
        
        // 여기서 벽이 존재시
        if (minSpend == 999999999)
            minSpend = -1;
        
        // 결과 출력
        System.out.println(minSpend);
        // 알고리즘 종료
        
    }
    
    
    
    public static void backTracking(int index) {
        // 만일 백트레킹 기법으로 모든 바이러스의 대해 검사 다 했을 경우!
        if (index == virus.size()) {
            // 여기서 전체 바이러스 중 일부 활성화 된 바이러스만 수행
            if (pick.size() == M) {
                
                // 선택된 바이러스들을 큐에 먼저 넣고 bfs 알고리즘 수행을 위해 실시
                //queue.addAll(pick);
                
                for (int i = 0; i < M; i++)
                    queue.offer(pick.get(i));
                
                // 전체를 -1로 초기화!
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        second[y][x] = -1;
                    }
                }
                
                // m개의 바이러스에서 초기값 0으로 지정
                for (int i = 0; i < pick.size(); i++) {
                    second[pick.get(i).y][pick.get(i).x] = 0;
                }
                
                // 여기서 일부 활성화 바이러스를 골라 활성화 시작!
                bfs();
            }
            // 그렇지 않은 경우 수행 금지
        } else {
            // 먼저 스택으로 골라 바이러스에 집어 넣는다.
            pick.push(virus.get(index));
            backTracking(index + 1);
            pick.pop();
            
            // 선택 하지 않고 바이러스 번호 순회
            backTracking(index + 1);
        }
    }
    
 // 여기서 바이러스 퍼트리는 방법의 대해 알고리즘 작성
    public static void bfs() {
        // bfs 공식을 먼저
        while(!queue.isEmpty()) {
            VirusLocation vl = queue.poll();
            int x = vl.x;
            int y = vl.y;
            
            // 방향 검사
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 다음은 연구소 범위 안에 있을 경우만 실시
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 다음은 벽이 아닐 경우 혹은 비활성화 된 바이러스는 활성화 바이러스를 만나면 활성화
                    if (map[ny][nx] != 1 && second[ny][nx] <= 0) {
                        second[ny][nx] = second[y][x] + 1;
                        vl = new VirusLocation();
                        vl.x = nx;
                        vl.y = ny;
                        queue.offer(vl);
                    }
                }
            }
        }
        
        
        // 다음은 빈칸이 있는지 검사 혹은 최소 시간 검사
        boolean isClear = true;
        int maxTime = 0;
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                // 빈칸인 경우!
                if (map[y][x] == 0) {
                    // 벽이 아니고 빈칸이 존재시 작업 종료
                    
                    if (second[y][x] < 0) {
                        isClear = false;
                        break;
                    }
                    
                    maxTime = Math.max(maxTime, second[y][x]);
                }
            }
        }
        
        // 최소시간 환산
        if (isClear) minSpend = Math.min(minSpend, maxTime);
    }
}
