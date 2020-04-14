package author.hyun.sik.lim.sw.expert.academy.Q1233;

import java.util.Scanner;

// 계산이 가능한지의 대한 여부 확인 문제
// Tree의 중위순회로 사칙연산 구하기

class Solution {
    static String string;
    static String[] node;
    static int size;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            size = sc.nextInt();
            sc.nextLine();
            
            string = "";
            node = new String[size + 1];
            
            for (int i = 1; i <= size; i++) {
                String str = sc.nextLine();
                node[i] = str.split(" ")[1];
            }
            
            inOrder(1);
//            System.out.println(test_case + " " + string);
            calculation(string, test_case);
//            System.out.println("#" + test_case + " " + answer);
        }
    }
    
 // 중위 순회 알고리즘
    public static void inOrder(int index) {
        if(isValid(index)) {/*지정한 크기가 더 클때*/
            inOrder(index * 2);  // 왼쪽 먼저 순회
            //System.out.print(node[index]);// visit = true;
            string += node[index];
            inOrder((index * 2) + 1);  // 오른쪽 순회
        }
    }
    
    public static boolean isValid (int index) {
        if (index > size)
            return false;
        return true;
    }
    
    public static void calculation(String string, int test_case) {
        int value = Integer.parseInt(string.split("")[0]);
        String[] str = string.split("");
        //System.out.println(str.length);
        try {
            for (int i = 1; i < size; i++) {
                if(str[i].equals("+")) {
                    value = value + Integer.parseInt(str[i+1]);
                } else if(str[i].equals("-")) {
                    value = value - Integer.parseInt(str[i+1]);
                } else if(str[i].equals("/")) {
                    value = value / Integer.parseInt(str[i+1]);
                } else if(str[i].equals("*")) {
                    value = value * Integer.parseInt(str[i+1]);
                } else {
                    
                }
            }
        } catch (Exception e) {
            System.out.println("#" + test_case + " " + 0);
            return;
        }
        System.out.println("#" + test_case + " " + 1);
        return;
    }
}
