package author.hyun.sik.lim.programmers.kakao22.q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // [2,1,1,0]
        for (int i : solution(new String[] {"muzi", "frodo", "apeach", "neo"},
                                 new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2))
            System.out.print(i + " ");
        System.out.println();
        
        // [0,0]
        for (int i : solution(new String[] {"con", "ryan"},
                                 new String[] {"ryan con", "ryan con", "ryan con", "ryan con"}, 3))
            System.out.print(i + " ");
        System.out.println();
        
    }

    // 게시판 불량 이용자 신고
    // 각 유저 1 per 1man 신고
    //동일 유저 신고 
    // K번 이상 신고된 유저 : 즉시 게시판 이용 정지
    // 신고한 유저 : 정지 사실 메일 받음
    
    // id_list(2 ~ 1000) : 이용자의 ID 담긴 배열
    // report(1 ~ 200000) : 신고한 이용자의 id 정보
    // k(1 ~ 200) : 정지 기준 
    static Set<String> set;
    public static int[] solution(String[] id_list, String[] report, int k) {
        set = new HashSet<>();
        Map<String, ArrayList<String>> id = new HashMap<>();
        Map<String, Integer> index = new HashMap<>();
        Map<String, Integer> reportMap = new HashMap<>();
        
        for (int i = 0; i < id_list.length; i++) {
            index.put(id_list[i], i);
            id.put(id_list[i], new ArrayList<>());
            reportMap.put(id_list[i], 0);
        }
        
        for (int i = 0; i < report.length; i++) {
            set.add(report[i]);
        }
        
        Iterator<String> iterator = set.iterator();
//        System.out.println(iterator.toString());
        
        while (iterator.hasNext()) {
            // System.out.println(iterator.next());
            String str = iterator.next();
            String[] split = str.split(" ");
            
            ArrayList<String> al = new ArrayList<>();
//            System.out.println(split[0]);
            al = id.get(split[0]);
            al.add(split[1]);
            id.put(split[0], al);
            System.out.println(id);
            
            
            reportMap.put(split[1], al);
            
//            System.out.println(reportMap.get(split[1]));
            
            
        }
        
        int[] answer = new int[id.size()];
        
//        System.out.println(map.size());
        for (int i = 0; i < id.size(); i++) {
//            System.out.println(id.get(id_list[i]).size());
//            System.out.println(reportmap.get(id_list[i]).size());
            
//            System.out.println(reportMap.get(id_list[i]));
            System.out.println(id.get(id_list[i]));
            // 제거
            if (reportMap.get(id_list[i]) < k) {
                Set s = reportMap.keySet();
                
                for (int j = 0; j < al.size(); j++) {
                    //id.get(id_list[i]).remove(al);
                    System.out.println(id.get(id_list[i]).size());
                }
                
            } 
            
            answer[index.get(id_list[i])] = id.get(id_list[i]).size();
            
            
        }
        return answer;
        
    }
}

