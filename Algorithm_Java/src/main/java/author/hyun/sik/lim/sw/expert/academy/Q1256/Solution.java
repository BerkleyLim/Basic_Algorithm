package author.hyun.sik.lim.sw.expert.academy.Q1256;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 트라이 문자
// 트라이 참고 : https://4ngs.tistory.com/24
// K번째 접미어 찾기 문제


public class Solution {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int test = 1; test <= T; test++) {
            int K = Integer.parseInt(br.readLine());
            String str = br.readLine();
            bw.write("#"+test+" "+solution(K, str)+"\n");
        }
        
        br.close();
        bw.close();
    }
    
    static int N;
    static class TrieNode {
        TrieNode[] child;
        boolean isTerminal;
        public TrieNode() {
            this.child = new TrieNode[N];
            this.isTerminal = false;
        }
    }
    
    private static String solution(int K, String str) {
        // TODO Auto-generated method stub
        N = str.length();
        String[] s = new String[N];
        
        return null;
    }

}
