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
        int answer = Integer.MAX_VALUE;
        isPick = new boolean[words.length];
        answer = dfs(begin, target, words, 0, answer);
        
        return answer == Integer.MAX_VALUE? 0 : answer;
    }
    
    private static int dfs(String begin, String target, 
            String[] words, int count, int minimum) {
        for (int x = 0; x < words.length; x++) {
            int diff = 0;
            boolean conversion = true;
            
            // 글자 바꿀 수 있는지 검사
            for (int i = 0; i < words[x].length(); i++) {
                if (!begin.substring(i,i+1).equals(words[x].substring(i,i+1))) {
                    diff++;
                    if (diff > 1) {
                        conversion = false;
                        break;
                    }
                }
            }
            
            // 로직
            if (!isPick[x] && conversion) {
                
                if (words[x].equals(target)) {
                    return Math.min(minimum, count + 1);
                }
                
                isPick[x] = true;
                int n = dfs(words[x], target, words, count+1, minimum);
                if (n < minimum) {
                    minimum = n;
                }
                isPick[x] = false;
            }
        }
        return minimum;
    }
}
