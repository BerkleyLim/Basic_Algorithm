package author.hyun.sik.lim.programmers.DFS.BFS.changeword;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution(begin, target, words)); // 4
        
        words = new String[]{"hot", "dot", "dog", "lot", "log",};
        System.out.println(solution(begin, target, words)); // 0
    }
    
    // 두 개의 단어 begin, target과 단어의 집합 words가 있다.
    // 한번에 한개의 알파벳만 바꿀수있음
    // words에 있는 단어로만 변환 가능
    static boolean[] isPick;
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean isTarget = false;
        
        // target이 있을 경우 검사
        for (int i = 0; i < words.length; i++) {
            // target이 words 안에 존재하는지 여부 검사
            if (target.equals(words[i])) {
                isTarget = true;
                break;
            }
        }
        
        if (!isTarget)
            return 0;
        
        isPick = new boolean[words.length];
        
        return answer;
    }
}
