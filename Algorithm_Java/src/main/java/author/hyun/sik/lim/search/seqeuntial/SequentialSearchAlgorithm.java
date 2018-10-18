package author.hyun.sik.lim.search.seqeuntial;

/**
 * 
 * <PRE>
 * 1. ClassName : SequentialSearchAlgorithm
 * 2. FileName  : SequentialSearchAlgorithm.java
 * 3. Package  : author.hyun.sik.lim.search.seqeuntial
 * 4. Comment  :  다음은 순차 정렬의 알고리즘을 작성하였다!
 * 5. 작성자   : limhs
 * 6. 작성일   : 2018. 10. 18. 오후 7:07:16
 * </PRE>
 */

/**
 * 
 * 순차 정렬 알고리즘
 * - S : 배열에 저장된 값
 * - n : 배열의 n개의 키
 * - x : 배열의 키값
 * - location : x의 위치
 */

// 입력 
public class SequentialSearchAlgorithm {
    // 순차정렬 알고리즘을 구성한다!
    // 단 s[0] 자리를 사용하지 않고,
    // 보통은 S[n+1] 형태로 사용
    void seqsearch (int n, final Object s[], Object x, int location) {
        location = 1;
        
        // 
        while (location <= n && s[location] != x)
            location++;
        if (location > n)
            location = 0;
    }
    
    // 문제 1) n개의 수로 구성된 리스트(또는 배열)에서 가장 큰 수를 찾는 알고리즘
    
}
