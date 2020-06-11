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

public class SolutionNot {
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
        int answer = Integer.MAX_VALUE;
        
        for (int token = 1; token <= s.length()/2; token++) {
            int count = 0; // 초기 길이 값 (압축 후 문자열의 갯수)
            
            int start = 0;
            int end = start + token;
            
            String currentWord = s.substring(start, end);
            String nextWord;
            
            start += token;
            end += token;
            
            int tokenCount = 1;
            while (end <= s.length()) {
                nextWord = s.substring(start, end);
                // next가 cur와 달라지게 될때, cur의 압축 길이를 compressedLen에 더한다.  
                if(!currentWord.equals(nextWord)) {
                    
                    // 반복이 1 넘으면, 압축 수를 길이에 고려 
                    if(tokenCount > 1) {
                        count += (int)(Math.log10(tokenCount)+1);
                    }
                    count += currentWord.length();
                    
                    tokenCount = 0;
                    currentWord = nextWord;
                }
                
                tokenCount++;
                start += token;
                end += token;
            }
            
            // while문을 빠져나가며 고려되지 않은 남은 압축 길이 추가 
            end -= token;
            count += s.substring(end).length();
            
            // while문을 빠져나가며 고려되지 않은 마지막 압축 문자열 길이 추가  
            if(tokenCount > 1) {
                count += (int)(Math.log10(tokenCount)+1);
            }
            count += currentWord.length();
            
//            System.out.println();
            answer = Math.min(answer, count);
        }
        
        return answer;
    }
}
