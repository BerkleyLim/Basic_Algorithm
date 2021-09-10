package author.hyun.sik.lim.programmers.kakao21.q6;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        
        // 14
        System.out.println(solution(new int[][] {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0));
        
        // 16
        System.out.println(solution(new int[][] {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1));
        
    }

    
    // 카드 짝 맞추기
    // 카드 2장 선책하여 앞면으로 뒤집으면 같은 그림이 그려진 카드면 해당 카드는 화면에서 사라진다
    // 같은 그림이 아니면 원래 상태로 뒷면이 보여야 함
    // 모든 카드 화면에서 사라지면 게임 종료
    // 커서 이동 (ctrl로 이동하여 가장 가까운 카드로 한번에 사방으로 이동 가능)
    // 카드 뒤집기 (Enter 누른다)
    // 남은 카드 모두 제거하는데 필요한 키 조작 횟수의 최솟값?
    // board : 4 * 4 배열
    // r : 세로(행) 위치, c : 가로(열) 위치
    
    // 문제 의도 : bfs, 순열/조합(dfs), 완전 탐색
    private static final int[] dx = {0,1,0,-1};
    private static final int[] dy = {1,0,-1,0};
    
    private static Queue<String> queue;
    private static class XY {
        int x;
        int y;
        int move;
        
        public XY(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public String toString() {
            return "XY [x=" + x + ", y=" + y + ", move=" + move + "]";
        }
    }
    
    public static int solution(int[][] board, int r, int c) {
        // 1) 카드 갯수 구하기
        int cardCount = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] > 0) {
                    cardCount++;
                }
            }
        }
        cardCount /= 2;
        
        int[] card = new int[cardCount];
        for (int i = 0; i < cardCount; i++) {
            card[i] = i + 1;
        }
        
        // 2) 카드 순서 모든 경우의 수 조합
        queue = new LinkedList<>(); 
        permutation("",0,card);
//        System.out.println(queue.size());
        // 3) 최솟값 구하기 - bfs 활용하여 경우의 수 구하기
        int answer = Integer.MAX_VALUE;
        
        // 경우의 수 탐험
        while (!queue.isEmpty()) {
            // 완전 탐색모드, 순차적으로 최솟값 구하기
            String str = queue.poll();
//            System.out.println(str.toString());
            String[] comb = str.split("");
            
            // 움직인 횟수
            int moveCount = 0;
            
            // 시작점 초기화
            int[] position= new int[] {r,c};
            
            // 원본을 사본으로 이용하여 구한다
            int[][] boardCopy = new int[board.length][board.length];
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    boardCopy[x][y] = board[x][y];
                }
            }
            
            // 각 같은 카드 조합별로 순차적을로 찾기 진행
            for (String target : comb) {
                int number = Integer.parseInt(target);
                
                // 첫번째 카드 찾기 : 해당 카드를 먼저 찾을것
                moveCount += bfs(position, number, boardCopy);
                boardCopy[position[0]][position[1]] = 0;
                
                // enter 
                moveCount += 1;
                
                // 두번째 카드 찾기 : 다음 카드를 기준점으로 이동
                moveCount += bfs(position, number, boardCopy);
                boardCopy[position[0]][position[1]] = 0;
                
                // enter 
                moveCount += 1;
            }
            
            answer = Math.min(moveCount, answer);
        }
        return answer;
    }
    
    // 2) 순열, 조합 알고리즘 (dfs 방식)
    private static void permutation(String combination, int depth, int[] card) {
        // TODO Auto-generated method stub
//        System.out.println(combination);
        if (card.length == depth) {
            queue.offer(combination);
            return;
        } 
        for (int i = 0; i < card.length; i++) {
            if(!combination.contains("" + card[i])) {
                permutation(combination + card[i], depth + 1, card);
            }
        }
    
        
    }
    
    // 3) 카드 찾으러 이동하기  (bfs 방식)
    private static int bfs(int[] position, int target, int[][] boardCopy) {
        Queue<XY> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        int x= position[0];
        int y= position[1];
        
        visited[x][y] = true;
        q.add(new XY(x,y,0));
        
        while(!q.isEmpty()) {
            XY next = q.poll();
            int px = next.x;
            int py = next.y;
            int move = next.move;
            
            if(boardCopy[next.x][next.y] == target) {
//                System.out.println("["+target+ "] find! "+ next.x+","+next.y+ ":"+ move);
                position[0] = next.x;
                position[1] = next.y;
                return move;
            }
            
            //상하좌우 1칸 direction 
            for(int i=0; i<4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];
                
                if(nx<0 || ny<0 || nx>3 || ny>3) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(new XY(nx,ny, move+1));
            }
            
            // ctrl+상하좌우 direction
            for(int i=0; i<4; i++) {
                XY res = isCtrlRoute(px,py, i, boardCopy);
                int nx = res.x, ny =res.y;
                
                if(nx==x && ny ==y) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(new XY(nx,ny, move+1));
            }
        }
        return 0;
        
        
        
    }

    private static XY isCtrlRoute(int x, int y, int direction, int[][] boardCopy) {
        // TODO Auto-generated method stub
        x += dx[direction];
        y += dy[direction];
        
        while(x >= 0 && x < 4 && y >= 0 && y < 4) {
            if(boardCopy[x][y] != 0) return new XY(x,y,0);
            
            x += dx[direction];
            y += dy[direction];
        }
        
        // 카드 없으면 끝 
        return new XY(x-dx[direction],y-dy[direction],0); 
    }
}
