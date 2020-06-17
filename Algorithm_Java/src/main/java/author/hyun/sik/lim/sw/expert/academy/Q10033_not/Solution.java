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
            System.out.println(solution(str));
        }
        br.close();
    }

    private static int solution(String str) {
        // TODO Auto-generated method stub
        if (indexB < 0)
            return 0;
        
        int count = 0;
        int N = str.length();
        String token = str.substring(indexB, N);
        while(true) {
            boolean isExit = false;
            
            boolean isSearchB = false;
            int Bend = 0;
            int Wend = 0;
            int Wcount = 0; // W 갯수만큼 곱한다.
            String temp = "";
            for (int i = 0; i < token.length(); i++) {
                // 이거는 bw 패턴에 대해서 풀기 (brute 관련 알고리즘)
//                if (token[i].equals("B") && token[i+1].equals("W")) {
//                    // break 멈추기
//                    if (isExit) { 
//                        isExit = false;
//                    }
//                    count++;
//                    // 바꾸기
//                    token[i] = "W"; token[i+1] = "B";
//                    index = i;
//                    break;
//                } else {
//                    continue;
//                }
                
                // 여기서는 B가 몇개이고 W가 몇개이며 B와 W의 갯수 지정
                // B 갯수만큼 카운터를 센다
                
                if (token.substring(i,i+1).equals("B")) {
                    if (isSearchB) {
                        count *= Wcount;
                        break;
                    }
                    Bend++;
                    Wend++;
                    count++;
                } else {
                    Wend++;
                    Wcount++;
                    isSearchB = false;
                }
            }
            // 문자열 바꾸기
            temp += token.substring(0, Bend);
            //System.out.println(temp);
            if (Wend != token.length())
                temp += token.substring(Wend, token.length());
            token = temp;  
            //System.out.println(temp);
            if (!temp.contains("W")) isExit = true;
            if (isExit) break;
        }
        return count;
    }

    private static String init(BufferedReader br) throws Exception {
        // TODO Auto-generated method stub
        String str = br.readLine();
        
        indexB = str.indexOf("B");
        return str;
    }
}
