package author.hyun.sik.lim.kakao.career.no3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
 * 
지도개발팀에서 근무하는 제이지는 지도에서 도시 이름을 검색하면 해당 도시와 관련된 맛집 게시물들을 
데이터베이스에서 읽어 보여주는 서비스를 개발하고 있다.
이 프로그램의 테스팅 업무를 담당하고 있는 어피치는 서비스를 오픈하기 전 각 로직에 대한 성능 측정을 수행하였는데, 
제이지가 작성한 부분 중 데이터베이스에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸린다는 것을 알게 되었다.
어피치는 제이지에게 해당 로직을 개선하라고 닦달하기 시작하였고, 
제이지는 DB 캐시를 적용하여 성능 개선을 시도하고 있지만 캐시 크기를 얼마로 해야 효율적인지 몰라 난감한 상황이다.

어피치에게 시달리는 제이지를 도와, DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램을 작성하시오.


입력 형식
- 캐시 크기(cacheSize)와 도시이름 배열(cities)을 입력받는다.
- cacheSize는 정수이며, 범위는 0 ≦ cacheSize ≦ 30 이다.
- cities는 도시 이름으로 이뤄진 문자열 배열로, 최대 도시 수는 100,000개이다.
- 각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다. 도시 이름은 최대 20자로 이루어져 있다.

출력 형식
- 입력된 도시이름 배열을 순서대로 처리할 때, “총 실행시간”을 출력한다.


조건
- 캐시 교체 알고리즘은 LRU(Least Recently Used)를 사용한다.
- cache hit일 경우 실행시간은 1이다.
- cache miss일 경우 실행시간은 5이다.


입출력 예제
캐시크기(cacheSize)    도시이름(cities)                                                                                                      실행시간
3                   [“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”]                            50
3                   [“Jeju”, “Pangyo”, “Seoul”, “Jeju”, “Pangyo”, “Seoul”, “Jeju”, “Pangyo”, “Seoul”]                                   21
2                   [“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “SanFrancisco”, “Seoul”, “Rome”, “Paris”, “Jeju”, “NewYork”, “Rome”]   60
5                   [“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “SanFrancisco”, “Seoul”, “Rome”, “Paris”, “Jeju”, “NewYork”, “Rome”]   52
2                   [“Jeju”, “Pangyo”, “NewYork”, “NewYork”]                                                                            16
0                   [“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”]                                                                        25
 * */
public class KakaoAlgorithmNo3 {
    private int run = 0; // 총 실행시간 표시기능, <부재시 5, 부재가 아닐 시  1>
    
    
    // 최댓값 찾기 - 즉 오래된 페이지 찾기 위한 조건 (이것은 페이지가 가득 찼을 때 용도)
    private int recentPage(int[] recentWrite, int cacheSize) {
        int oldPage = recentWrite[0];
        int pageTimeReturn = 0;
        
        for (int i = 1; i < cacheSize; i++) {
            if (oldPage < recentWrite[i]) {
                oldPage = recentWrite[i];
                pageTimeReturn = i;
            }
        }
        
        return pageTimeReturn;
    }
    
    // 페이지 교체 알고리즘 구성하는 로직!
    private void logicReplacePage(int cacheSize, List<String> page, 
            List<String> cities, int[] recentWrite, int i) {
        // 해당 캐쉬 처리할 페이지가 꽉 찰 경우 여부 검사, 비어 있을 시 바로 넣는다.
        if (page.size() == cacheSize) {
            int oldPage = recentPage(recentWrite, cacheSize);
            page.set(oldPage, cities.get(i));
            recentWrite[oldPage] = 0;
        } else {
            page.add(cities.get(i));
        }
    }
    
    
    // 지도개발 도시의 대한 정보를 읽어 출력시키는 로직 구성(최대한 효율성이 좋은 알고리즘을 구현한것)
    public void logicTest(int cacheSize, List<String> cities) {
        // 페이지 알고리즘 구성!
        List<String> page = new ArrayList<>(); // 페이지 정의!
        
        int[] recentWrite = new int[cacheSize]; // 페이지 사용 여부 기본 초기값 : 0
                                                // 사용 직후, 바로 올린다!
        boolean search; // 여기서 찾았는지 안찾았는지 조건문 부여 
        
        // 도시이름을 입력한 순서대로 검사하는 조건문 구현
        for (int i = 0; i< cities.size(); i++) {
            search = false;
            
            // 여기선 page가 부재여부인지 아닌지 검사하는 알고리즘 (즉, 찾았는지 안 찾았는지 검사)
            for (int z = 0; z < page.size(); z++) {
                if (cities.get(i).equals(page.get(z))) {
                    recentWrite[z] = 0;
                    run++;
                    search = true;
                } else {
                    recentWrite[z]++;
                }
            }
            
            // 못찾았을 경우!
            if (!search) {
                logicReplacePage(cacheSize, page, cities, recentWrite, i);
                run += 5;
            }
            
            
            // 교체후 알고리즘 결과 값 확인하는 메소드
            //System.out.println(page.toString());
        }
    }
    
    // LRU 알고리즘으로 한 결과 실행 결과 출력
    public int resultRun() {
        return run;
    }
    
    // 실행 테스트!
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        
        // 알고리즘 테스트!
        for (int T = 1; T <= testCase; T++) {
            KakaoAlgorithmNo3 result = new KakaoAlgorithmNo3();
            
            int cacheSize = sc.nextInt(); // 페이지 캐시 크기 지정!
            sc.nextLine();
            
            // 여기에 바로 배열 선언
            List<String> cities = new ArrayList<>();
            String temp = sc.nextLine(); // 한줄로 도시명 입력
            
            String[] arr = temp.split(" ");
            
            // 각 배열로 저장
            for (int i = 0; i < arr.length; i++) {
                cities.add(arr[i]);
            }
            
            result.logicTest(cacheSize, cities);
            
            // 결과 값 출력
            System.out.print("#" + T + " : ");
            System.out.println(result.resultRun());
            
        }
        
    }
    
    
}
