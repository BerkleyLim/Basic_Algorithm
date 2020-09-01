package author.hyun.sik.lim.programmers.stack.queue.printer;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] priorities = {2,1,3,2};
        int location = 2; // 1
        System.out.println(solution(priorities, location));
        
        priorities = new int[]{1,1,9,1,1,1};
        location = 0; // 5
        System.out.println(solution(priorities, location));
        
        priorities = new int[]{4,3,2,1,3,2,1};
        location = 3; // 6
        System.out.println(solution(priorities, location));
        
    }

//    1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
//    2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
//    3. 그렇지 않으면 J를 인쇄합니다.
    // 문서 : A, B, C, D
    // 순서 : 2, 1, 3, 2
    // 출력 : C, D, A, B
    // 중요도 : 숫자가 클수록 중요
    // location : 0이상(현재 대기목록에 있는 작업수 -1)이하의 값!
    //    대기목록 가장 앞 : 0, 두번째 : 1
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int priority : priorities) {
            priorityQueue.offer(priority);
        }
        
        while(!priorityQueue.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorityQueue.peek() == priorities[i]) {
                    priorityQueue.poll();
                    
                    answer++;
                    if (location == i) {
                        priorityQueue.clear();
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}
