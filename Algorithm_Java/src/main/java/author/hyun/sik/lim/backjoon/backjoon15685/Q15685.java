package author.hyun.sik.lim.backjoon.backjoon15685;

import java.util.Scanner;

// https://www.acmicpc.net/problem/15685
// 드래곤 커브
public class Q15685 {
    // 움직임 방향! (생각을 좀 더 깊게 해볼껄!) (y 부분을 감소를 증가로, 증가를 감소로 바꿔보기)
    final static int[] dx = {1, 0, -1, 0};
    final static int[] dy = {0, -1, 0, 1};
    
    // map : 해당 좌표를 지나갈때
    static boolean[][] map;
    
    public static void main(String[] args) {
        map = new boolean[100][100];
        Scanner sc = new Scanner(System.in);
        
        // 첫번째 줄
        int N = sc.nextInt(); // 드래곤 커브 갯수
        
        
        // 두번째 줄 이후부터
        // (x, y) : 드래곤 커브 시작점, d : 시작방향, g : 드래곤 커브 세대
        int d = 0;
        int g = 0;
        // 여기는 x, y -> 시작점부터 이동하는 방향을 구한다.
        int x;
        int y;
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            d = sc.nextInt();
            g = sc.nextInt();
            
            // 여기는 커브 이동 관련 알고리즘 작성 (g가 1부터 적용)
            curve(x, y, d, g);
            
        }
        
        int answer = 0;
        // 여기서 사각형 구하기! (포문 사용을 N/2로 줄인다)
        for (int sy = 1; sy < 100; sy++) {
            for (int sx = sy; sx < 100; sx++) {
                // 여기서 삼각형으로 검사하는 것.
                if (sx == sy) {
                    if (map[sy][sx] && map[sy-1][sx] 
                            && map[sy][sx-1] && map[sy-1][sx-1]) {
                        answer += 1;
                    }
                } else {
                    if (map[sy][sx] && map[sy-1][sx] 
                            && map[sy][sx-1] && map[sy-1][sx-1]) {
                        answer += 1;
                    } 
                    if (map[sx][sy] && map[sx-1][sy] 
                            && map[sx][sy-1] && map[sx-1][sy-1]) {
                        answer += 1;
                    } 
                }
            }
        }
        System.out.println(answer);
    }
    
    // 드래곤 커브 이동 관련 알고리즘 (재귀 함수, 피보나치 수열!)
    public static void curve(int x, int y, int d, int g) {
        // 방향 설정
        int[] dir = new int[(int) Math.pow(2, g)];
        dir[0] = d;
        
        //ArrayList<Integer> direction = new ArrayList<>();
        //direction.add(d);
        
        int f = 1;
        for(int j=0; j<g; j++){
            //이동해야할 방향을 설정한다.
            int size = f;
            
            //int size = direction.size();
            for(int k= size-1; k>=0; k--){
                int n = dir[k];
                
                //int n = direction.get(k);
                if(n==3){
                    dir[f] = 0;
                    
                    //direction.add(0);
                } else {
                    dir[f] = n + 1;
                    
                    //direction.add(n+1);
                }
                f++;
            }
            
        }
        
        // 여기는 그림 그리기
        map[y][x] = true;
        for(int i=0; i < dir.length/*direction.size()*/; i++){
            y = y+dy[dir[i]];
            x = x+dx[dir[i]];
            
            //y = y+dy[direction.get(i)];
            //x = x+dx[direction.get(i)];
            
            map[y][x] = true;
        }
        
    }

}
