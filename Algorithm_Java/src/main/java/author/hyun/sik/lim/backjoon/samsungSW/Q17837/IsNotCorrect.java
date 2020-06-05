package author.hyun.sik.lim.backjoon.samsungSW.Q17837;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 새로운 게임 2
// 체스판과 말을 이용해서 전체 말들이 모두 한곳으로 좌표에 올랐을 때 나오는 결과 
// 흰색, 빨간색, 파란색 칸이 주어진다.
// 입력 조건
// 첫째 줄, 체스판의 크기(N), 말의 개수 (K)
// 둘째 줄 : 체스판 크기 지정
// 0 : 흰색, 1 : 빨간색, 2 : 파란색
// 마지막 줄 : K개의 말의 대한 정보(1번말부터 순서대로 주어짐)
// 행번호, 열번호, 이동방향 (1~4가지)
// 이동방향 정보 : 1 : 오른쪽, 2 : 왼쪽, 3 : 윗쪽, 4 : 아랫쪽
public class IsNotCorrect {
    static final int TURN = 1000; // 턴 횟수 : 최대 1000번
    
    // 입력 첫째줄 용 변수
    static int N;
    static int K;
    
    // 입력 둘째줄
    static int[][] map;
    
    // 마지막 줄
    static class Chese {
        int x;
        int y;
        int direction;
        
        public Chese(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    
    static List<Chese> list;
    // 여기는 Queue로 순서대로 이동
    
    // 방향 변수
    static final int[] dx = {0, 1, -1, 0, 0};
    static final int[] dy = {0, 0, 0, -1, 1};
    
    static Queue<Integer>[][] area;
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        // 첫째줄
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        
        // 둘째줄
        map = new int[N+1][N+1];
        list = new LinkedList<>();
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 마지막 줄
        area = new LinkedList[N+1][N+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            list.add(new Chese(x, y, direction));
            area[y][x] = new LinkedList<>();
            area[y][x].offer(i);
        }
        
        // 알고리즘 시작!
        int count = 0;
        for (int i = 0; i < TURN; i++) {
            int nx = 0;
            int ny = 0;
            
            for (int z = 0; z < K; z++) {
                int x = list.get(z).x;
                int y = list.get(z).y;
                int direction = list.get(z).direction;
                
                nx = x + dx[direction];
                ny = y + dy[direction];
                
                if (nx < 1) {
                    direction = 2;
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                    
                    if (map[ny][nx] == 2) {
                        list.set(z, new Chese(x,y,direction));
                        continue;
                    }
                } else if (nx > N) {
                    direction = 1;
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                    
                    if (map[ny][nx] == 2) {
                        list.set(z, new Chese(x,y,direction));
                        continue;
                    }
                } else if (ny < 1) {
                    direction = 4;
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                    
                    if (map[ny][nx] == 2) {
                        list.set(z, new Chese(x,y,direction));
                        continue;
                    }
                } else if (ny > N) {
                    direction = 3;
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                    
                    if (map[ny][nx] == 2) {
                        list.set(z, new Chese(x,y,direction));
                        continue;
                    }
                } else {
                    
                }
                
                switch(map[ny][nx]) {
                case 0:
                    list.set(z, new Chese(nx,ny,direction));
                    while(!area[y][x].isEmpty()) {
                        int k = area[y][x].remove();
                        area[ny][nx].offer(k);
                        direction = list.get(k).direction;
                        list.set(k, new Chese(nx,ny,direction));
                        if (k == K) {
                            break;
                        }
                    }
                    break;
                case 1:
                    list.set(z, new Chese(nx,ny,direction));
                    while(!area[y][x].isEmpty()) {
                        int k = area[y][x].remove();
                        area[ny][nx].offer(k);
                        direction = list.get(k).direction;
                        list.set(k, new Chese(nx,ny,direction));
                        if (k == K) {
                            break;
                        }
                    }
                    Stack<Integer> stack = new Stack<>();
                    while(!stack.isEmpty()) {
                        stack.push(area[ny][nx].remove());
                    }
                    while(!area[ny][nx].isEmpty())
                        area[ny][nx].offer(stack.pop());
                    break;
                case 2:
                    if (direction == 1) {
                        direction = 2;
                    } else if (direction == 2) {
                        direction = 1;
                    } else if (direction == 3) {
                        direction = 4;
                    } else if (direction == 4) {
                        direction = 3;
                    } else {
                        
                    }
                    
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                    
                    list.set(z, new Chese(nx,ny,direction));
                    while(!area[y][x].isEmpty()) {
                        int k = area[y][x].remove();
                        area[ny][nx].offer(k);
                        direction = list.get(k).direction;
                        list.set(k, new Chese(nx,ny,direction));
                        if (k == K) {
                            break;
                        }
                    }
                    break;
                }
                
            }
            
            count++;
            // 여기는 말이  한곳에 모두 모여 있을 때 턴종료
            
            if (area[ny][nx].size() == K) {
                break;
            }
            
        }
        
        if (count == TURN)
            count = -1;
        
        System.out.println(count);
    }
    
}
