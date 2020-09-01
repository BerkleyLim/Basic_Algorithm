package author.hyun.sik.lim.programmers.bruteforce.test;

// 모의고사
public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] answers = {1,2,3,4,5};
        System.out.println(solution(answers)); // {1}
        
        answers = new int[]{1,3,2,4,2};
        System.out.println(solution(answers)); // {1, 2, 3}
    }

    // 수포자 : 수학을 포기한 자
    // 수포자 삼인방은 모의고사에 수학 문제를 전부 찍는다.
    // 1번 문제에서 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어질때,
    // 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 한다.
    
    // 시험 : 최대 1만문제
    // 문제 정답 : 1,2,3,4,5 중 하나
    // 가장 높은 점수를 받은 사람이 여럿이면, return 하는 값을 오름차순 정렬
    
    // 수포자 1 패턴 : {1, 2, 3, 4, 5}
    // 수포자 2 패턴 : {2, 1, 2, 3, 2, 4, 2, 5}
    // 수포자 3 패턴 : {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    public static int[] solution(int[] answers) {
        
        // 수포자 패턴
        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        // 점수 확인
        int[] score = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == person1[i % person1.length]) {
                score[0]++;
            }
            
            if (answers[i] == person2[i % person2.length]) {
                score[1]++;
            }
            
            if (answers[i] == person3[i % person3.length]) {
                score[2]++;
            }
            
        }
        
        int[] answer = {};
        if (score[0] == score[1]) {
            if (score[0] > score[2]) {
                answer = new int[] {1, 2};
                return answer;
            } else if (score[0] < score[2]) {
                answer = new int[] {3};
                return answer;
            } else {
                answer = new int[] {1, 2, 3};
                return answer;
            }
        } else if (score[0] > score[1]) {
            if (score[0] > score[2]) {
                answer = new int[] {1};
                return answer;
            } else if (score[0] < score[2]) {
                answer = new int[] {3};
                return answer;
            } else {
                answer = new int[] {1, 3};
                return answer;
            }
        } else {
            if (score[1] > score[2]) {
                answer = new int[] {2};
                return answer;
            } else if (score[1] < score[2]) {
                answer = new int[] {3};
                return answer;
            } else {
                answer = new int[] {2, 3};
                return answer;
            }
        }
    }
}
