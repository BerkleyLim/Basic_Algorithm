package author.hyun.sik.lim.programmers.kakao21.q5;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // "01:30:59"
        System.out.println(solution("02:03:55","00:14:15",
                new String[] {"01:20:15-01:45:14", 
                              "00:40:31-01:00:00",
                              "00:25:50-00:48:29",
                              "01:30:59-01:53:29", 
                              "01:37:44-02:02:30"}));
        
        // "01:00:00"
        System.out.println(solution("99:59:59", "25:00:00",
                new String[] {"69:59:59-89:59:59",
                              "01:00:00-21:00:00",
                              "79:59:59-99:59:59",
                              "11:00:00-31:00:00"}));
        
        // "00:00:00"
        System.out.println(solution("50:00:00", "50:00:00",
                new String[] {"15:36:51-38:21:49",
                              "10:14:18-15:36:51",
                              "38:21:49-42:51:45"}));
        
    }

    // 광고 삽입
    // 동영상 중간중간에 광고를 삽일 할 것이다
    // 동영상 재상시간 길이(play_time), 공익광고 재상시간 길이(adv_time),
    // 시청자들이 해당 동영상을 재생했던 구간 정보(logs)가 주어진다
    // 공익 광고가 들어갈 시작 시작을 구하여라.
    // 삽입 위치 : 누적 재생시간이 가장 많이 나오는 곳 (누적 재생시간이 가장 많은 곳이 여러곳일때 그 중 가장 빠른 시작 시각으로)
    
    
    /*
        1. 영상의 길이, 광고의 길이를 초단위로 변경
        2. 영상의 길이까지 담을 수 있는 int배열 생성
        3. 로그들을 순회하면서 배열에 +1 - 다만 끝나는 지점은 +1 하지않음(끝점을 포함하지않고 계산해야하기 때문)
        4. 0초에 광고를 삽입한다고 가정했을대의 누적값을 구함
        5. 광고의 시작과 끝을 +1 씩하면서 누적값을 비교함
         - 시작시간의 viewer 수를 뺌
         - 종료시간의 viewer 수를 더 함
         - 비교해서 크면 max값으로 둠
         
         여기서 long형으로 사용하는 것이 좋고, 종료시점에서 누적 값에서 계산안되어야함
     * */
    public static String solution(String play_time, String adv_time, String[] logs) {
//        // 만일 재생시간과 공익 광고시간이랑 같을때
//        if (play_time.equals(adv_time))
//                return "00:00:00";
        
        int playSecond = secondChange(play_time);
        int adSecond = secondChange(adv_time);
        long[] counts = new long[playSecond + 1]; // play 값까지 포함해야 해서 + 1로 지정
        
        for (String log : logs) {
            String[] str = log.split("-");
            int startViewTime = secondChange(str[0]);
            int endViewTime = secondChange(str[1]);

//            System.out.println(startViewTime + " " + endViewTime);
            // viewer 의 시작부터 끝까지 +1 - 종료 시점은 본것아니므로 < 부등호 사용
            for (int i = startViewTime; i < endViewTime; i++) {
                counts[i]++;
            }
        }
        
        // 0초에 광고를 넣는 다고가정 했을때 누적 값을 계산
        int startTime = 0;
        int endTime = adSecond;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += counts[i];
        }

        // 누적값에서 앞에 값을 빼고 뒤에 값을 추가하면서 각초마다 광고를 넣었을때의 누적값을 구하고 비교
        long max = sum;
        int maxStartTime = 0;
        while (endTime <= playSecond) {
            sum -= counts[startTime];
            sum += counts[endTime];
            if(sum > max) {
                max = sum;
                maxStartTime = startTime + 1;
            }
            startTime++;
            endTime++;
        }
        return dateChange(maxStartTime);
    }

    private static String dateChange(int time) {
        // TODO Auto-generated method stub
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        int second = time % 60;

        String strHour = hour > 9 ?  String.valueOf(hour) : "0" + hour;
        String strMinute = minute > 9 ?  String.valueOf(minute) : "0" + minute;
        String strSecond = second > 9 ?  String.valueOf(second) : "0" + second;

        return String.join(":", strHour, strMinute, strSecond);
    }

    // 시간을 초로 바꾼다
    private static int secondChange(String timeSecond) {
        // TODO Auto-generated method stub
        String[] str = timeSecond.split(":");
        return Integer.parseInt(str[0]) * 60 * 60 + Integer.parseInt(str[1]) * 60 + Integer.parseInt(str[2]);
    }
    
    
    
}
