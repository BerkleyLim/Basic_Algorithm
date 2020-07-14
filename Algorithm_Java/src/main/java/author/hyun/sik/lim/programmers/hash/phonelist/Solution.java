package author.hyun.sik.lim.programmers.hash.phonelist;

import java.util.Arrays;

// 전화번호 목록 문제 (Hash)
// 트라이 구현방법 참고 : https://the-dev.tistory.com/3
public class Solution {
    public static void main(String[] args) {
        
        String[] phone_book = {"119", "97674223", "1195524421"}; //false
        System.out.println(solution(phone_book));
        phone_book = new String[]{"123","456","789"};   //true
        System.out.println(solution(phone_book));
        phone_book = new String[]{"12","123","1235","567","88"};    //false
        System.out.println(solution(phone_book));
    }
    
    // 한 번호가 다른 번호의 접두어인 경우 있는지 검사 문제
    // phone_book의 길이는 1 ~ 1,000,000
    // 각 전화번호의 길이는 1이상 20이하
    // 트라이 알고리즘
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length -1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false; 
            }
        }
        return true;
    }
}
