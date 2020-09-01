package author.hyun.sik.lim.programmers.heap.morespicy;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        
        System.out.println(solution(scoville,K)); // 2
    }
    
    // 목적 : 스코빌 지수 K 이상으로 만든다
    // 스코빌 지수가 가장 낮은 두개의 음식을 특별한 방법으로 섞어 새로운 음식으로 만든다.
    // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    // 모든 음식의 스코빌 지수가 K 이상이 될때까지 반복하여 섞는다.
    // 여기서 섞어야 하는 최소 횟수를 return 할 것!
    // 단 : 모든 음식의 스코빌 지수를 K 이상으로 만들 수없는 경우 -1을 리턴!
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        
        // Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        Queue<Integer> queue = new PriorityQueue<>(); // 내림차순
        
        for (int s : scoville) {
            queue.offer(s);
        }
        
        while (queue.size() > 1) {
//            System.out.println(queue);
            int first = queue.poll(); // 가장 맵지 않은 것
//            System.out.println(queue);
            int second = queue.poll(); // 가장 2번째 맵지 않은 것
//            System.out.println(queue);
            // 섞지 못할 경우
            if (first > K && second > K) {
                break;
            }
            
            // 섞은 스코빌 지수
            int mix = first + (second * 2);
            queue.offer(mix);
            answer++;
        }
        
        if (queue.size() == 1) {
            int mix = queue.poll();
            if (mix < K)
                answer = -1;
        } else {
            queue.clear();
        }
        
        if (answer < 1) {
            return -1;
        } else {
            return answer;
        }
        
    }
}
