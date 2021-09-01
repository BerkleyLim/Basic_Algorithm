package author.hyun.sik.lim.dp.triangle;

public class Solution {
    public static void main(String[] args) {
        
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(triangle);
    }
    
    
    // 정수 삼각형,
    // 삼각형 형태(트리)로 정수가 주어지고,
    // 위에서 부터 아래로 정수의 합을 구한다
    // 단, 아래로 내려갈 때 왼쪽 오른쪽만 이동 가능하며
    // 여기서 최댓값을 구한다
    // 알고리즘 구조 예시
    //       7
    //     3   8
    //   8   1   0
    
    public static int solution(int[][] triangle) {
        
        // 이 방식은 brute-force 방식
        // 단 짧게 나누지 않고, 일일히 순차적으로 검사하기 때문에 실행시간 초과 가능성 높음
        // 실행 시간 : 2^n
//        int answer = 0;
//        int length = triangle.length;
//        int[] array = new int[length*2];
//        
//        for (int i = 0; i < length - 1; i++) {
//            
//            for (int j=0; j <= i*2; j*=2){
//                array[j] = triangle[i][j] + triangle[i+1][j];
//                answer = Math.max(array[j], answer);
//                array[j+1] = triangle[i][j] + triangle[i+1][j];
//                answer = Math.max(array[j+1], answer);
//            }
//        }
//        return answer;
        
        // 따라서 문제를 잘게 쪼개는 방식으로 접근
        // 아래서 부터 풀 경우 실행시간 대폭 감소 예정
        // 가장 밑에 있는 정수와 그 위에 있는 정수와 비교하여 올라간다
        // 실행시간 : 2n
        int length = triangle.length;
        //int[] array = new int[length - 1];
        
        // 예를 들면 높이가 5일 경우 높이가 4인 노드부터 시작하여 그 밑에 있는 정수와 비교하여 합을 넣는다
        for (int i = length - 2; i >= 0; i--) {
            
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i+1][j+1], triangle[i+1][j]);
            }
        }
        
        
        return triangle[0][0];
    }
}
