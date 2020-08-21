package author.hyun.sik.lim.programmers.stack.queue.functionDeveloper;

// 기능 개발
public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] progresses = {93,30,55};
        int[] speeds = {1,30,5};
        
        int[] sol = solution(progresses, speeds);
        for (int value : sol)
            System.out.println(value); // 2, 1
    }
    
    // 기능 개선 작업 수행, 각 기능은 진도가 100% 일때만 서비스 반영
    // 기능 개발 속도는 모두 다름, 뒷 기능이 앞 기능보다 먼저 개발 가능성 있다.
    // 이 경우 뒷 기능은 앞 기능이 배포될때 함께 배포됨
    
    // progresses : 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열
    // speeds : 각 작업의 개발 속도가 적힌 정수 배열
    // 몇개의 기능이 배포될지?
    // 배포는 하루 한번 가능
    // <예제>
    // 첫째 기능 : 93% 완료, 1%씩 작업      ==> 7일 작업 7일 후 배포
    // 둘째 기능 : 30% 완료, 30% 씩 작업  ==> 3일 작업 7일 후 배포
    // 셋째 기능 : 55% 완료, 5%씩 작업      ==> 9일 작업 9일 후 배포
    // 답 : 7일에 2번, 9일에 1번
    //    [2, 1]
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] complete = new int[progresses.length];
        
        for (int i = 0; i < progresses.length; i++) {
            int task = 100 - progresses[i];
            complete[i] = (task % speeds[i] == 0)?(task / speeds[i]):(task / speeds[i])+1;
            
        }
        
        int max = complete[0];
        int count = 1;
        int size = 0;
        int[] temp = new int[progresses.length];
        for (int i = 1; i <= progresses.length; i++) {
            if (i == progresses.length) {
                temp[size] = count;
                size++;
                break;
            }
            
            if (max >= complete[i])
                count++;
            else {
                temp[size] = count;
                count = 1;
                max = complete[i];
                size++;
            }
        }
        
        int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = temp[i];
        }
        return answer;
    }
}
