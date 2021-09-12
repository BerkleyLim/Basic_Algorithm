package author.hyun.sik.lim.programmers.kakao22.q2;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        
        System.out.println(solution(437674,3)); // 3
        System.out.println(solution(110011,10)); // 2
    }
    // k진법 구한 후 소수의 갯수
    
    public static int solution (int n, int k) {
        int answer = -1;
        int[] receiveNumber = new int[10000000];
        int insu = 0;
        while (n >= k) {
            receiveNumber[insu++] = n % k;
            n /= k;
//            System.out.print(number[insu - 1]);
        }
        receiveNumber[insu++] = n % k;
//        System.out.print(number[insu - 1]);
//        System.out.println();
        
        long a = 0;
        for (int i = 0; i < insu; i++) {
            a += receiveNumber[i] * Math.pow(10, i);
        }
//        System.out.println(a);
        return answer;
    }
}
