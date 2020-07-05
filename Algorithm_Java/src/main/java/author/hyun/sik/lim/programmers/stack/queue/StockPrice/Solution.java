package author.hyun.sik.lim.programmers.stack.queue.StockPrice;

import java.util.LinkedList;
import java.util.Queue;

// 문제 출처 : https://programmers.co.kr/learn/courses/30/lessons/42584

// 주식 가격 문제
// 초 단위로 기록된 주식 가격이 담긴 배열 prices가 매개변수로 주어짐

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] prices = {1,2,3,2,3};
        
        int[] result = solution(prices);
        for (int value : result)
            System.out.println(value);
    }
    
    // 배열 prices 범위 지정
    // price 길이 : 100,000
    // prices 가격 : 10,000
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] > prices[j]) {
                    answer[i] = j-i;
                    break;
                }
                
                if (j == answer.length - 1) 
                    answer[i] = j-i;
            }
        }
        
        return answer;
    }
}
