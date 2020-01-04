package author.hyun.sik.lim.sw.expert.academy.Q1213;

import java.util.Scanner;

// 영어 문장에서 특정한 문자열 개수 반환하는 프로그램 작성
// 문장 길이 1000자 안넘어감
// 한 문장에서 검색하는 문자열의 길이는 최대 10을 안넘음
// 한 문장에서는 하나의 문자열만 검색
// 1줄 : 테케 번호
// 2줄 : 찾을 문자열
// 3줄 : 검색할 문장이 주어짐
// 무어 알고리즘 풀이도 해보기
public class Solution {
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        int num = 0;
        
        // 솔루션 시작!
        while(true) {
            num = sc.nextInt();
            String pattern = sc.next();
            String string = sc.next();
            
            int count = 0;
            int fromIndex = -1;
            
            while((fromIndex = string.indexOf(pattern, fromIndex + 1)) >= 0) {
                count++;
            }
            
            System.out.println("#"+num+" "+count);
            // 종료
            if (num == 10)
                break;
        }
        
    }

}
