package author.hyun.sik.lim.programmers.kakao21.q2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // test 1 ["AC", "ACDE", "BCFG", "CDE"]
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4};
        
        for (String s : solution(orders, course))
            System.out.println(s);
        System.out.println();
        
//        // test 2 ["ACD", "AD", "ADE", "CD", "XYZ"]
//        orders = new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//        course = new int[]{2,3,5};
//        
//        for (String s : solution(orders, course))
//            System.out.println(s);
//        System.out.println();
//        
//        // test 3 ["WX", "XY"]
//        orders = new String[]{"XYZ", "XWY", "WXA"};
//        course = new int[]{2,3,4};
//        
//        for (String s : solution(orders, course))
//            System.out.println(s);
//        System.out.println();
    }
    
    // 메뉴 리뉴얼
    // 단품만 제공하는 메뉴를 조합하여 코스 요리 형태로 재구성한다
    // 코스요리 메뉴 : 2가지 이상의 단품 메뉴로 구성한다
    // 단, 최소 2명 이상의 손님으로 부터 주문된 단품 메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
    // 각 손님별로 : 가장 많이 함께 주문된 단품 메뉴 조합에 따라 코스요리 메뉴 준비한다
    // 각 손님들이 단품 메뉴 주문 횟수 A, C가 4회일 경우 2개 코스 준비
    // C, D, E 3회일 경우 : 3개 코스
    // 즉 문자열 패턴 검사
    // course(1 ~ 10) : 코스요리 구성하는 단품메뉴 각 갯수
    // 문자 검사 : 각각 패턴 검사 
    // 1) 조합 만들기
    // 2) 단품 메뉴조합을 길이별로 분류, 해당 단품메뉴가 몇번 선택되었는지 체크 (List에 삽입)
    // 3)  List를 돌면서 가장 많이 선택된 메뉴를 기입
    // 4) 오름차순 정렬
    // 참조 : https://gwang920.github.io/algorithm/progreammers-1-72411/
    
    static HashMap<String, Integer> map;
    static int man;
    
    public static String[] solution(String[] orders, int[] course) {
//        char[] alphabet = new char[26];
        //System.out.println((int)'A'); // 65
        
        // 우선순위 큐를 통해 불필요한 과정 최소화, course 활용하여 길이별 조합하고, 경우의 수마다 새롭게 map 생성
        Queue<String> queue = new PriorityQueue<>();
        
        // 몇개의 코스의 조합을 위해 차근차근 순회한다
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            man = 0; 
            
            // 각각의 조합의 갯수를 저장한다
            for (int j = 0; j < orders.length; j++) {
                search(0, "", course[i], 0, orders[j]);
//                System.out.println();
            }
            
            for (String s : map.keySet()) {
                System.out.print(man + "  " + s + "  " );
                System.out.println(map.get(s)) ;
                if ((map.get(s) == man) & (man > 1)) {
                    queue.offer(s);
                }
            }
            System.out.println();
        }
        
        String[] answer = new String[queue.size()];
        int i = 0;
        while(!queue.isEmpty()) {
            answer[i++] = queue.poll();
        }
        
        
        return answer;
    }

    // dfs 적용 : 숫자, 임시문자열, 타겟번호, 인덱스 번호, 알파벳
    private static void search(int count, String string, int target, int index, String alphabet) {
        // TODO Auto-generated method stub
        if (count == target) {
            char[] ch = string.toCharArray();
            Arrays.sort(ch);
            String temp = "";
            
            for (int i = 0; i < ch.length; i++) {
                temp += ch[i];
            }
            map.put(temp,  map.getOrDefault(temp, 0) + 1);
//            System.out.println(temp);
            man = Math.max(man,  map.get(temp));
            return;
        }
        
        for (int i = index; i < alphabet.length(); i++) {
            char ch = alphabet.charAt(i);
            search(count + 1, string + ch, target, i + 1, alphabet);
        }
        
    }

}