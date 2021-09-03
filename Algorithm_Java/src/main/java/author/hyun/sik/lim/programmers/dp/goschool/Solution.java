package author.hyun.sik.lim.programmers.dp.goschool;

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
        map = new int[m+1][n+1];
        map[1][1] = 1;
        
        // 값 셋팅
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            map[x][y] = -1;
        }
        
        // 여기서부터 논리식 시작
        // 문제에서 1,000,000,007을 나눈 나머지 값으로 나타내라는 의미는 효율성 때문이다
        
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                if (map[x][y] < 0) {
                    continue;
                }
                
                if (map[x][y-1] >= 0 && map[x][y] >= 0)
                    map[x][y] += map[x][y-1] % 1000000007;
                if (map[x-1][y] >= 0 && map[x][y] >= 0)
                    map[x][y] += map[x-1][y] % 1000000007;
            }
                
        }
        
        return map[m][n] % 1000000007;
    }
}
