package author.hyun.sik.lim.sw.expert.academy.Q1242;

import java.util.Scanner;

// Q1240 문제와 동일
// 암호코드 가로 길이와 세로길이가 증가
// 그림에서 각 숫자에서 흰색과 파란식은 넓이의 비로 표현한다
// 암호코드 하나의 최소 가로 길이 : 56, 선이 굵을 수록 56의 배수의 길이를 가짐
// 암호코드 숫자 하나가 14칸을 사용히 암호코드 하나의 가로길이 : 112
// 입력시 배열은 16진수!
// 16진수를 2진수로 변환!
// 총 소요시간 적은 것을 우선!

class Solution {
    static final int[][] hexaToBinary = {
            { 0, 0, 0, 0 }, //0
            { 0, 0, 0, 1 }, //1
            { 0, 0, 1, 0 }, //2
            { 0, 0, 1, 1 }, //3
            { 0, 1, 0, 0 }, //4
            { 0, 1, 0, 1 }, //5
            { 0, 1, 1, 0 }, //6
            { 0, 1, 1, 1 }, //7
            { 1, 0, 0, 0 }, //8
            { 1, 0, 0, 1 }, //9
            { 1, 0, 1, 0 }, //A
            { 1, 0, 1, 1 }, //B
            { 1, 1, 0, 0 }, //C
            { 1, 1, 0, 1 }, //D
            { 1, 1, 1, 0 }, //E
            { 1, 1, 1, 1 }  //F
    };
    
    static String[] code;
    static int[] hashTableNumber; // 암호문을 0 ~ 9까지 나타내주는 지표!
    // 0 - 3 : 2 : 1 : 1
    // 1 - 2 : 2 : 2 : 1
    // 2 - 2 : 1 : 2 : 2
    // 3 - 1 : 4 : 1 : 1
    // 4 - 1 : 1 : 3 : 2
    // 5 - 1 : 2 : 3 : 1
    // 6 - 1 : 1 : 1 : 4
    // 7 - 1 : 3 : 1 : 2
    // 8 - 1 : 2 : 1 : 3
    // 9 - 3 : 1 : 1 : 2
    static char[][] array;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        hashTableNumber = new int[7000];
        // 암호문 해쉬값 저장
        hashTableNumber[3211] = 0;
        hashTableNumber[2221] = 1;
        hashTableNumber[2122] = 2;
        hashTableNumber[1411] = 3;
        hashTableNumber[1132] = 4;
        hashTableNumber[1231] = 5;
        hashTableNumber[1114] = 6;
        hashTableNumber[1312] = 7;
        hashTableNumber[1213] = 8;
        hashTableNumber[3112] = 9;
        
//        System.out.println((int)'A' - 55);
//        System.out.println((int)'0' - 48);
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();   // 세로 크기  1 ~ 2000
            int M = sc.nextInt();   // 가로 크기  1 ~ 500
            sc.nextLine();
            
            // 여기서 16진수를 2진수로 저장하기 위해 배열 너비 입력을 *4를 한것이다
            array = new char[N][M * 4];
            code = new String[N];
            
            // 입력 받기
            for (int y = 0; y < N; y++) {
                code[y] = "";
                String str = sc.nextLine();
                
                for (int x = 0; x < M; x++) {
                    String temp= str.split("")[x];
                    char ch = temp.charAt(0);
//                    System.out.println(ch);
                    
                    if(ch >= 'A' && ch <= 'F') {
                        for (int i = 0; i < 4; i++) {
                            code[y] += Integer.toString(hexaToBinary[ch - 'A' + 10][i]);
                        }
                    } else {
                        for (int i = 0; i < 4; i++) {
                            code[y] += Integer.toString(hexaToBinary[ch - '0'][i]);
                        }
                    }
                }
                array[y] = code[y].toCharArray();
                
            }
            
            // 여기서 답을 찾는다.
            solve(test_case, N, M);
        }
    }
    
    // 여기는 56의 길이를 배수로 곱하기 위해!
    public static int calcScale(int M, int N) {
        int count = 0;
        
        for (int y = N; y > 0; y--) {
            if (array[M][y] != array[M][y - 1]) ++count;
            if (count == 4) return (N - y + 1) / 7;
        }
        return 1;
    }
    
    public static int parse(int y, int x, int scale, int N, int M) {
        int key = 0; 
        int count = 1;
        
        for (int i = 0; i < 7 * scale; i++) {
            if (x + i < M - 1 && array[y][x + i ] == array[y][x + i + 1]) {
                count++;
            } else {
                count /= scale;
                key = key * 10 + count;
                count = 1;
            }
        }
        
        return hashTableNumber[key];
    }
    
    // 여기서 패스워드 코드 찾기
    public static void solve(int test_case, int N, int M) {
        // 패스워드 코드 
        int[] password = new int[8];
        int answer = 0;
        
        for (int y = 0; y < N; y++) {
            
            for (int x = M - 1; x >= 55; x--) {
                if (array[y][x] == '1') {
                    // 배수 범위 지정!
                    int scale = calcScale(x,y);
                    int startCol = x - (56 * scale) + 1;
                    boolean flag = true;
                    
                    for (int k = 0; k < 8; k++) {
                        password[k] = parse(y, startCol + k * 7 * scale, scale, N, M);
                        if (password[k] < 0) {
                            flag = false;
                            break;
                        }
                    }
                    
                    
                    if(flag) {
                        for (int a = y + 1; a < M; a++) {
                            for (int b = startCol; b <= x; b++) {
                                if(array[y][b] == array[a][b]) {
                                    array[a][b] = 0;
                                } else {
                                    a = M;
                                    break;
                                }
                            }
                        }
                        
                        
                        // 답 계산
                        int sum = (password[0] + password[2] + password[4] + password[6]) * 3
                                + password[1] + password[3] + password[5] + password[7];
                        
                        if ((sum % 10) == 0 && sum <=10) {
                            for (int i = 0; i < 8; i++) {
                                answer += password[i];
                            }
                            x = startCol - 1;
                        }
                    }
                }
            }
        }
        
        System.out.println("#" + test_case + " " + answer);
    }
}