package author.hyun.sik.lim;

import java.util.Stack;

public class Solution2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // 1번 솔루션
//        String[] str = {"Jane","Kim"};
//        System.out.println(solution(str));
        
        // 2번 솔루션
        int[][] board = { {0, 0, 0, 0, 0},
                          {0, 0, 1, 0, 3},
                          {0, 2, 5, 0, 1},
                          {4, 2, 4, 4, 2},
                          {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(solution(board, moves));
        
    }
    
    // 2번 솔루션
    // 죠르디 게임
    // 크레인 인형뽑기 기계를 모바일 게임으로 제작
    // 게임 화면 : 1 * 1 크기의 칸들로 이뤄진 N*N 크기의 정사각 격자
    // 위쪽에 크레인 존재
    // 오른쪽에는 바구니 존재
    // 게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있음
    // 바구니에 같은 모양의 인형 두개가 연속해서 쌓이면 두 인형은 터져서 바구니에 사라진다.
    // 여기서 사라진 인형의 갯수를 구하라!
    public static int solution(int[][] board, int[] moves) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        
        int N = board.length;
        for (int i = 0; i < moves.length; i++) {
            
            // 크레인 이동후 인형뽑기
            for (int y = 0; y < N; y++) {
                if (board[y][moves[i] - 1] > 0) {
                    
                    if (!stack.isEmpty() && stack.peek() == board[y][moves[i]-1]) {
                        stack.pop();
                        count += 2;
                    } else {
                        stack.push(board[y][moves[i] -1]);
                    }
                    board[y][moves[i]-1] = 0;
                    
                    break;
                }
            }
            
        }
        return count;
    }
    
    // 1번 솔루션
    // String형의 배열 seoul의 element 중 "Kim"의 위치 x를 찾아
    // "김서방은 x에 있다" 라는 String을 반환하는 함수,
    // Kim은 오직 한번만 나타냄
//    public static String solution(String[] seoul) {
//        int location = 0;
//        for (int i = 0; i < seoul.length; i++) {
//            if (seoul[i].equals("Kim")) {
//                location = i;
//                break;
//            }
//        }
//        return "김서방은 " + location + "에 있다";
//    }
}
