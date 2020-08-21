package author.hyun.sik.lim.programmers.hash.hide;

import java.util.HashMap;
import java.util.Set;

// 위장 문제
public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[][] clothes = 
            {{"yellow_hat", "headgear"}, 
             {"blue_sunglasses", "eyewear"}, 
             {"green_turban", "headgear"}};  //5
        
        System.out.println(solution(clothes));
        
        clothes 
        = new String[][]
                {{"crow_mask", "face"},
                 {"blue_sunglasses", "face"},
                 {"smoky_makeup", "face"}};  //3
        System.out.println(solution(clothes));
    }
    
    // 위장 : 스파이는 매일 다른 옷으로 조합하여 입는다.
//    종류   이름
//    얼굴  동그란 안경, 검정 선글라스
//    상의  파란색 티셔츠
//    하의  청바지
//    겉옷  긴 코트
    
    // clothes [의상 이름, 의상 종류], 조합 짬뽕
    public static int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> hash = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            // getOrDefault : 키에 해당하는 값을 넣는다. 만일 없을 시 0으로 세팅
            //hash.put(clothes[i][1], hash.getOrDefault(clothes[i][1], 0)+1);
            String key = clothes[i][1];
            hash.put(key, hash.getOrDefault(key, 0) + 1);
        }
        
        Set<String> set = hash.keySet();
        
        for (String s : set)
            answer *= hash.get(s) + 1;
        
        return answer - 1;
    }
    
    
}
