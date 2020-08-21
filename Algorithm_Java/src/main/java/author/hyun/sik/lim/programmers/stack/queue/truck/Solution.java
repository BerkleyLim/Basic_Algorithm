package author.hyun.sik.lim.programmers.stack.queue.truck;


// 다리를 지나가는 트럭
public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int bridge_length = 2; 
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};
        // 8
        System.out.println(solution(bridge_length, weight, truck_weights));
        
        bridge_length = 100; 
        weight = 100;
        truck_weights = new int[]{10};
        // 101
        System.out.println(solution(bridge_length, weight, truck_weights));
        
        bridge_length = 100; 
        weight = 100;
        truck_weights = new int[]{10,10,10,10,10,10,10,10,10,10};
        // 110
        System.out.println(solution(bridge_length, weight, truck_weights));
        
    }

    // 트럭 여러대가 강을 가로지름
    // 여기서 일 차선 다리를 정해진 순으로 건넌다.
    // 모든 트럭 다닐 시 최소 몇소가 필요한가?
    // 트럭 : 1초당 1만큼 움직임
    // bridge_length : 다리 길이
    // weight : 다리가 견딜 수 있는 무게
    // truck_weight : 각 트럭별 무게
    // 트럭이 다리에 완전히 오르지 않은 경우 : 트럭의 무게를 고려 안함
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        // 초기! {7, 4, 5, 6} / 길이 : 2 / 중량 10
        
        // 다리가 길이 2라면 , 1초 : 0번째, 2초 : 1번째, 3초 : 지나감 형태
        // 0번방 : 1초 움직임
        // 1번방 : 2초 움직임
        int[] bridge = new int[bridge_length];
        
        // 0, 1에 위치한 트럭이 10kg 초과시 0번째에서 1번째로만 이동
        int wait = 0; // 대기 트럭 
        int second = 0;
        int[] passTruck = new int[truck_weights.length];
        int count = 0; // count는 다리 지나간 트럭 갯수 센다, 트럭 모두 다리 지나가면 break;
        while(count < truck_weights.length) {
            //System.out.println(bridge[0] + " " + bridge[1]);
            if (bridge[bridge_length - 1] > 0) {
                passTruck[count++] = bridge[bridge_length - 1];
            }
            
            int sum = 0; // 다리 중량 합계
            for (int index = bridge_length - 1; index > 0; index--) {
                bridge[index] = bridge[index - 1];
                sum += bridge[index];
            }
            
            if (wait < truck_weights.length 
                    && (sum + truck_weights[wait]) <= weight) {
                bridge[0] = truck_weights[wait++];
            } else {
                bridge[0] = 0;
            }
            second++;
        }
        
        return second;
    }
}
