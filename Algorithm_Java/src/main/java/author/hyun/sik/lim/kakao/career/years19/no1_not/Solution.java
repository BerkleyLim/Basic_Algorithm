package author.hyun.sik.lim.kakao.career.years19.no1_not;

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

public class Solution {
    static String input;
    static int size;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        logic();
    }
    
    private static void logic() {
        // TODO Auto-generated method stub
        // 문자열 자르기 패턴 (문자열 크기의 절반 이상은 검사할 이유 없으니 절반 이하만 검사)
        searching();
        System.out.println(size);
            
    }
    
    private static void searching() {
        // TODO Auto-generated method stub
        int minSize = size;
        for (int tokenSize = 1; tokenSize <= size/2; tokenSize++) {
            int count = 1;
            String str = "";

            int j = tokenSize;
            String[] arr = new String[size];
            int arrLength = 0;
            
            arr[0] = input.substring(0,1);
            while(j <= size) {
                String token = input.substring(j-tokenSize,j);
                if (j + tokenSize >= size) {
                    if (count > 1) {
                        arr[arrLength] = Integer.toString(count) + token;
                    } 
                    if (arr[arrLength] != null)
                        arrLength++;
                    arr[arrLength++] = input.substring(j,size);
                    j++;
                    break;
                }
                
                if (input.substring(j-tokenSize,j).equals(input.substring(j,j+tokenSize))) {
                    count++;
                    arr[arrLength] = Integer.toString(count) + token;
                    j += tokenSize;
                } else {
                    count = 1;
                    if (arr[arrLength] != null)
                        arrLength++;
                    arr[arrLength] = input.substring(j,j+1);
                    j++;
                }
            }
            
            for (int i = 0; i < arrLength; i++) {
                str += arr[i];
            }
            System.out.println(str);
            minSize = Math.min(minSize, str.length());
        }
        size = minSize;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        input = st.nextToken();
        size = input.length();
        
        br.close();
    }

}
