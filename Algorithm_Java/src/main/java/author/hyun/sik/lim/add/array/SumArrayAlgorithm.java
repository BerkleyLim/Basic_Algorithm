package author.hyun.sik.lim.add.array;

/**
 * 
 * <PRE>
 * 1. ClassName : SumArrayAlgorithm
 * 2. FileName  : SumArrayAlgorithm.java
 * 3. Package  : author.hyun.sik.lim.add.array
 * 4. Comment  : 다음은 배열의 합을 구하는 알고리즘이다
 * 5. 작성자   : limhs
 * 6. 작성일   : 2018. 10. 18. 오후 7:18:30
 * </PRE>
 */

/**
 * 
 * 배열의 합 알고리즘
 * - n개의 수로 된 배열 S에 있는 모든 수를 더하기!
 * - 양의 정수 n, 배열 S(첨자는 1 ~ n)
 * - S에 있는 수의 합, sum
 */ 

public class SumArrayAlgorithm {
    int sum (int n, final int S[]) {
        int i; // 인덱스
        int result; // 결과 값 (number)
        result = 0;
        
        for (i = 1; i<=n; i++) 
            result += S[i];
        
        return result;
    }
}
