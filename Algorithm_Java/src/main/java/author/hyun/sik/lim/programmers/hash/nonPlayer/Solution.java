package author.hyun.sik.lim.programmers.hash.nonPlayer;

import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /*
         [leo, kiki, eden]    [eden, kiki]    leo
         [marina, josipa, nikola, vinko, filipa] [josipa, filipa, marina, nikola]    vinko
         [mislav, stanko, mislav, ana]   [stanko, ana, mislav]   
         * */
        
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participant, completion));
    }
    
    // 동명이인까지 고려하기!
    // 선수 : 최대 100,000명 이하
    // 완주인 : 1명
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> nonPlayer = new HashMap<>();
        
        for (String name : participant) {
            nonPlayer.put(name, nonPlayer.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) {
            nonPlayer.put(name, nonPlayer.get(name) - 1);
        }
        
        for (String name : nonPlayer.keySet()) {
            if (nonPlayer.get(name) != 0) {
                answer = name;
                break;
            }
            
        }
        
        return answer;
    }

}
