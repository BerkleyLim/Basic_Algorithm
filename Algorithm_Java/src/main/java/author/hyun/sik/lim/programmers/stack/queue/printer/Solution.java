package author.hyun.sik.lim.programmers.stack.queue.printer;

import java.util.LinkedList;
import java.util.List;

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
        
        // 리스트로 인쇄순위 매기기
        List<Integer> list = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            list.add(i);
            if (i == location)
                answer = location;
        }
        
        boolean exit = true;
        // 먼저 321144 부터 생각하고 풀어보기
        while (exit) {
            int i = 1;
            exit = false;
            for (int j = 0; j < priorities.length; j++) {
                System.out.print("(" + list.get(j) + ", " +priorities[list.get(j)] + ")");
            }
            System.out.println();
            
            while (i < priorities.length) {
                if (priorities[list.get(i-1)] < priorities[list.get(i)]) {
                    exit = true;
                    int count = 0;
                    int prev = i - 1;
                    while (prev > 0) {
                        if (priorities[list.get(prev)] > priorities[list.get(i-1)]) {
                            break;
                        } else {
                            count++;
                            prev--;
                        }
                    }
                    
                    while (count >= 0) {
                        int value = list.remove(prev);
                        list.add(value);
                        count--;
                    }
                    
                    break;
                } 
                i++;
            }
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (location == list.get(i)) {
                answer = i + 1;
                break;
            }
        }
        
        return answer;
    }
}
