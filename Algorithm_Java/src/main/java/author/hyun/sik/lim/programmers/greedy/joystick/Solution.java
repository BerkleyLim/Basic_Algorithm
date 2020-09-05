package author.hyun.sik.lim.programmers.greedy.joystick;


// 조이스틱 문제
// 문제 : https://programmers.co.kr/learn/courses/30/lessons/42860
public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String name = "JEROEN";  // 56
        System.out.println(solution(name));
        
        name = "JAN"; // 23
        System.out.println(solution(name));
        
        name = "BBAABBB"; // 10
        System.out.println(solution(name));
    }
    
    // 조이스틱으로 알파벳 이름 완성!
    // 처음 : A로만 이뤄짐
    // up : 다음 알파벳, down : 이전 알파벳 (A에서 아래쪽을 이동시 z로)
    // left : 커서를 왼쪽으로 이동(첫번째 위치에서 왼쪽으로 이동시 커서 마지막으로 커서 이동)
    // right : 커서를 오른쪽으로 이동
    // 조이스틱 조작 횟수를 구하라 (최소의 방법으로)
    public static int solution(String name) {
        int answer = 0;
        int size = name.length();
        int exp = size - 1;
        
        // 알파벳 개수 : 26개
        int letter = 26;
        char[] array = name.toCharArray();
        int[] ch = new int[array.length];
        for (int i = 0; i <ch.length; i++) {
            ch[i] = array[i] - 'A';
        }
        
        // up, down을 먼저 구한다.
        for (int i = 0; i < size; i++) {
            if (ch[i] > (letter / 2)) {
                answer += (letter - ch[i]);
            } else {
                answer += ch[i];
            }
            
            // A는 0, count 변수는 A 갯수
            if (ch[i] == 0) {
                int nextIndex = i + 1;
                int count = 0;
                
                while (nextIndex < size && ch[nextIndex] == 0) {
                    count++;
                    nextIndex++;
                }
                
                int temp = (i - 1) * 2 + (size - 1 - i - count);
                if (exp > temp) exp = temp;
            }
        }
        
        answer += exp;
        
        return answer;
    }
    
}
