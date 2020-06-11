package author.hyun.sik.lim.kakao.career.years19.no1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 압축 문제
// 문자열 압축 방법의 대해 공부하고 있음
// 대량 데이터 처리를 위한 간단한 비손실 압축 방법의 대해 공부
// 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복 되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현
// 문자 1개 단위로 자를경우 전혀 압축되지 않지만
// 2개 단위로 잘라 압축시 2ab2cd2ab2cd로 표현 가능
// 2ababcdcd로 표현 가능
// 여기서 출력시 문자열의 길이를 최소로 출력할것

// 출처 : https://keepgoing0328.tistory.com/entry/2020%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B3%B5%EC%B1%84-%EB%AC%B8%EC%9E%90%EC%97%B4-%EC%95%95%EC%B6%95-%EC%9E%90%EB%B0%94

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(solution("aabbaccc")); // 7
        System.out.println(solution("ababcdcdababcdcd")); // 9
        System.out.println(solution("abcabcdede")); // 8
        System.out.println(solution("abcabcabcabcdededededede")); // 14
        System.out.println(solution("xababcdcdababcdcd")); // 17
        System.out.println(solution("aabbccaabbcceeddaaaaddaa")); // 19
        
    }
    
    public static int solution(String s) {
        int minLen = s.length();
       // unit 단위만큼 검사 
       for(int unit = 1 ; unit <= s.length()/2 ; unit++) {
           // 검사할 문자의 범위 start ~ end 
           int start = 0;
           int end = start + unit;
           int compressedLen = 0;
           int repeatedCnt = 1;
           
           // unit만큼 자른 첫 문자열  
           String curWord = s.substring(start, end);
           String nextWord;
           
           start += unit;
           end += unit;
           
           // 문자열 끝까지 검사 
           while(end <= s.length()) {
               nextWord = s.substring(start ,end);
               
               // next가 cur와 달라지게 될때, cur의 압축 길이를 compressedLen에 더한다.  
               if(!curWord.equals(nextWord)) {
                   
                   // 반복이 1 넘으면, 압축 수를 길이에 고려 
                   if(repeatedCnt > 1) {
                       compressedLen +=  (int)(Math.log10(repeatedCnt)+1);
                   }
                   compressedLen += curWord.length();
                   
                   repeatedCnt = 0;
                   curWord = nextWord;
               }
               
               repeatedCnt++;
               start += unit;
               end += unit;
           }
           
           // while문을 빠져나가며 고려되지 않은 남은 압축 길이 추가 
           end -= unit;
           compressedLen += s.substring(end).length();
           
           // while문을 빠져나가며 고려되지 않은 마지막 압축 문자열 길이 추가  
           if(repeatedCnt > 1) {
               compressedLen += (int)(Math.log10(repeatedCnt)+1);
           }
           compressedLen += curWord.length();
           
           if (  minLen > compressedLen) {
               minLen = compressedLen;
           }
       }

       return minLen;
   }
}
