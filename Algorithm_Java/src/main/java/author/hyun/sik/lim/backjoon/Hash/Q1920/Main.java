package author.hyun.sik.lim.backjoon.Hash.Q1920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 수 찾기 문제
// N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 
// 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

// 입력조건
// 첫째 줄에 자연수 N(1≤N≤100,000)이 주어진다. 
// 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 
// 다음 줄에는 M(1≤M≤100,000)이 주어진다. 
// 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 
// 모든 정수의 범위는 -2^31 보다 크거나 같고 2^31보다 작다.

// 출력조건
// M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
public class Main {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        
        int[] X = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }
        
        solution(A, X);
        br.close();
    }

    private static void solution(int[] A, int[] X) {
        // TODO Auto-generated method stub
        HashSet hash = new HashSet();
        int N = A.length;
        for (int i = 0; i < N; i++) {
            hash.add(A[i]);
        }
        
        int M = X.length;
        for (int i = 0; i < M; i++) {
            if (hash.contains(X[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
        hash.clear();
    }

}
