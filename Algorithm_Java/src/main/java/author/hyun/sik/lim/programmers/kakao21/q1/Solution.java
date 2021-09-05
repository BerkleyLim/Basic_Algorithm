package author.hyun.sik.lim.programmers.kakao21.q1;

public class Solution {
    public static void main(String[] args) {
        
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm")); //   "bat.y.abcdefghi"
        System.out.println(solution("z-+.^."));  //  "z--"
        System.out.println(solution("=.=")); //  "aaa"
        System.out.println(solution("123_.def"));  // "123_.def"
        System.out.println(solution("abcdefghijklmn.p"));  // "abcdefghijklmn"
    }
    
    // 신규 아이디 추천
    // 카카오 아이디 규칙에 맞지 않는 아이디 입력 시, 입력된 아이디와 유사하면서 규칙에 맞는 아이디 추천
    // ID 길이 : 3~15
    // 사용 가능 ID : 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 
    // 마침표(.) : 처음과 끝에 사용 불가
    
    // 아이디 바꾸는 과정
    // 1) 대문자 -> 소문자
    // 2) 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 제외한 나머지 제거
    // 3) 여러개의 마침표가 2번 이상일 경우 하나의 마침표(.)로 변경
    // 4) 마침표(.)가 처음이랑 끝에 위치 시 제거
    // 5) 빈 문자열일시 a로 대입
    // 6) 길이가 16자 이상 시, 15개 문자 제외한 나머지 문자들을 모두 제거
    // 7) 길이가 2자 이하시 마지막 문자를 3이 될때까지 반복해서 끝에 붙힌다
    public static String solution(String new_id) {
        String answer = "";
        int length = new_id.length();
        
        // 1단계 : 소문자 변환
        answer = new_id.toLowerCase();
        new_id = answer;
        
        // 2단계 : 사용 가능 ID 문자열 조건에 맞게 수행
        answer = "";
        for (int i = 0; i < length; i++) {
            if (!((new_id.charAt(i) >= 'a' && new_id.charAt(i) <= 'z') 
                    || (new_id.charAt(i) >= '0' && new_id.charAt(i) <= '9') 
                    || new_id.charAt(i) == '-'
                    || new_id.charAt(i) == '_'
                    || new_id.charAt(i) == '.')) {
                continue;
            }
            else
                answer += new_id.substring(i, i+1);
        }
        new_id = answer;
        length = new_id.length();
        
        // 3단계 : 마침표가 2개 연속 이상일시 1개로 줄인다
        answer = "";
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (new_id.charAt(i) == '.')
                count++;
            else
                count = 0;
            
            if (count > 1) 
                continue;
            else {
                answer += new_id.substring(i, i+1);
            }
        }
        new_id = answer;
        length = new_id.length();
        
        // 4단계 : 마침표 첫문자와 끝문자 제거
        answer = "";
        
        if (new_id.charAt(0) == '.')
            answer = new_id.substring(1, length);
        else if (new_id.charAt(length-1) == '.') 
            answer = new_id.substring(0, length-1);
        else if (new_id.charAt(0) == '.' && new_id.charAt(length-1) == '.') 
            answer = new_id.substring(1, length-1);
        else
            answer = new_id;

        new_id = answer;
        length = new_id.length();
        
        // 5단계 : 빈문자열을 a 하나 채우기
        answer = "";
        
        if (length == 0) {
            answer = "a";
            new_id = answer;
            length = new_id.length();
        } else
            answer = new_id;
        
        new_id = answer;
        length = new_id.length();
        
        
        
        // 6단계 : 길이 짜르기, 짜른 후 마지막 문자열에 마침표(.)가 있는경우 즉시 제거
        answer = "";
        
        if (length > 15)
            answer = new_id.substring(0,15);
        else
            answer = new_id;
        
        new_id = answer;
        length = new_id.length();
        
            //문자 마지막 마침표 제거
        answer = "";
        for (int i = length -1; i >= 0; i--) {
            if (new_id.charAt(i) == '.') {
                continue;
            } else {
                answer = new_id.substring(0,i+1);
                break;
            }
        }
        
        new_id = answer;
        length = new_id.length();
        
        
        // 7단계 : 3자 이하 문자열 공백채우기 
        if (length <= 3) {
            String letter = new_id.substring(length-1);
            
            for (int i = length; i < 3; i++) {
                answer += letter;
            }
            
            return answer;
        } else 
            return answer;
    }
}
