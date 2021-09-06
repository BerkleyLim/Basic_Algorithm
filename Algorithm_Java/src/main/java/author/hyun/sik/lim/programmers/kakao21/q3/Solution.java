package author.hyun.sik.lim.programmers.kakao21.q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] info = {"java backend junior pizza 150",
                         "python frontend senior chicken 210",
                         "python frontend senior chicken 150",
                         "cpp backend senior pizza 260",
                         "java backend junior chicken 80",
                         "python backend senior chicken 50"};
        
        String[] query = {"java and backend and junior and pizza 100",
                          "python and frontend and senior and chicken 200",
                          "cpp and - and senior and pizza 250",
                          "- and backend and senior and - 150",
                          "- and - and - and chicken 100",
                          "- and - and - and - 150"};
        
        for(int i : solution(info, query))
            System.out.println(i);
    }

    // 순위 검색
    // 개발언어 : cpp, java ptyhon 중 하나 선택한다
    // 직군 : backend, frontend
    // 경력 구분 : junior, senior
    // 선호 소울 푸드 : chicken, pizza
    /*
     * 코딩테스트에 java로 참여했으며, backend 직군을 선택했고, 
     * junior 경력이면서, 
     * 소울푸드로 pizza를 선택한 사람 중 코딩테스트 점수를 50점 이상 받은 지원자는 몇 명인가?
     * */
    // 개발팀에서 궁금해 하는 내용
    // [조건]을 만족하는 사람 중 코딩테스트 점수를 x점 이상 받은 사람은 모두 몇명인가?
    
    // 4가지 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성
    // info(1~50,000) : "개발언어 직군 경력 소울푸드 점수" (스페이스로 구분)
    // query(조건)(1~100,000) : "개발언어 and 직군 and 경력 and 소울푸드 and 점수" 형식의 문자열
    //
    // dp문제 (정렬 후 푸는 문제)
    // 이분 탐색 요법으로 풀어보기
    
    // 참조 : https://girawhale.tistory.com/94
    
    
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<String, List<Integer>> information = new HashMap<>();
        
        // info 배열에 먼저 쪼개기
        for (int z = 0; z < info.length; z++) {
            String[] string = info[z].split(" ");
            
            int score = Integer.parseInt(string[4]);
            
            // 비트 연산을 통한 조건
            for (int i = 0; i < (1 << 4); i++) {
                String key = new String();
                //System.out.println(i);
                for (int x = 0; x < 4; x++) {
                    if ((i & (1 << x)) > 0)
                        key += string[x];
                    //System.out.println((i & (1 << x)));
                }
                information.computeIfAbsent(key, s -> new ArrayList<>()).add(score);
            }
            //System.out.println();
        }
        
        List<Integer> empty = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : information.entrySet())
            entry.getValue().sort(null);

        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].replaceAll("-", "").split(" and | ");
            String key = String.join("", split[0], split[1], split[2], split[3]);
            int score = Integer.parseInt(split[4]);
           
            List<Integer> list = information.getOrDefault(key, empty);

            int s = 0, e = list.size();

            while (s < e) {
                int mid = (s + e) / 2;

                if (list.get(mid) < score) s = mid + 1;
                else e = mid;
            }

            answer[i] = list.size() - s;
        }
        
        
        return answer;
    }


}
