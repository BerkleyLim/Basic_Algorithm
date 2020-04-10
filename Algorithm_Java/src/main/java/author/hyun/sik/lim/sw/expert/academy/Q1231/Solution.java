package author.hyun.sik.lim.sw.expert.academy.Q1231;

import java.util.Scanner;

class Solution {
    
    static String[] node;
    static int size;
    static String answer;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            answer = "";
            size = sc.nextInt();
            
            node = new String[size + 1];
            sc.nextLine();
            
            for (int i = 1; i <= size; i++) {
                String str = sc.nextLine();
                node[i] = str.split(" ")[1];
            }
            
            System.out.print("#" + test_case + " ");
            inOrder(1);
            System.out.println(answer);
        }
    }
    
    
    
    // 중위 순회 알고리즘
    public static void inOrder(int index) {
        if(isValid(index)) {/*지정한 크기가 더 클때*/
            inOrder(index * 2);  // 왼쪽 먼저 순회
            //System.out.print(node[index]);// visit = true;
            answer += node[index];
            inOrder((index * 2) + 1);  // 오른쪽 순회
        }
    }
    
    public static boolean isValid (int index) {
        if (index > size)
            return false;
        return true;
    }

}