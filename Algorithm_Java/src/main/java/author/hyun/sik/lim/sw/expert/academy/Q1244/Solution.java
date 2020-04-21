package author.hyun.sik.lim.sw.expert.academy.Q1244;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


// 퀴즈 대회에 참가해서 우승 시 보너스 상금을 획득한다.
// 우승자 특혜 : 주어진 숫자판들 중 두개 선택해서 정해진 횟수만큼 서로의 자리를 교환 가능
// 정해진 횟수만큼 교환 끝날 시 숫자판의 위치에 부여된 가중치에 의해 상금 계산
// ex) 배치 숫자가 88832 일경우 : 88832원의 보너스 상금 획득
// 반드시 횟수만큼 교환해야하고, 동일한 위치의 교환이 중복해서 가능
// 정해진 횟수만큼 숫자판을 교환 시 받을 수 있는 가장 큰 금액을 계산한다!
// 숫자의 최대 자릿수 : 6

class Solution {
    static Integer[] numbers;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
            String str = sc.next();
            
            numbers = new Integer[str.length()];
            
            int temp = Integer.parseInt(str);
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(str.split("")[i]);
            }
            
            int changeCount = sc.nextInt();
            
            solve(changeCount);
            
            // 결과 출력
            System.out.print("#" + test_case + " ");
            for (int number : numbers) {
                System.out.print(number);
            }
            System.out.println();
            
        }
    }
    
    public static void solve(int changeCount) {
        
        // 동일 숫자 교환했는지 체크!
        ArrayList<Integer>[] check = new ArrayList[10];
        for (int i = 0; i < check.length; i++) {
            check[i] = new ArrayList();
        }
        
        // 교환 알고리즘 실행
        for (int i = 0; i < numbers.length && changeCount > 0; i++) {
            int maxIndex = i;
            
            // 최대값 찾기!
            for (int j = i; j < numbers.length; j++) {
                if(numbers[maxIndex] <= numbers[j])
                    maxIndex = j;
            }
            
            if (numbers[maxIndex] != numbers[i]) {
                int temp = swap(maxIndex, i);
                changeCount--; // 교환 횟수 감소
                
                // 동일 숫자 교환했는지 체크하기 위해 기록 후, 같은 수를 2개 이상 바꿨는지 확인!
                ArrayList<Integer> allNumber = check[temp];
                
                allNumber.add(maxIndex);
                
                if (allNumber.size() > 1) { // 동일한 숫자를 2개 이상 바꾼 경우, 교환횟수 감소 없이 정렬
                    Collections.sort(allNumber);    // 저장된 index를 일단 정렬 
                    
                    for (int j = 0; j < allNumber.size(); j++) {
                        int maxI = allNumber.get(j);
                        
                        for (int k = j; k < allNumber.size(); k++) {
                            if (numbers[maxI] < numbers[allNumber.get(k)]) {
                                maxI = allNumber.get(k);
                            }
                        }
                        
                        swap(allNumber.get(j), maxI);
                    }
                }
            }
            
         // Arrays.asList(배열) : 배열을 List 로 변경해주는 메소드, 기본형타입의 배열은 안되고 객체 타입만 된다.
            HashSet<Integer> hs = new HashSet<Integer>(Arrays.asList(numbers));
            
         // 교환 횟수가 남았으면 남은만큼 교환
         // 짝수만큼 남은 건 무시, 홀수만큼 남으면 1회 교환(LSB를 바꾸자) 321 4
            if (changeCount % 2 == 1 && hs.size() == numbers.length) {
                swap(numbers.length-1, numbers.length-2);
            }
        }
    }
    
    public static int swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
        
        return temp;
    }
}