package author.hyun.sik.lim.programmers.greedy.makingBigNumbers;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String number = "1924";
        int k = 2;
        System.out.println(solution(number,k)); // 94
        
        number = "1231234";
        k = 3;
        System.out.println(solution(number,k)); // 3234
        
        number = "4177252841";
        k = 4;
        System.out.println(solution(number,k)); // 775841
    }
    
    // 어떤 숫자에서 k개의 수를 제거시 얻을수 있는 가장 큰 숫자 구하기
    // 1924의 경우 19, 12, 14, 92, 94, 24로 나눌 수 있음
    // 이때 가장 큰 숫자는?
    public static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        
        // 글자길이 표시
        int length = number.length() - k;
        
        // 기준점
        int start = 0; // 초기 시작점 : 0
        int end = number.length() - length; // 끝 점 : 짜를 범위
        int max = -1;
        
        int index = 0;
        
        while(length > 0) {
            max = -1;
            
            for (int i = start; i <= end; i++) {
                int num = number.charAt(i) - '0';
                
                if (num > max) {
                    index = i;
                    max = num;
                }
            }
            
            answer.append(number.charAt(index));
            start = index + 1;
            end = number.length() - --length;
        }
        
        return answer.toString();
    }
}
