package author.hyun.sik.lim.programmers.greedy.PEcloth;

// https://programmers.co.kr/learn/courses/30/lessons/42862
// 체육복 문제

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(solution(n, lost, reserve)); // 5
        
        n = 5;
        lost = new int[]{2, 4};
        reserve = new int[]{3};
        System.out.println(solution(n, lost, reserve)); // 4
        
        n = 3;
        lost = new int[]{3};
        reserve = new int[]{1};
        System.out.println(solution(n, lost, reserve)); // 2
    }
    
    // 체육복 도난 사건
    // 여벌 체육복이 있는 학생이 도난 당한 학생의 체육복이 있을 경우 체육복을 빌려준다.
    // 번호가 체격순으로 이뤄진다.
    // n : 전체 학생 수 , lost : 체육복을 도난 당한 학생들의 번호가 담겨진 배열
    // reserve : 여별의 체육복을 가져온 학생들의 번호가 담긴 reserve
    // 체육 수업을 들을 수 있는 학생의 최댓값
    // n ==> 2 to 30 / 
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        
      //잃어버린애가 두벌가져온 여부(참 : -1, reserve자격박탈)
        for(int i = 0; i < reserve.length; i++){
            for(int j = 0; j < lost.length; j++){
                //자격박탈된애들 빼주기
                if(lost[j] == -1 && reserve[i] == -1){
                    continue;
                }
                // reserve 자격 박탈
                if(lost[j] == reserve[i]){
                    lost[j]=-1;
                    reserve[i]=-1;
                    answer++;
                }
            }
        }
        
        // 다음으로 인접한 학생들중 체육복 잃어버린사람 
        for(int i =0; i < lost.length; i++){
            for(int j = 0; j < reserve.length; j++){
                if(lost[i]==-1 || reserve[j]==-1){
                    continue;
                }
                if(Math.abs(lost[i]-reserve[j])==1){
                    answer++;
                    reserve[j]=-1;
                    break;
                }
            }
        }
        
        return answer;
    }
}
