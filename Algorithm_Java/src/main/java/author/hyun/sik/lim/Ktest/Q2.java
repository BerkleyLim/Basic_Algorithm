package author.hyun.sik.lim.Ktest;

import java.util.Scanner;

public class Q2 {
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    String string = sc.nextLine();
    
    String answer = "";
    for (int i = 0; i < string.length(); i += 2) {
      answer += string.substring(i,i+1);
      
    }
    System.out.println(answer);
  }
}
