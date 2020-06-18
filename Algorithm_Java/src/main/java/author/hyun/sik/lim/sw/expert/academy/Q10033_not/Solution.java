package author.hyun.sik.lim.sw.expert.academy.Q10033_not;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 카드 뒤집기
// N 개의 카드가 이렬로 놓여 있고
// 앞면 : 검은색(B), 뒷면 : 흰색(W)
// N개의 문자열 S로 표현 가능
// S의 왼쪽에서부터 i번째 글자는 카드 덱에서 왼쪽에서부터 i번째에 있는 카드의 윗면의 색

// 연산 과정  - 0회 이상 반복 가능
// 1) 왼쪽 부터 i번째 카드는 윗면 : 검은색, i+1번째 카드는 윗면이 흰색이 되도록 하는 정수 i (1 ~ N-1) 고름
//   만일 i가 존재하지 않을 시 연산 수행 불가
// 2) 왼쪽에서부터 i번째 카드와 i+1번째 카드를 뒤집음, 왼쪽에서부터 i번째 카드는 윗면이 흰색, i+1번째 카드는 윗면이 검은색

// 전제 조건 : 문자열 길이 200,000 이내
// 
public class Solution {
    static int indexB;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++) {
            String str = init(br);
            System.out.println("#" + test_case + " " + solution(str));
        }
        br.close();
    }

    private static int solution(String str) {
        // TODO Auto-generated method stub
        indexB = str.indexOf("B");
        //System.out.println(indexB);
        if (indexB < 0)
            return 0;
        
        int count = 0;
        int Bcount = 1;
        int N = str.length();
        String token = str.substring(indexB, N);
        
        // 여기서는 B가 몇개이고 W가 몇개이며 B와 W의 갯수 지정
        // B 갯수만큼 카운터를 센다
        // 이유는 : WWBBBBWWWWWBWBW 라고 주어질 경우를 생각한다.
        /*
            wwbbbwbwwwwwwwbwb - 3
            wwbbwbwbwwwwwwwbb - 6
            wwbwbwbwbwwwwwwbb - 9
            wwwbwbwbwbwwwwwbb - 13
            wwwwbwbwbwbwwwwbb - 17
            wwwwwbwbwbwbwwwbb - 21
            wwwwwwbwbwbwbwwbb - 25
            wwwwwwwbwbwbwbwbb - 29
            wwwwwwwwbwbwbwbbb - 33
            wwwwwwwwwbwbwbbbb - 36
            wwwwwwwwwwbwbbbbb - 38
            wwwwwwwwwwwbbbbbb - 39
         * */
        for (int i = 1; i < token.length(); i++) {
            if (token.substring(i,i+1).equals("B")) {
                Bcount++;
            } else {
                count += Bcount;
            }
        }
        return count;
    }

    private static String init(BufferedReader br) throws Exception {
        // TODO Auto-generated method stub
        String str = br.readLine();
        
        return str;
    }
}
