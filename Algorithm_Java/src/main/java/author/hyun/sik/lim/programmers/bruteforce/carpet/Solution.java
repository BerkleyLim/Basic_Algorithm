package author.hyun.sik.lim.programmers.bruteforce.carpet;

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        System.out.println(solution(10,2)); // [4,3]
        System.out.println(solution(8,1));  // [3,3]
        System.out.println(solution(24,24)); // [8,6]
    }
    
    
    // brown : 8 ~ 5,000
    // yellow : 1 ~ 2,000,000
    // 카펫 길이 : 가로 >= 세로
    // 카펫 길이의 가로와 세로 길이를 구하라
    public static int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        
        // 갈색 타일 갯수 = (가로 길이* 2) + (세로 길이* 2) -4
        // 노란색 타일 갯수 = (가로 길이 * 세로 길이) - 갈색 타일 갯수
        // 갈색 타일 갯수 + 노란식 타일 갯수 = 가로 * 세로
        for (int length = 1; length <= 5000; length++) { // 가로 길이
            for (int width = 1; width <= length; width++) { // 세로 길이
                
                // 조건을 아래와 같이 할 경우 갈색 타일 갯수와 노란색 타일 갯수의 조건에 모순 된다.
//                if ((brown + yellow) == ((length * width))) {
//                    answer[0] = length; 
//                    answer[1] = width; 
//                    return answer;
//                }
                // 따라서 갈색 타일 갯수와, 노란색 타일 갯수를 일일히 검사할 것
                if (brown == (length* 2) + (width * 2) - 4) {
                    if (yellow == (length * width) - brown) {
                        answer[0] = length; 
                        answer[1] = width; 
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }

}
