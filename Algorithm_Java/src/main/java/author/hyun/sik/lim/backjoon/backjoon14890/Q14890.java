package author.hyun.sik.lim.backjoon.backjoon14890;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14890
// 경사로 문제

public class Q14890 {
    // N 값 : 정사각형 지도의 크기, L 값 : 경사로 길이
    // mapLoad : 지도안에 있는 배열 갯수
    // load : 지나갈 수 있는 길의 갯수의 대한 결과 값
    // v : 이미 경사로에 올라온 해당 경로
    private static int N;
    private static int L;
    private static int[][] mapLoad;
    private static int load = 0;
    private static boolean[] v;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 첫째줄 N 값 및 L 값의 대한 입력 
        N = sc.nextInt();
        L = sc.nextInt();
        mapLoad = new int[N][N];
        
        
        // 둘째줄 입력 (배열 안에 있는 값)
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                mapLoad[y][x] = sc.nextInt();
            }
        }
        // 알고리즘  (가로기준)
        loadValue();
        
        //System.out.println(load);
        
        // 다음은 세로로 검색하기 위해 위치를 바꾼다.
        for (int y = 0; y < N; y++) {
            for (int x = y + 1; x < N; x++) {
                int temp = mapLoad[y][x];
                mapLoad[y][x] = mapLoad[x][y];
                mapLoad[x][y] = temp;
            }
        }
        
//        for (int y = 0; y < N; y++) {
//            for (int x = 0; x < N; x++) {
//                System.out.print(mapLoad[y][x] + " ");
//            }
//            System.out.println();
//        }
        
        // 다음 알고리즘 수행
        loadValue();
        
        System.out.println(load);
    }
    
    // 로직 풀이
    public static void loadValue() {

        // 가로용 풀이 (가로방향과 세로방향을 바꾸면 세로방향 풀이가 된다)
        for (int y = 0; y < N; y++) {
            v = new boolean[N];
            
            int prev = mapLoad[y][0]; // 가장 첫째열 기준으로 저장 
            
            int x = 1; // 다음 가로 크기 검사
            // 무한루프
            while(true) {
                // 오른쪽 끝까지 검사를 다 했으면 OK! (즉, 첫번째 행이 3이고 이에 해당하는 행이 모두 3일때)
                if (x == N) { load++; break; }
                if (prev == mapLoad[y][x]) { x++; }
                else {
                    // 여기서 다음 칸이 가장 첫번째 보다 높이가 낮을 때
                    if (mapLoad[y][x] == prev - 1) {
                        // 여기는 정사각형 크기가 안에 들어 갈 수 없을 때 (즉 오른쪽 초과) 
                        if (x + L - 1 >= N) break;
                        boolean go = true;
                        
                        // 여기서 L의 길이 (경사로)를 넣을 수 있는지 검사한다.
                        // 즉, x의 위치의 높이가 2이면 그 왼쪽높이가 L의 길이만큼 3가 되어야 한다.
                        for (int width = x ; width <= x + L - 1; width++) {
                            if (mapLoad[y][width] != mapLoad[y][x] || v[width]) {
                                go = false;
                                break;
                            }
                        }
                        
                        // 만일 갈 수 있는 경우, 지금 현재 기준하는(prev 변수) 높이 값을 낮추고,
                        // 다음 명령을 수행한다.
                        if (go) {
                            for (int width = x ; width <= x + L - 1; width++) {
                                v[width] = true;
                            }
                            x += L;
                            prev--;
                        } else {
                            break;
                        }
                        
                    // 여기서 다음 칸이 가장 첫번째 보다 높이가 높을 때
                    } else if (mapLoad[y][x] == prev + 1){
                        // 여기는 정사각형 크기가 안에 들어 갈 수 없을 때 (즉 왼쪽 초과)
                        if (x - L < 0) break;
                        
                        boolean go = true;
                        
                        // 여기서 L의 길이 (경사로)를 넣을 수 있는지 검사한다.
                        // 즉, x의 위치가 3이면 그 왼쪽높이가 L의 길이만큼 2가 되어야 한다.
                        for (int width = x - L ; width < x; width++) {
                            if (mapLoad[y][width] != mapLoad[y][x] - 1 || v[width]) {
                                go = false;
                                break;
                            }
                        }
                        
                        // 만일 갈 수 있는 경우, 지금 현재 기준하는(prev 변수) 높이 값을 올리고,
                        // 다음 명령을 수행한다.
                        if (go) {
                            for (int width = x - L; width < x; width++) {
                                v[width] = true;
                            }
                            x += 1;
                            prev++;
                        } else {
                            break;
                        }
                        
                    // 높이가 2 이상 차이가 날때
                    } else {
                        break;
                    }
                }
            }
        }
    }
}

// 참고 사이트 : https://lyzqm.blogspot.com/2017/10/2017-sw.html