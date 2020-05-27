package author.hyun.sik.lim.backjoon.Q16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 나무 재테크 문제
// 상도가 N * N 크기의 땅을 구매
// 땅 관리를 위해 1 * 1 크기의 칸으로 나눔
// 각 칸은 (r,c)로 나타냄
// 땅에 로봇 S2D2를 배치 예정 (땅의 양분을 조사를 위해) - 크기 : 1 * 1
// S2D2는 모든 칸의 대해 조사
// 가장 처음에는 양분은 모든 칸에 5만큼 있다.
// 여기서 나무 재테크를 하기 위해 작은 묘목을 구매 후 어느정도 키워 판매한다.
// M개의 나무를 구매하여 땅을 심음

// 봄
// 하나의 칸에 여러 개의 나무가 주어질 시 나이가 어는 나무부터 양분을 먹는다.
// 땅에 양분이 부족시 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽음

// 여름
// 봄에 죽은 나무가 양분으로 변함
// 각 죽은 나무마다 나이를 2로 나눈 값이 양분이다(소수점 값 무시)

// 가을 
// 나무 번식, 번식하는 나무는 나이가 5의 배수
// 인접한 8개의 칸에 나이가 1인 나무가 생김
// (r,c) 칸 인접 기준, 가로 세로 대각선
// 땅이 벗어나는 칸은 나무가 생기지 않음

// 겨울
// S2D2가 땅을 돌아다니면서 땅에 양분을 추가
// 각 칸에 추가되는 양분의 양은 A[r][c]

// k 년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구한다.

// 입력 조건
// 첫째줄 : N, M, K
// 둘째줄 부터 N개의 줄 : A 배열 값
// 마지막 줄 : 상도가 심은 나무의 정보를 나타내는 세 정수 r, c, years
//       좌표 (r, c)와 year : 나무의 나이


// 참조 : https://lily-lee.postype.com/post/4906542

public class Solution {
    public static class Tree{
        int r, c, years;

        public Tree(int r, int c, int years) {
            this.r = r;
            this.c = c;
            this.years = years;
        }

        public Tree(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Tree [r=" + r + ", c=" + c + ", years=" + years + "]";
        }
    }

    public static int N, M, K;
    public static int dr[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
    public static int dc[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
    public static int[][] nutr, maps, dead; // 양분양, 초기 양분, 죽은 나무
    public static Deque<Tree> tree;

    //소요시간: 1시간 7분 메모리: 281000kb 시간 716ms
    public static void main(String[] args) throws Exception {
        INIT(); // 초기 값!
        for(int i = 0; i<K; i++) {
            SS();
            FW();
        }
        System.out.println(tree.size());
    }

    public static void INIT() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        try {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken()); // NxN배열의 크기
            M = Integer.parseInt(st.nextToken()); // M줄의 심은 나무의 정보
            K = Integer.parseInt(st.nextToken()); // K년 후의 상태
            nutr = new int[N][N]; // 기본/겨울의 추가 양분 정보
            maps = new int[N][N]; // 기본 땅의 정보 -> 영양분은 5씩 들어있음
            dead = new int[N][N]; // 죽은 나무 양분으로
            tree = new LinkedList<Tree>();
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    nutr[i][j] = Integer.parseInt(st.nextToken());
                    maps[i][j] = 5;
                }
            } // 양분 끝

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int year = Integer.parseInt(st.nextToken());

                tree.add(new Tree(r, c, year));
            } // 초기 입력으로 주어지는 나무의 위치는 모두 서로 다름
        } catch (IOException e) {
        }

    }

    public static void SS() {
        // Spring

        // Eat nutr
        for (int i = 0; i < tree.size();) {
            Tree t = tree.poll();
            if (maps[t.r][t.c] >= t.years) {
                maps[t.r][t.c] -= t.years;
                t.years++;
                i++;
                tree.add(t);
            } else {
                // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
                // 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점은 버린다.
                dead[t.r][t.c] += (t.years / 2);
            }
        }

        // Summer
        // Add to nutr
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maps[i][j] += dead[i][j];
                dead[i][j] = 0;    //죽은 나무 초기화
            }
        }

    }

    public static void FW() {
        // Fall -> 가을에는 나무가 번식한다.
        // 번식하는 나무는 나이가 5의 배수이어야 하며 
        Queue <Tree> q = new LinkedList<Tree>();
        for(int i = 0; i<tree.size(); i++) {
            Tree t = tree.poll();
            if(t.years %5 == 0) {
                q.offer(t);
            }
            tree.add(t);
        }
        
        while(!q.isEmpty()) {
            Tree t = q.poll();
            
            for(int d = 0; d<dr.length; d++) {
                int nr = t.r + dr[d];
                int nc = t.c + dc[d];
                if(isIn(nr, nc)) {
                    //인접한 8개의 칸에 나이가 1인 나무가 생긴다.
                    //기존 위치에 있던 나무보다는 새로 생기는 나무의 나이가 어리니
                    //addFirst를 사용해준다
                    tree.addFirst(new Tree(nr, nc, 1));
                }
            }
        }
        
        //Winter -> 땅을 돌아다니면서 땅에 양분을 추가한다.
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                maps[i][j] += nutr[i][j];
            }
        }
        
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }
}
