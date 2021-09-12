package author.hyun.sik.lim.programmers.kakao22.q3;

import java.util.HashMap;
import java.util.Iterator;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // [14600, 34400, 5000]
        for (int i : solution(new int[] {180, 5000, 10, 600},
                new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", 
                        "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})) {
            System.out.println(i);
        }
        
        // [0, 591]
        for (int i : solution(new int[] {120, 0, 60, 591},
                new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"})) {
            System.out.println(i);
        }
        
        //  [14841]
        for (int i : solution(new int[] {1, 461, 1, 10},
                new String[] {"00:00 1234 IN"})) {
            System.out.println(i);
        }
    }

    // 주차장
    /*
     * 
     * 요금표
        기본 시간(분)    기본 요금(원)    단위 시간(분)    단위 요금(원)
        180 5000    10  600
        
        주차 요금 : fees : (기본시간(분), 기본요금(원), 단위시간(분), 단위 요금(원))
        자동차 입력 배열 : records(1 ~ 1000)
            ==> 시각 차량번호 내역 (00:00~23:59)
        
     * */
    static class Car {
        int time;
        boolean isIn;
        int costTime;
        
        Car () {
            this.time = 0;
            this.isIn = false;
            this.costTime = 0;
        };
        
        Car (int time, boolean isIn, int cost) {
            this.time  = time;
            this.isIn = isIn;
            this.costTime = cost;
        }

        public char[] keyValue() {
            // TODO Auto-generated method stub
            return null;
        }
    }
    public static int[] solution(int[] fees, String[] records) {
        
        int basicTime = fees[0];
        int basicCost = fees[1];
        int unitTime = fees[2];
        int unitCost = fees[3];
        
        HashMap<String, Car> car = new HashMap<>();
        
        for (String s : records) {
            String[] split = s.split(" ");
            
            if(!car.containsKey(split[1]))
                car.put(split[1], new Car());
            
            Car c = new Car();
            int time = secondChange(split[0]);
            boolean isIn = (split[2].equals("IN")) ? true : false;
            
            if (isIn) {
                Car temp = car.get(split[1]);
                isIn = false;
                temp.time = time;
                temp.isIn = isIn;
                
                car.put(split[1], temp);
                
            } else {
                Car temp = car.get(split[1]);
                
//                System.out.println(cur);
//                System.out.println();
                isIn = true;
                temp.isIn = isIn;
                temp.costTime += time - temp.time;
                
                car.put(split[1], temp);
            }
            
        }
        
        int[] answer = new int[car.size()];
        
        Iterator<String> keys = car.keySet().iterator();
        
        String[] index = new String[car.size()];
        int zz = 0;
        while (keys.hasNext()) {
            index[zz++] = keys.next();
            System.out.println(index[zz++]);
        }

        
        for (int i = 0; i < index.length; i++) {
//            System.out.println(c);
            int arr = 0;
            Car c = car.get(index[i]);
            if (c.isIn) {
                int cur = secondChange("23:59") - c.time;
                c.costTime += cur;
                arr = (c.costTime < basicTime) ? basicCost : ((basicTime - c.costTime) / unitTime) * unitCost;
            } else {
                
            }
            arr = (c.costTime < basicTime) ? basicCost : ((basicTime - c.costTime) / unitTime) * unitCost;
//            System.out.println(arr);
//            System.out.println(c.cost);
            answer[i] = arr;
        }
        
        
        //answer = car.value()
        return answer;
    }
    
    
    private static int secondChange(String string) {
        String[] split = string.split(":");
        
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
