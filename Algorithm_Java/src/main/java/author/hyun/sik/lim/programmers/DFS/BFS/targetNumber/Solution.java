package author.hyun.sik.lim.programmers.DFS.BFS.targetNumber;

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] numbers = new int[] {1,1,1,1,1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }
    
    // n개의 음이 아닌 정수가 있다!
    // n개의 자연수를 이용하여 target 값을 지정하여 일치하는 갯수를 출력하라
    static int n;
    public static int solution(int[] numbers, int target) {
        n = 0;
        dfs(numbers, target, 0, 0);
        return n;
    }

    private static void dfs(int[] numbers, int target, int count, int sum) {
        // TODO Auto-generated method stub
        if (count >= numbers.length) {
            if (sum == target)
                n++;
            return;
        }
        
        // i = 0 일때 +, i = 1일때 -
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                dfs(numbers, target, count+1, sum+numbers[count]);
            } else {
                dfs(numbers, target, count+1, sum-numbers[count]);
            }
        }
    }
}
