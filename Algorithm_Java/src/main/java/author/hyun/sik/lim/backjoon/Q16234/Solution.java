package author.hyun.sik.lim.backjoon.Q16234;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// N * N 땅이 존재
// 땅은 1 * 1 개 칸으로 나눠지고 각 나라의 영역이라 한다
// 영역마다 A[r][c] 명이 살고 있음
// 모든 국경선은 정사각형 형태
// 알고리즘 수행
// 인구 이동의 날이고, 인구 이동이 없을 때까지 존재

// 인구이동 조건
// 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루동안 연다.
// 2. 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
// 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
// 4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
// 5. 연합을 해체하고, 모든 국경선을 닫는다.

// 각 나라의 인구수가 주어질 시 인구 이동이 몇번 발생하는지 알아보기!

// N(1 ~ 50), L(1 ~ R), R (L ~ 100) 개 주어짐
// A[r][c] 별 인구수 입력
public class Solution {
    static int N;
    static int L;
    static int R;
    
    static int[][] A;
    
    public static void main(String[] args) throws Exception {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String s = bf.readLine();   // string
//        int i = Integer.parseInt(bf.readLine());    // int
//        
//        StringTokenizer st = new StringTokenizer(s); //StringTokenizer인자값에 입력 문자열 넣음
//        int a = Integer.parseInt(st.nextToken()); //첫번째 호출
//        int b = Integer.parseInt(st.nextToken()); //두번째 호출
//
//        String array[] = s.split(" "); //공백마다 데이터 끊어서 배열에 넣음
//        
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
//        /* String */ s = "abcdefg";//출력할 문자열
//        bw.write(s+"\n");//출력
//        bw.flush();//남아있는 데이터를 모두 출력시킴
//        bw.close();//스트림을 닫음
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        A = new int[N][N];
        
        // 각각 나라별 인구 입력
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(bf.readLine());
            for (int x = 0; x < N; x++) {
                A[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 솔루션 시작!
        
        bf.close();
    }
}
