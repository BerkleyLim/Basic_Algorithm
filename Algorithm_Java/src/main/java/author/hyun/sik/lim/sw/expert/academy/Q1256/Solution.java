package author.hyun.sik.lim.sw.expert.academy.Q1256;

// 트라이 문자
// 트라이 참고 : https://4ngs.tistory.com/24
// K번째 접미어 찾기 문제


public class Solution {
    static int N;
    static class TrieNode {
        TrieNode[] child;
        boolean isTerminal;
        public TrieNode() {
            this.child = new TrieNode[N];
            this.isTerminal = false;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
