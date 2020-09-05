package author.hyun.sik.lim;

public class Solution4 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(solution(2,4,2,1));    // 0111
        System.out.println(solution(16,16,2,1));  // 02468ACE11111111
        System.out.println(solution(16,16,2,2));  // 13579BDF01234567
       
    }
    
    // 0 부터 시작해서 차례대로 말함 
    // 두번째 : 1, 세번째 2, 10번째 : 9를 말함
    // 10 이상의 숫자부터 한 자리씩 끊어서 말함
    // 열두번 째 사람 : 둘째자리 0을 말함 
    // 여기서 십육진법까지 모든 진법으로 게임을 진행
    // 자신이 말해야 하는 숫자를 스마트폰에 미리 출력해주는 프로그램을 제작
    // n(진법) : 2 ~ 16
    // t(미리 구할 숫자 갯수) : 0 ~ 1000
    // m(게임 참가 인원) : 2 ~ 100
    // p(튜브 순서) : 1 ~ p ~ m
    // 출력은 : 10~15면 A~F로 출력
    static char[] DIGIT = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String solution(int n, int t, int m, int p) {
        String answer = "";
        
        String str = "";
        for (int i = 0; i < t * m; i++) {
            str += digitCode(i,n);
        }
        
        int count = 0;
        
        for (int i = p-1; i < str.length(); ) {
            answer += str.substring(i,i+1);
            
            if (count + 1 == t) break;
            count++;
            i += m;
        }
        
        return answer;
    }
    
    private static String digitCode(int digit, int n) {
        String result = "";
        
        while (digit / n != 0) {
            result = DIGIT[digit%n] + result;
            digit /= n;
        }
        
        result = DIGIT[digit%n] + result;
        return result;
    }
    
    
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String s = "1 2 3 4"; //  1 4
//        System.out.println(solution(s));
//        s = "-1 -2 -3 -4";  // -4 -1
//        System.out.println(solution(s));
//        s = "-1 -1";  //  -1 -1
//        System.out.println(solution(s));
//       
//    }
    // 문자열 s : 공백으로 구분된 숫자
    
//    public static String solution(String s) {
//        String answer = "";
//        
//        String[] str = s.split(" ");
//        int[] number = new int[str.length];
//        
//        int min = Integer.MAX_VALUE;
//        int max = Integer.MIN_VALUE;
//        
//        for (int i = 0; i < number.length; i++) {
//            int temp = Integer.parseInt(str[i]);
//            min = Math.min(min, temp);
//            max = Math.max(max, temp);
//        }
//        
//        answer = Integer.toString(min) + " " + Integer.toString(max);
//        
//        return answer;
//    }
}
