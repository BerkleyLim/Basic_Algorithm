package author.hyun.sik.lim.backjoon.Q15686;

import java.util.ArrayList;
import java.util.Scanner;

// 치킨 배달 게임
// 도시 크기는 N * N 이 주어진다.
// 이때, 각 칸에서는 0 : 빈칸, 1 : 집, 2 : 치킨집
// 여기서 M개의 치킨집만 유지하고 나머지 폐쇄
// M개의 치킨집을 구할때 각각 집의 거리가 최소가 되는 치킨집 장소만 구할것

// 입력
// 첫째줄 N, M
// 둘째줄 부터 : N개의 도시 크기에 해당하는 값 입력

// 참고 : https://lmcoa15.tistory.com/72
public class Solution {
    static class Location {
        int x, y;
        Location (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int M;
    
    
    // 문제 풀이 : DFS, 순열(C++만 가능)
    // 필자는 DFS로
    
    static int minDistance;
    static ArrayList<Location> house;   // 집 좌표
    static ArrayList<Location> chicken; // 치킨집 좌표
    static ArrayList<Integer> open;     // 치킨 집 폐업 유무
    
    static int houseCount; // 집갯수
    static int chickenCount;    // 치킨잡 갯수
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        open = new ArrayList<>();
        houseCount = 0;
        chickenCount = 0;
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                // 도시 정보
                int cityInfo = sc.nextInt();
                
                if (cityInfo == 1) {
                    house.add(new Location(x,y));
                } else if (cityInfo == 2) {
                    chicken.add(new Location(x,y));
                    open.add(0); // 모두 폐업으로 처리
                } else {
                    continue;
                }
            }
        }
        
        houseCount = house.size();
        chickenCount = chicken.size();
        
        dfs(0,0);
        
        System.out.println(minDistance);
        
    }
    
    private static void dfs(int pos, int count) {
        // TODO Auto-generated method stub
        
        // 최소 M개 만큼 열었을 때,
        if (count == M) {
            solve();
            return;
        }
        
        // 확인해야 할 치킨집 범위
        if (pos == chickenCount)
            return;
        
        open.set(pos, open.get(pos) + 1);
        dfs(pos + 1, count + 1);
        
        // 폐업
        open.set(pos, open.get(pos) - 1);
        dfs(pos + 1, count);
        
    }

    private static void solve() {
        // TODO Auto-generated method stub
        int[] distance = new int[houseCount];
        
        for (int i = 0; i < houseCount; i++) {
            distance[i] = 100;
            
            int x = house.get(i).x;
            int y = house.get(i).y;
            
            for (int j = 0; j < chickenCount; j++) {
                if (open.get(j) == 0)
                    continue;
                
                int nx = chicken.get(j).x;
                int ny = chicken.get(j).y;
                distance[i] = Math.min(distance[i], Math.abs(x - nx) + Math.abs(y - ny));
            }
        }
        
        int sum = 0;
        
        for (int i = 0; i < houseCount; i++) {
            sum += distance[i];
        }
        
        if (minDistance == 0) minDistance = sum;
        else minDistance = Math.min(minDistance, sum);
    }

}
