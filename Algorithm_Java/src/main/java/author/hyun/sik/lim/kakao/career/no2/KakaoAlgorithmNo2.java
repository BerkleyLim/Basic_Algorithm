package author.hyun.sik.lim.kakao.career.no2;

import java.util.Scanner;

/*
 * http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
 * 
 * 2. 다트 게임(난이도: 하)
 카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다. 
 다트 게임은 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임으로, 모두가 간단히 즐길 수 있다.
갓 입사한 무지는 코딩 실력을 인정받아 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다. 
다트 게임의 점수 계산 로직은 아래와 같다.

1. 다트 게임은 총 3번의 기회로 구성된다.
2. 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
3. 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱 (점수^1 , 점수^2 , 점수^3 )으로 계산된다.
4. 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스된다.
5. 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
6. 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
7. 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
8. Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
9. 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.

 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.
 
 - 입력 형식
“점수|보너스|[옵션]”으로 이루어진 문자열 3세트.
예) 1S2D*3T

점수는 0에서 10 사이의 정수이다.
보너스는 S, D, T 중 하나이다.
옵선은 *이나 # 중 하나이며, 없을 수도 있다.


 - 출력 형식
3번의 기회에서 얻은 점수 합계에 해당하는 정수값을 출력한다.
예) 37


- 입출력 예제

예제  dartResult  answer  설명
1   1S2D*3T     37      1^1 * 2 + 2^2 * 2 + 3^3
2   1D2S#10S    9       1^2 + 2^1 * (-1) + 10^1
3   1D2S0T      3       1^2 + 2^1 + 0^3
4   1S*2T*3S    23      1^1 * 2 * 2 + 2^3 * 2 + 3^1
5   1D#2S*3S    5       1^2 * (-1) * 2 + 2^1 * 2 + 3^1
6   1T2D3D#     -4      1^3 + 2^2 + 3^2 * (-1)
7   1D2S3T*     59      1^2 + 2^1 * 2 + 3^3 * 2

 * */

public class KakaoAlgorithmNo2 {
    private int result = 0;
    
    // 토큰 식별기 검사
    private int tokenTest(char token, int scoreNumber) {
        
        switch(token) {
        case 'S':
            return (int) Math.pow(scoreNumber, 1);
        case 'D':
            return (int) Math.pow(scoreNumber, 2);
        case 'T':
            return (int) Math.pow(scoreNumber, 3);
        default:
            return -1;
        }
    }
    
    // 옵션여부 검사
    private void option(char token, int x, int[] scoreNumber) {
        switch(token) {
        case '*':
            scoreNumber[x] *= 2;
            if(x != 0) {
                scoreNumber[x-1] *= 2;
            }
            break;
        case '#':
            scoreNumber[x] *= (-1);
            break;
        default:
            break;
        }
    }
    
    // 변수 설정
    public void setVariable(String score) {
        System.out.println(score);
        char token[] = score.toCharArray();
        
        // 여기는 임시 점수 저장
        int[] scoreNumber = new int[3];
        int x = 0;
        
        for (int i = 0; i < score.length(); i++) {
            if (token[i] == 'S' || token[i] == 'D' || token[i] == 'T') {
                // 여기서는 'i-1' 점수,
                // 'i+1'이 점수가 아닐경우 옵션으로 간주 (단, 점수가 10점일시 i-2까지 포함)
                if (token[i-1] == '0' && token[i-2] == '1') {
                    scoreNumber[x] = tokenTest(token[i], 10);
                } else {
                    scoreNumber[x] = tokenTest(token[i], (int)token[i-1] - 48);
                }
                
                // 옵션여부 보기
                if(score.length() > i+1) {
                    option(token[i+1], x, scoreNumber);
                }
                
                x++;
            }
        }
        
        for (int i = 0; i < x; i++) {
            result += scoreNumber[i];
        }
        
    }
    
    // 결과 값 리턴
    public int Result() {
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        
        // 알고리즘 시작
        for (int testCase = 1; testCase <= T; testCase++) {
            KakaoAlgorithmNo2 test = new KakaoAlgorithmNo2();
            
            String score = sc.nextLine();
            test.setVariable(score.toUpperCase());
            
            // 결과 값 출력!
            System.out.print("#" + testCase + " : ");
            System.out.println(test.Result());
        }
        
    }
}
