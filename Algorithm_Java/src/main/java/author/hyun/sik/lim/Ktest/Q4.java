package author.hyun.sik.lim.Ktest;

public class Q4 {
  public static void main(String[] args) {
    String[] string = {"2", "6", "10"};
    
    
    int answer = 0;
    for (int i = 0; i < string.length; i++) {
      String temp = string[i];
      
//      System.out.println((i + 1) % string.length);
      
      String A = temp + string[(i+1)%string.length] + string[(i+2)%string.length];
      String B = temp + string[(i+2)%string.length] + string[(i+1)%string.length];
      if (Integer.parseInt(A) >= Integer.parseInt(B))
        temp = A;
      else
        temp = B;
      
      answer = Math.max(answer, Integer.parseInt(temp));
    }
    System.out.println(answer);
  }
  
}
