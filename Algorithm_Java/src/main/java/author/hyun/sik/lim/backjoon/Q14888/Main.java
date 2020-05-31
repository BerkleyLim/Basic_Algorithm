package author.hyun.sik.lim.backjoon.Q14888;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 연산자 끼워넣기 문제 
// 참조 : https://lily-lee.postype.com/post/4833960


public class Main {
    public static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static int[] arr, calc;
    public static boolean[] v;
    public static ArrayList<Integer> al;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } // 숫자
        st = new StringTokenizer(br.readLine());

        // 0: + | 1: - | 2: * | 3: /
        al = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(st.nextToken());

            while (x-- > 0) {
                al.add(i);
            }

        } // FOR LOOP END

        calc = new int[N-1];
        v = new boolean[N-1];
        
        permcomb(0);
        System.out.println(max);
        System.out.println(min);
    }

    public static void permcomb(int cnt) {
        if (cnt == N-1) {
            int sol = calcprocess();
            min = min < sol?min:sol;
            max = max>sol?max:sol;
            return;
        }
        for (int i = 0; i < N-1; i++) {
            if (!v[i]) {
                v[i] = true;
                calc[cnt] = al.get(i);
                permcomb(cnt+1);
                v[i] = false;
            }
        }
    }
    public static int calcprocess() {
        int sol = arr[0];
        
        for(int i = 0; i<N-1; i++) {
            switch(calc[i]) {
            case 0:    // +
                sol += arr[i+1];
                break;
            case 1:    // -
                sol -= arr[i+1];
                break;
            case 2:    // *
                sol *= arr[i+1];
                break;
            case 3:    // /
                sol /= arr[i+1];
                break;
            }
        }

        return sol;
    }
}