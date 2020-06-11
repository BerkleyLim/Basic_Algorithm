package author.hyun.sik.lim.programmers.skill.check.level1;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++)
            arr[i] = sc.nextInt();
        
        Q1 q1 = new Q1();
        int[] answer = q1.solution(arr);
        
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
    


}
