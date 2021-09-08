package author.hyun.sik.lim.programmers.skill.check.level3;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println();
    }
    
    // 1번 - 리틀 프렌즈 사천성
    // 2차원 배열로 구성된 게임으로 진행,
    // 같은 모양의 타일은 두 개씩 배치 되어, 모든 타일을 제거한다
    // 같은 모양의 타일을 규칙에 따라 제거해야 한다
    // 게임판의 크기 : m * n (1 ~ 100)
    // board 구성요소
    // '.' : 빈칸
    // '*' : 막힌 칸
    // 대문자 (A ~ Z) : 타일(각 알파벳마다 2개씩 구성)
    // 
    static char[][] boards;
//    static int m;
//    static int n;
    static LinkedList<Character> list = new LinkedList<>();
    static HashMap<Character, int[][]> map = new HashMap<>();
    
    public static String solution(int m, int n, String[] board) {
        String answer = "";
        
        boards = new char[m][n];
        
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                char ch = board[x].charAt(y);
                boards[x][y] = ch;
                
                if (ch != '.' && ch != '*') {
                    if (!list.contains(ch)) {
                        list.add(ch);
                        map.put(ch,  new int[2][2]);
                        map.get(ch)[0][0] = x;
                        map.get(ch)[0][1] = y;
                    } else {
                        map.get(ch)[1][0] = x;
                        map.get(ch)[1][1] = y;
                    }
                }
            }
        }
        
        Collections.sort(list);
        
        int index = 0;
        while (list.size() != 0) {
            if(isDelete(list.get(index))) {
                char popped = list.remove(index);
                answer += popped;
                takeOff(popped);
                index = 0;
            } else {
                index++;
                if (index == list.size()) {
                    return "IMPOSSIBLE";
                }
            }
        }
        
        return answer;
    }

    // 제거
    private static void takeOff(char popped) {
        // TODO Auto-generated method stub
        boards[map.get(popped)[0][0]][map.get(popped)[0][1]] = '.';
        boards[map.get(popped)[1][0]][map.get(popped)[1][1]] = '.';
    }

    // 제거 여부 검사
    private static boolean isDelete(char ch) {
        // TODO Auto-generated method stub
        int row1 = map.get(ch)[0][0];
        int column1 = map.get(ch)[0][1];
        int row2 = map.get(ch)[1][0];
        int column2 = map.get(ch)[1][1];
        
        if(column1 < column2){
            if(linearColumnCheck(column1, column2, row1, ch) && linearRowCheck(row1, row2, column2, ch)){
                return true;
            }
            if(linearRowCheck(row1, row2, column1, ch) && linearColumnCheck(column1, column2, row2, ch)){
                return true;
                }
        }else{
            if(linearRowCheck(row1, row2, column2, ch) && linearColumnCheck(column2, column1, row1, ch)){
                return true;
            }
            if(linearColumnCheck(column2, column1, row2, ch) && linearRowCheck(row1, row2, column1, ch)){
                return true;
            }
        }
        
        return false;
    }

    private static boolean linearColumnCheck(int column1, int column2, int row, char ch) {
        // TODO Auto-generated method stub
        for(int y = column1; y < column2 + 1; y++){
            if(boards[row][y] != '.' && boards[row][y] != ch)
                return false;
        }
        return true;
    }

    private static boolean linearRowCheck(int row1, int row2, int column, char ch) {
        // TODO Auto-generated method stub
        for(int x = row1; x < row2 + 1; x++){
            if(boards[x][column] != '.' && boards[x][column] != ch)
                return false;
        }
        return true;
    }

}
