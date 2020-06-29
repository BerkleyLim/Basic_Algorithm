package author.hyun.sik.lim.sw.expert.academy.Q4112;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이상한 피라미드 탐험
// 인접한 지역 찾기
// 다음은 정사각형 형태의 구성된 모습을 찾아본다.
// 여기서 보물이 있는 구역에서 보물을 찾을때까지의 최소 시간을 구한다.

public class Solution {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            System.out.println("#"+test+" "+solution(a,b));
        }
        br.close();
    }

    private static int solution(int a, int b) {
        // TODO Auto-generated method stub
        int count = 0;
        
        // 여기서 
        int A = Math.min(a,b);
        int B = Math.max(a,b);
        
        int Alevel = 0, Blevel = 0;
        int level = 0;
         
        boolean stopA = false;
        boolean stopB = false;
        // 이건 DP 문제풀이법!
        
        // 먼저 높이를 구한다!
        for(int i = 1; i<10000; i++){
            count += i;
            level++;
            if(stopA == false && A <= count){
                Alevel = level;
                stopA = true;
            }
            if(stopB == false && B <= count){
                Blevel = level;
                stopB = true;
            }
            
            // A와 B를 찾으면!
            if(stopA && stopB)
                break;
        }
         
        // 다음으로  B레벨이랑 동일하게 더할것!
        for(int i = Alevel; i< Blevel; i++){
            A += i;
        }
        
        // 같은 레벨일 때 비교해보기
        int ABDiffrent = B - A;
        int levelDiffrent = Blevel - Alevel;
        int result = 0;
         
        if(ABDiffrent <=0){
            result = Math.abs(ABDiffrent) + levelDiffrent;
        }else{
            if(ABDiffrent >= levelDiffrent){
                result = ABDiffrent;
            }else
                result = levelDiffrent;
        }
        
        return result;
    }

}
