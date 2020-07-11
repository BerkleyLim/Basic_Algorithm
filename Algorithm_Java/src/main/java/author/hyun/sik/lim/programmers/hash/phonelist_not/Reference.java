package author.hyun.sik.lim.programmers.hash.phonelist_not;

import java.util.HashMap;
import java.util.Map;

// 전화번호 목록 문제 (Hash)
// 트라이 구현방법 참고 : https://the-dev.tistory.com/3
public class Reference {
    public static void main(String[] args) {
        
        String[] phone_book = {"119", "97674223", "1195524421"}; //false
        System.out.println(solution(phone_book));
        phone_book = new String[]{"123","456","789"};   //true
        System.out.println(solution(phone_book));
        phone_book = new String[]{"12","123","1235","567","88"};    //false
        System.out.println(solution(phone_book));
    }
    
    // 한 번호가 다른 번호의 접두어인 경우 있는지 검사 문제
    // phone_book의 길이는 1 ~ 1,000,000
    // 각 전화번호의 길이는 1이상 20이하
    // 트라이 알고리즘
//    public static boolean solution(String[] phone_book) {
//        Trie trie = new Trie();
//        
//        // insert 메서드
//        assertTrue(trie.isRootEmpty());
//        trie.insert("PI");
//        trie.insert("PIE");
//        trie.insert("POW");
//        trie.insert("POP");
//        assertFalse(trie.isRootEmpty());
//        // Contains 메서드
//        assertTrue(trie.contains("POW"));
//        assertFalse(trie.contains("PIES"));
//        // Delete 메서드
//        trie.delete("POP");
//        assertFalse(trie.contains("POP"));
//        assertTrue(trie.contains("POW"));
//        // 없는 단어를 지울 때 > 에러발생하는 예
//        trie.delete("PO");
//        trie.delete("PIES");
//        trie.delete("PEN");
//
//        return true;
//    }
    
    static class Trie {
         // [ 변수 ]
         // 루트 노드
         private TrieNode rootNode;
         // [ 생성자 ]
         Trie() {
             rootNode = new TrieNode();
         }
         // [ 메서드 ]
         // 자식 노드 추가
         void insert(String word) {
             TrieNode thisNode = this.rootNode;
             for (int i = 0; i < word.length(); i++) {
                 thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
             }
             thisNode.setIsLastChar(true);
         }
         // 특정 단어가 들어있는지 확인
         boolean contains(String word) {
             TrieNode thisNode = this.rootNode;
             for (int i = 0; i < word.length(); i++) {
                 char character = word.charAt(i);
                 TrieNode node = thisNode.getChildNodes().get(character);
                 if (node == null)
                     return false;
                 thisNode = node;
             }
             return thisNode.isLastChar();
         }
         // 특정 단어 지우기
         void delete(String word) {
             delete(this.rootNode, word, 0); // 최초로 delete 던지는 부분
         }
         
         private void delete(TrieNode thisNode, String word, int index) {
             char character = word.charAt(index);
             
             // APPLE, PEN과 같이 아예 없는 단어인 경우 에러 출력
             if(!thisNode.getChildNodes().containsKey(character))
                 throw new Error("There is no [" + word + "] in this Trie.");
             
             TrieNode childNode = thisNode.getChildNodes().get(character);
             index++;
             
             if(index == word.length()) {
                 // 삭제조건 2번 항목
                 // PO와 같이 노드는 존재하지만 insert한 단어가 아닌 경우 에러 출력
                 if (!childNode.isLastChar())
                     throw new Error("There is no [" + word + "] in this Trie.");
                 childNode.setIsLastChar(false);
                 // 삭제조건 1번 항목
                 // 삭제 대상 언어의 제일 끝으로써 자식 노드가 없으면(이 단어를 포함하는 더 긴 단어가 없으면) 삭제 시작
                 if (childNode.getChildNodes().isEmpty())
                     thisNode.getChildNodes().remove(character);
             } else {
                 delete(childNode, word, index); // 콜백함수부분
                 // 삭제조건 1,3번 항목
                 // 삭제 중, 자식 노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 이 노드 삭제
                 if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
                     thisNode.getChildNodes().remove(character);
             }
         }
     }

    static class TrieNode {
         // [ 변수 ]
         // 자식 노드 맵
         private Map<Character, TrieNode> childNodes = new HashMap<>();
         // 마지막 글자인지 여부
         private boolean isLastChar;
         // [ GETTER / SETTER 메서드 ]
         // 자식 노드 맵 Getter
         Map<Character, TrieNode> getChildNodes() {
             return this.childNodes;
         }
         // 마지막 글자인지 여부 Getter
         boolean isLastChar() {
             return this.isLastChar;
         }
         // 마지막 글자인지 여부 Setter
         void setIsLastChar(boolean isLastChar) {
             this.isLastChar = isLastChar;
         }
     }
}
