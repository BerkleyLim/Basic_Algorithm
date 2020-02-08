package author.hyun.sik.lim.agv;

import java.util.ArrayList;

//오더라인 개수 : 45,234 orderline
//매장 개수 : 78 개
//SKU 개수 : 5,587
//같이 주어 졌을 때
//전체 매장 개수를 n개를 나눠 각각 DAS 수(올림 값)와 각각의 n개의 로케이션를 계산한다 (나머지 값도 포함)
//이 경우 1개의 DAS에 있을 경우 선반이 도착이후 1 SKU를 꺼내고 나서 DAS에 있는 L/C에 있는 매장별로 분배 할 수 있고,
//1 SKU를 꺼낼 때 마다 pick 수가 증가한다.
//전체 DAS를 모두 매장별로 SKU를 분배했을 경우 pick 횟수를 최대값, 최소값, 평균값을 구하는 것이고,
//78 개의 매장이 주어졌을 때 DAS에 매장별로 L/C를 임의 배치를 할 것이며
//모두 배치가 끝났을 때, pick 횟수의 최대값, 최소값, 평균값을 나타내라.


public class PickCalcurator {
    class Node {
        int storeNumber;
        ArrayList<Integer> SKU = new ArrayList<>();
    }
    ArrayList<Node> node; // 매장번호 별 SKU 피킹 명령
    private static int STORE = 78; // 매장 수
    private static int SKUCount = 5578; // SKU 수   
    
    PickCalcurator() {
        node = new ArrayList<>();
    }
    
    // 배열 생성
    public void createPickCalcurater(int storeNumber, int SKU) {
        // 매장 리스트 생성 및 추가 등록
        boolean isExist = false;
        
        int i = 0;
        
        while(i < node.size()) {
            
            if (node.get(i).storeNumber == storeNumber) {
                isExist = true;
                break;
            }
            
            i++;
        }
        
        if (isExist) {
            Node temp = node.get(i);
            temp.SKU.add(SKU);
            node.add(temp);
        } else {
            Node temp = new Node();
            temp.storeNumber = storeNumber;
            temp.SKU.add(SKU);
            node.add(temp);
        }
        
    }
    
}
