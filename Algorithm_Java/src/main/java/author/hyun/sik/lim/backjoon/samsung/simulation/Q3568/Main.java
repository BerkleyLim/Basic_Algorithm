package author.hyun.sik.lim.backjoon.samsung.simulation.Q3568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// iSharp 문제
// C, C++, Java와 다른 언어를 만듬
// 그 언어는 i#
// 배열([]), 참조(&), 포인터(*) 제공
// 배열, 참조, 포인터는 순서에 상관없이 혼합해서 사용 가능
// ex) int&&[]* 가능
// 여러개의 변수를 한줄에 정의 가능
// 공통된 변수형을 제일 먼저 쓰고, 다음엔 각 변수의 이름과 추가적인 변수형 사용가능
// ex) int& a*[]&, b, c*;
// a타입 : int&&[]*, b타입 : int&, c타입 : int&*
// 변수의 오른편에 있는 변수형은 순서를 뒤집어서 왼편에 다음과 같이 가능
// ex) int*& a = int a&*
// 여기서 변수를 한 줄에 하나로 선언 할려고 함
// 각 변수를 오른편에 있는 변수형을 모두 왼쪽으로 옮기고, 한 줄에 하나씩 선언하는 프로그래밍 작성

// 첫째줄 : i# 변수 선언문 주어짐
// 이 선언문에는 변수가 여러개 포함 가능
// 

public class Main {
    static String[] value;
    static int sizeValue;
    static String command;
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        init();
        logic();
    }

    private static void logic() {
        // TODO Auto-generated method stub
        for (int i = 0; i < sizeValue; i++) {
            String token = tokenSearch(i, value[i]);
            
            int size = value[i].length() - token.length() - 1;
            String str = "";
            
            for (int s = 0; s < size; s++) {
                str += value[i].split("")[s];
            }
            
            System.out.println(command + token + " " + str + ";");
        }
    }

    private static String tokenSearch(int x, String str) {
        // TODO Auto-generated method stub
        String token = "";
        
        String[] string = str.split("");
        
        for (int i = string.length - 2; i > 0; i--) {
            //System.out.println(string[i]);
            if("&".equals(string[i])) {
                token += "&";
            } else if("]".equals(string[i])) {
                token += "[]";
                i--;
            } else if("*".equals(string[i])) {
                token += "*";
            } else {
                return token;
            }
        }
        
        return token;
    }

    private static void init() throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        command = st.nextToken();
        
        // 사이즈 구하기
        sizeValue = st.countTokens();
        value = new String[sizeValue];
        
        for (int i = 0; i < sizeValue; i++) {
            value[i] = st.nextToken();
        }
        
        br.close();
    }

}
