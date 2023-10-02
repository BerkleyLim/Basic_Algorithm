package author.hyun.sik.lim.Ktest;

import java.util.Scanner;

public class Q5 {
  public static void main(String[] args) {
    int[] numbers= {1,8,5,3,7,2,7,9,1,3};
    
    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt();
    int B = sc.nextInt();
    int C = sc.nextInt();
    
    int number = 1;
    for (int i = A; i < B; i++) {
      if (number == C) {
        System.out.println(numbers[i]);
        break;
      }
      number++;
    }
    sc.close();
  }
}
