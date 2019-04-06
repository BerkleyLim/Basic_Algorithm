package author.hyun.sik.lim.backjoon.backjoon14499;

import java.util.Scanner;

public class diceEx {
    private static int[] dice = {0, 1, 2, 3, 4, 5};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 초기 주사위 값
        for (int dices : dice) {
            System.out.print(dices + "  ");
        }
        System.out.println();
        
        
        int temp;
        while (true) {
            switch(sc.nextInt()) {
            // 동쪽일때 
            case 1:
                temp = dice[2];
                dice[2] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                
                break;
                
                // 서쪽일때
            case 2:
                temp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                
                    
                break;
                
                // 북쪽일때
            case 3:
                temp = dice[2];
                dice[2] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[0];
                dice[0] = temp;
                
                break;
                
                // 남쪽일때
            case 4:
                temp = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                    
                break;
            default:
                break;
            }
            
            for (int dices : dice) {
                System.out.print(dices + "  ");
            }
            System.out.println();
            
            
        }
    }
}
