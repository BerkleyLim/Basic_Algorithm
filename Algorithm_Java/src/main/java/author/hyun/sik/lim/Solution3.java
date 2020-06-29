package author.hyun.sik.lim;

public class Solution3 {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(solution("FRANCE","french")); // 16384
        System.out.println(solution("handshake","shake hands"));  // 65536
        System.out.println(solution("aa1+aa2","AAAA12")); // 43690
        System.out.println(solution("E=M*C^2","e=m*c^2"));  // 65536
    }
    
    // 1번, 자카드 유사도
    // 집합 간 유사도 검사하는 여러 방법 중의 하나
    // 두 집합 A, B사이에 자카드 유사도 J(A, B)는 
    // 두 집합의 교집합 크기를 두 집합의 합 집합 크기로 나눈 값
    // A = {1, 2, 3} , B = {2, 3, 4} 일 경우
    // 교집합 = {2, 3};
    // 합집합 = {1, 2, 3, 4};
    // J(A,B) : 0.5
    // 공집합일 경우 : 1
    // 두 문자열 사이의 자카드 유사도 J("FRANCE", "FRENCH") = 2/8 = 0.25가 된다.
    // 값은 65536을 곱해서 출력할껏!
    public static int solution(String str1, String str2) {
        double answer = 0;
        
        
        
        return (int) answer * 65536;
    }
}
