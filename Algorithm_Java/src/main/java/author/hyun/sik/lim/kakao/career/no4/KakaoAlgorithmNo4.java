package author.hyun.sik.lim.kakao.career.no4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
 * 
 * 4. 셔틀버스(난이도: 중)
카카오에서는 무료 셔틀버스를 운행하기 때문에 판교역에서 편하게 사무실로 올 수 있다. 
카카오의 직원은 서로를 ‘크루’라고 부르는데, 아침마다 많은 크루들이 이 셔틀을 이용하여 출근한다.

이 문제에서는 편의를 위해 셔틀은 다음과 같은 규칙으로 운행한다고 가정하자.

- 셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
- 셔틀은 도착했을 때 도착한 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발한다. 


예를 들어 09:00에 도착한 셔틀은 자리가 있다면 09:00에 줄을 선 크루도 탈 수 있다.
일찍 나와서 셔틀을 기다리는 것이 귀찮았던 콘은, 
일주일간의 집요한 관찰 끝에 어떤 크루가 몇 시에 셔틀 대기열에 도착하는지 알아냈다. 
콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각을 구하여라.

단, 콘은 게으르기 때문에 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. 
또한, 모든 크루는 잠을 자야 하므로 23:59에 집에 돌아간다. 따라서 어떤 크루도 다음날 셔틀을 타는 일은 없다.

입력 형식
셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 
크루가 대기열에 도착하는 시각을 모은 배열 timetable이 입력으로 주어진다.

- 0 ＜ n ≦ 10
- 0 ＜ t ≦ 60
- 0 ＜ m ≦ 45
- timetable은 최소 길이 1이고 최대 길이 2000인 배열로,
- 하루 동안 크루가 대기열에 도착하는 시각이 HH:MM 형식으로 이루어져 있다.
- 크루의 도착 시각 HH:MM은 00:01에서 23:59 사이이다.


출력 형식
콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각을 출력한다. 
도착 시각은 HH:MM 형식이며, 00:00에서 23:59 사이의 값이 될 수 있다.

입출력 예제
n   t   m   timetable                                               answer
1   1   5   [“08:00”, “08:01”, “08:02”, “08:03”]                    “09:00”
2   10  2   [“09:10”, “09:09”, “08:00”]                             “09:09”
2   1   2   [“09:00”, “09:00”, “09:00”, “09:00”]                    “08:59”
1   1   5   [“00:01”, “00:01”, “00:01”, “00:01”, “00:01”]           “00:00”
1   1   1   [“23:59”]                                               “09:00”
10  60  45  [“23:59”,”23:59”, “23:59”, “23:59”, “23:59”, 
             “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, 
             “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”]  “18:00”
 * */

public class KakaoAlgorithmNo4 {
    private int destinationTime; // 도착시간!
    private static final int HOUR = 60; // 60분 = 1시간, 즉 1시간을 표현하는 용도
    
    // 콘이 도착을 해야하는 시간의 대해 논리적으로 설정!
    public void connArriveTime(int n, int t, int m, List<Integer> timetable) {
        // 버스 도착시간은 기본으로 09:00으로 설정한다!, 00:00 = 시간 값 : 0 = 1440
        int startTime = 9 * HOUR; // 09:00 기준
        
        // 1) n = 운행횟수, 2) t = 배차간격, 3) m = 좌석량 등 고려하여 작성
        
        
    }
    
    // 안정적으로 도착 시간
    public int resultTime() {
        return destinationTime;
    }
    
    // main() 메서드는 테스트로 돌릴 메서드
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        
        // 알고리즘 테스트!
        for (int T = 1; T <= testCase; T++) {
            KakaoAlgorithmNo4 result = new KakaoAlgorithmNo4();
            
            int n, t, m; // n = 운행 횟수, t = 배차간격 (단위 : 분), m = 좌석량
            
            n = sc.nextInt();
            t = sc.nextInt();
            m = sc.nextInt();
            sc.nextLine();
            
            // timetable : 즉 크루가 대기열에 서는 배열
            List<Integer> timetable = new ArrayList<>();
            
            String temp = sc.nextLine();
            String[] arr = temp.split(" ");
            
            // 대기열 시간을 입력하고 나서 바로 숫자로 대입하여 넣는다.
            for (int i = 0; i < arr.length; i++) {
                int time = Integer.parseInt(arr[i].split(":")[0]) * HOUR 
                        + Integer.parseInt(arr[i].split(":")[1]);
                timetable.add(time);
                System.out.println(timetable.get(i));
            }
            
            result.connArriveTime(n, t, m, timetable);
            
            // 결과 값 출력
            System.out.print("#" + T + " : ");
            System.out.printf("%02d:%02d\n",
                    result.resultTime() / HOUR, result.resultTime() % HOUR);
        }
    }
}
