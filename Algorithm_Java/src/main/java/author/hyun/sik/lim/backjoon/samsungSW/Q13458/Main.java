package author.hyun.sik.lim.backjoon.samsungSW.Q13458;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시험감독
// N개 시험장 존재
// i번 시험장 응시자 수 A명
// 감독관 : 총감독관, 부감독관으로 나눠짐
// 총감독관 : 한 시험장에서 감시할 수 있는 응시자의 수 : B명
// 부감독관 : 한 시험장에서 감시할 수 있는 응시자의 수 : C명
// 총감독관 : 각 교실 1명, 부감독관 : 여러명
// 전제 조건 : 응시생 모두 감시해야함
// 감독관 수 최솟값은?

// 첫째 줄 : N(시험장 갯수, 최소 1개, 최대 1,000,000개)
// 둘째 줄 : A(각 시험장에 있는 응시자수 : 최소 1명, 최대 1,000,000명)
// 셋째 줄 : B와 C (1 < B,C < 1,000,000)

// 부르드 포스 문제(만일 총감독관 1명 + 나머지 부감독관을 해서 나눈 경우가 0이면 패스)
public class Main {
    static int N; // 시험장 갯수
    static int[] A; // 각 시험장 당 응시자 수
    static int B; // 총감독관 감시범위
    static int C; // 부감독관 감시범위
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        System.out.println(rangeing());
    }

    private static long rangeing() {
        // TODO Auto-generated method stub
        long min = 0;
        
        // 초기화!
        for (int i = 0; i < N; i++) {
            // Math.floor(a) : 버림
            // Math.abs(a) : 절대값
            // Math.ceil(a) : 올림
            // Math.round(a) : 반올림
            A[i] -= B;
            min++;
            
            // 여기서 B 혼자 가능하면 C는 필요 없음 (if문 두지 않으면 백준에서 에러남)
            if (A[i] > 0)
                min += (A[i] % C == 0) ? (A[i] / C) : (A[i] / C + 1);
        }
        
        return min;
        
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        A = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        br.close();
    }

}
