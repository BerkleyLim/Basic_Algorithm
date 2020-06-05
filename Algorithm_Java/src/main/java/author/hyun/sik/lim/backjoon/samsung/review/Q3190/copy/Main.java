package author.hyun.sik.lim.backjoon.samsung.review.Q3190.copy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 뱀 문제
// N*N 정사각 보드 위 진행
// 사과가 놓여진 것을 먹으면
// 뱀길이 증가


public class Main {
    static Scanner sc = new Scanner(System.in);
    // direction : default - 0, L : -1 감소, D : 1 증가
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N;
    static int K;
    
    // 여기서 뱀 길이 : 1, 사과 : 2, 빈칸 : 0
    static int[][] map;
    
    static int L;
    
    static class TimeTable {
        int s;
        char l;
        public TimeTable(int s, char l) {
            this.s = s;
            this.l = l;
        }
    }
    
    static int snakeX;
    static int snakeY;
    static int direction;
    
    static Queue<TimeTable> timeTable;
    static Queue<Location> snake;
    static class Location {
        int x, y;
        Location (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Init(); // 초기값
        int time = snakeGame(); // 알고리즘 게임
        System.out.println(time);
    }
    
    private static int snakeGame() {
        // TODO Auto-generated method stub
        int t = 0;
        snake = new LinkedList<>();
        snake.add(new Location(snakeX, snakeY));
        while(t < 10000) {
            int x = snakeX;
            int y = snakeY;
            
            // 타임테이블 정보대로 명령 수행할 것!
            if (!timeTable.isEmpty() && timeTable.peek().s == t) {
                TimeTable tt = timeTable.remove();
                if (tt.l == 'L') {
                    direction--;
                    if (direction < 0)
                        direction = 3;
                } else {
                    direction++;
                    if (direction == 4)
                        direction = 0;
                }
            }
            
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            t++;
            // 벽일 경우 게임 종료
            if (isWall(nx, ny)) {
                //t++;
                break;
            } else {
                // 여기서 사과일 경우와 아닐 경우
                if (map[ny][nx] == 2) {
                    map[ny][nx] = 1;
                    snake.add(new Location(nx, ny));
                } else {
                    if (map[ny][nx] == 1) {
                        //t++;
                        break;
                    } else {
                        Location tail = snake.remove();
                        map[tail.y][tail.x] = 0;
                        snake.add(new Location(nx, ny));
                        map[ny][nx] = 1;
                    }
                }
                snakeX = nx;
                snakeY = ny;
            }
//            System.out.print(t + " ( " + snakeX + ", " + snakeY + " ) " );
//            System.out.println();
            //t++;
        }
        return t;
    }
    
    // 벽이거나 몸통에 부딪친 경우
    private static boolean isWall(int nx, int ny) {
        // TODO Auto-generated method stub
        if (nx == 0 || ny == 0 || nx == N+1 || ny == N+1)
            return true;
        else
            return false;
    }

    // 초기 입력 값
    private static void Init() {
        N = sc.nextInt();
        map = new int[N+1][N+1];
        snakeX = 1;
        snakeY = 1;
        direction = 0;
        map[snakeY][snakeX] = 1;
        
        K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            map[y][x] = 2;
        }
        
        L = sc.nextInt();
        timeTable = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            int x = sc.nextInt();
            char l = sc.next().charAt(0);
            timeTable.add(new TimeTable(x, l));
        }
    }

}
