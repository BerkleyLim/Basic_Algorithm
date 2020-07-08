package author.hyun.sik.lim.backjoon.set.Q2606;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 웜 바이러스 문제
// 바이러스 확산 관련하여 응용한다.
// 한 컴퓨터가 바이러스 감염시 여러 컴퓨터를 랜선을 통해 확산된다.

// 여기서 1번 컴퓨터와 연결됬을 때 연결된 컴퓨터를 감염되는데, 1번 컴퓨터와 연결된 컴퓨터 갯수 구하기

// 첫째줄 : N
// 둘째줄 : couple
// couple 갯수 : 연결된 쌍의 정보

public class Main {
    static int[] computer;
    static int N;
    static int couple;
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        couple = Integer.parseInt(st.nextToken());
        
        computer = new int[N+1];
        for (int i = 0; i <= N; i++) {
            computer[i] = i;
        }
        
        for (int i = 0; i < couple; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Union(x,y);
        }
        
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (Find(i) == Find(1))
                count++;
        }
        bw.write(count + "\n");
        br.close();
        bw.close();
    }
    
    public static void Union(int x, int y) {
        x = Find(x);
        y = Find(y);
        
        if (x != y)
            computer[y] = x;
    }
    
    public static int Find(int x) {
        if(x == computer[x]) {
            return x;
        } else {
            int p = Find(computer[x]);
            computer[x] = p;
            return p;
        }
    }
}
