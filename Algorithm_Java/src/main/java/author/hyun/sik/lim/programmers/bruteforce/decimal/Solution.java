package author.hyun.sik.lim.programmers.bruteforce.decimal;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

// 참고 : https://codevang.tistory.com/299?category=827588

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String numbers = "17";
        System.out.println(solution(numbers)); // 3
        
        numbers = "011";
        System.out.println(solution(numbers)); // 2
    }
    
    // 트리셋 활용
    static TreeSet<String> set;
    static int count;
    
    public static int solution(String numbers) {
        int size = numbers.length();
        count = 0;
        set = new TreeSet<>();
        
        List<String> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(numbers.split("")[i]);
        }
        
        List<String> result = new LinkedList<>();
        
        // 순열 알고리즘으로 이용
        for (int i = 0; i < size; i++) {
            permutation(list, result, size, i + 1);
        }
        
        return count;
    }

    // list : 원본, result : 결과, n : 전체 갯수, r : 선택할 갯수
    private static void permutation(List<String> list, List<String> result,
            int n, int r) {
        // TODO Auto-generated method stub
        
        if (r == 0) {
            // 0으로 시작하는 부분집할일 경우!
            if (!result.get(0).equals("0")) {
                
                String str = "";
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    str += result.get(i);
                }
                
                int number = 0;
                
                // 이미 나온 숫자 조합이 아닐때,
                if (!set.contains(str)) {
                    number = Integer.parseInt(str);
                    set.add(str);
                    
                    // 소수 판별하기
                    if (isPrime(number)) {
//                        System.out.println(number);
                        count++;
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                result.add(list.remove(i));
                permutation(list, result, n-1, r-1);
                list.add(i, result.remove(result.size() - 1));
            }
        }
        
    }

    // number : 판별 정수
    private static boolean isPrime(int number) {
        // TODO Auto-generated method stub
        
        int sqrt = (int) Math.sqrt(number);
        
        // 2는 유일한 짝수 소수
        if (number == 2) {
            return true;
        }
        
        // 짝수와 1은 소수 아님
        if (number % 2 == 0 || number == 1) {
            return false;
        }
        
        // 제곱근까지만 홀수로 나눈다.
        for (int i = 3; i <= sqrt; i += 2) {
            if (number % i == 0)
                return false;
        }
        
        
        return true;
    }

}
