package author.hyun.sik.lim.programmers.dp.goschool;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        
        System.out.println(solution(m,n,puddles));

    }

    
    // 등굣길 문제
    // 최단 거리 갯수 구하는 알고리즘
    // 통계적 수학 알고리즘 방식으로 구하기
    // 참조 공식 https://bhsmath.tistory.com/154
    // 초등학생 방식으로 풀기
    // 시간복잡도 : N^2
    static int[][] map;
    
    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        map = new int[m+1][n+1];
        
        // 값 셋팅
        init(m,n,puddles);
        
        // 여기서부터 논리식 시작
        
        return answer;
    }

    private static void init(int m, int n, int[][] puddles) {
        // TODO Auto-generated method stub
        
        // map 배열에서 -1 : 웅덩이
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            map[x][y] = -1;
        }
        
        // 다음은 1가지 경우의 수를 셋팅한다
        for (int i = 2; i < m; i++) {
            if (map[i][1] < 0)
                break;
            else
                map[i][1] = 1;
        }
        
        for (int i = 2; i < n; i++) {
            if (map[i][1] < 0)
                break;
            else
                map[1][i] = 1;
        }
    }


    
}
