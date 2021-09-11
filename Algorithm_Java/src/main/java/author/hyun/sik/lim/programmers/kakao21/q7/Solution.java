package author.hyun.sik.lim.programmers.kakao21.q7;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 44
        System.out.println(solution(new int[] {14, 17, 15, 18, 19, 14, 13, 16, 28, 17}, 
                new int[][] {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}}));

        // 6
        System.out.println(solution(new int[] {5,6,5,3,4}, 
                new int[][] {{2,3}, {1,4}, {2,5}, {1,2}}));

        // 5
        System.out.println(solution(new int[] {5,6,5,1,4}, 
                new int[][] {{2,3}, {1,4}, {2,5}, {1,2}}));
        
        // 2
        System.out.println(solution(new int[] {10, 10, 1, 1}, 
                new int[][] {{3, 2}, {4, 3}, {1, 4}}));
    }
    
    // 매출 하락 최소화

    // 여러개의 팀 단위로 조직을 구성
    // CEO와 직원의 수 포함
    // 노드에 (직원번호, 해당 직원의 하루 평균 매출액) 포함
    // CEO 포함, 모든 직원 : 팀장 or 팀원 직위 부여
    // 노드 1번 : CEO (항상 팀장), 나머지 노드 : 직원(팀장 or 팀원)
    // CEO 밑으로 직원들에게 화살표 칭한다
    // 한 직원 : 최대 2개 팀으로 소속 가능 (팀장이면서 팀원, 팀원)
    // 팀장 : 자식노드 존재, 팀원 : 단일 노드
    // 팀 내에서 최소 1명이상 팀원 필수
    // sales(2~300,000) : 하루 평균 매출액, links(sales의 배열 크기) : 팀장-팀원 관계
    // 정답 : 2^31-1 이하인 자연수

    
    // 참조 : https://countrysides.tistory.com/62
    // ※ 트리 dp문제
    // - dp[노드번호][노드포함여부] = 최적해
    
    // dp 테이블 채우기 2가지
    // 1) 자신이 포함될시 (include == true)
    // - 트리 dp로 접근, 그룹 내에서 최소 한명 포함되어 자식 노드는 포함 시켜도 되고 안시켜도 된다
    // - 자식 노드 번호 k에 대해 k를 포함시킬지 말지 생각하기
    // - 자식노드 포함시(dp[k][1])와 자식노드 포함하지 않을 시(dp[k][0]) 중 더 작은값 결정
//      if (include) {
//      int answer = 0;    
    
//      for (int i = 0; i < tree[now].size(); i++) {
//          int next += tree[now][i];
//          answer += Math.min(solve(next, true), solve(next, false));
//      }
//      
//    }
    
    // 2) 자신이 포함 안될시 (include == false)
    // - 자식노드 k에 대해 dp[k][1]와 dp[k][0] 중 작은 값 포함
    // - 자식노드가 참여 하지 않은 것이 최적해일때 강제로 1명을 참여시켜야함
    // - 자식 노드가 자신이 참여를 했을 대와 안 했을 때의 차이가 가장 적은거 선택 (default 값 : dp[k][1] > dp[k][0])
//    else {
//        int answer = 0;
//        
//        int different = (tree[now].size() > 0) ? Integer.MAX_VALUE : 0;
//        boolean isFlag = false; // 모두가 불참시
//        
//        for (int i = 0; i < tree[now].size(); i++) {
//            int next = tree[now][i];
//            int case1 = solve(next, true);
//            int case2 = solve(next, false);
//
//            //참여 케이스의 값이 작다는 것은, 참여를 했다는 것이다.
//            if(case1 < case2) isFlag = true;
//            else different = Math.min(different, case1 - case2);
//
//            answer += min(case1, case2);
//        }
//    }
//    
//    return ref = (!flag ? answer + dirfferent : answer);
    
    
    private static int[][] dp;
    private static ArrayList<ArrayList<Integer>> tree;
    private static int[] salesTree;
    
    public static int solution(int[] sales, int[][] links) {
        
        // 먼저 초기화 해준다 - dp tree 노드 생성 공식
        salesTree = sales;
        tree = new ArrayList<>();
        dp = new int[sales.length+1][2];
        
        for(int i = 0; i <= sales.length; i++)
            tree.add(new ArrayList<Integer>());
        
        for(int i = 0; i < links.length; i++)
            tree.get(links[i][0]).add(links[i][1]);
        
        for(int i = 0; i <= sales.length; i++)
            dp[i][0] = dp[i][1] = -1;
        
        return Math.min(dpTree(1, 1), dpTree(1, 0));
    }

    private static int dpTree(int now, int include) {
        // TODO Auto-generated method stub
        
        if (dp[now][include] != -1)
            return dp[now][include];
        
        
        dp[now][include] = 0;
        int answer = 0;
        
        // 1) 자신 포함
        if (include == 1) {
            for (int i = 0; i < tree.get(now).size(); i++) {
                int next = tree.get(now).get(i);
                answer += Math.min(dpTree(next, 1), dpTree(next, 0));
                
            }
            return dp[now][include] = (answer + salesTree[now - 1]);
            
        } else { // 2) 자신 포함 안함
            boolean isFlag = false; // 모두가 불참시
            int different = (tree.get(now).size() > 0) ? Integer.MAX_VALUE : 0;
                    
            for (int i = 0; i < tree.get(now).size(); i++) {
                int next = tree.get(now).get(i);
                int case1 = dpTree(next, 1);
                int case2 = dpTree(next, 0);
      
                //참여 케이스의 값이 작다는 것은, 참여를 했다는 것이다.
                if(case1 < case2) isFlag = true;
                else different = Math.min(different, case1 - case2);
      
                answer += Math.min(case1, case2);
            }
            
            return dp[now][include] = (isFlag) ? answer : answer + different;
        }
        
    }
}
