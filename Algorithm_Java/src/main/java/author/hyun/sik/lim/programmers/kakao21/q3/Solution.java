package author.hyun.sik.lim.programmers.kakao21.q3;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] info = {"java backend junior pizza 150",
                         "python frontend senior chicken 210",
                         "python frontend senior chicken 150",
                         "cpp backend senior pizza 260",
                         "java backend junior chicken 80",
                         "python backend senior chicken 50"};
        
        String[] query = {"java and backend and junior and pizza 100",
                          "python and frontend and senior and chicken 200",
                          "cpp and - and senior and pizza 250",
                          "- and backend and senior and - 150",
                          "- and - and - and chicken 100",
                          "- and - and - and - 150"};
        
        for(int i : solution(info, query))
            System.out.println(i);
    }

    // 순위 검색
    // 개발언어 : cpp, java ptyhon 중 하나 선택한다
    // 직군 : backend, frontend
    // 경력 구분 : junior, senior
    // 선호 소울 푸드 : chicken, pizza
    /*
     * 코딩테스트에 java로 참여했으며, backend 직군을 선택했고, 
     * junior 경력이면서, 
     * 소울푸드로 pizza를 선택한 사람 중 코딩테스트 점수를 50점 이상 받은 지원자는 몇 명인가?
     * */
    // 개발팀에서 궁금해 하는 내용
    // [조건]을 만족하는 사람 중 코딩테스트 점수를 x점 이상 받은 사람은 모두 몇명인가?
    
    // 4가지 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성
    // info(1~50,000) : "개발언어 직군 경력 소울푸드 점수" (스페이스로 구분)
    // query(조건)(1~100,000) : "개발언어 and 직군 and 경력 and 소울푸드 and 점수" 형식의 문자열
    //
    // dp문제 (정렬 후 푸는 문제)
    
    // 언어별 - 쪼개서 검사
    private static int[] java;
    private static int[] cpp;
    private static int[] python;
    private static int[] language;
    
    // 직군 여부 - 1 : front-end, 2 : back-end
    private static int[] position;
    
    // 경력 구분 - 1 : junior, 2 : senior
    private static int[] career;
    
    // 선호하는 소울 푸드 - 1 : chicken, 2 : pizza
    private static int[] food;
    
    // 점수 - 각각 입력
    private static int[] score;
    
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        java = new int[info.length];
        cpp = new int[info.length];
        python = new int[info.length];
        language = new int[info.length];
        position = new int[info.length];
        career = new int[info.length];
        food = new int[info.length];
        score = new int[info.length];
        
        // 효율을 위해 언어별로 각각 배열로 나눈후 각 배열마다 대입.
        int javaLength = 0;
        int cppLength = 0;
        int pythonLength = 0;
        
        for (int i = 0; i < info.length; i++) {
            String[] string = info[i].split(" ");
            
            // 언어 입력
            if (string[0].equals("java")) {
                java[javaLength++] = i;
            } else if (string[0].equals("cpp")) {
                cpp[cppLength++] = i;
            } else {
                python[pythonLength++] = i;
            }
            language[i] = i;
            
            // 직군 입력
            if (string[1].equals("frontend")) {
                position[i] = 1;
            } else 
                position[i] = 2;
            
            // 경력 입력
            if (string[2].equals("junior"))
                career[i] = 1;
            else 
                career[i] = 2;
            
            // 소울푸드
            if (string[3].equals("chicken"))
                food[i] = 1;
            else
                food[i] = 2;
            
            // 점수
            score[i] = Integer.parseInt(string[4]);
        }
        
        // 다음은 query의 대한 조건 출력
        // query 구성 : "java and backend and junior and pizza 100"
        //               0    1    2     3     4     5   6     7
        for (int i = 0; i < query.length; i++) {
            // query 조건문으로 순차적 수행
            String[] string = query[i].split(" ");
            int[] arr;
            int length;
            
            // 1) 언어 query 검사
            if (string[0].equals("java")) {
                arr = java;
                length = javaLength;
            } else if (string[0].equals("cpp")) {
                arr = cpp;
                length = cppLength;
            } else if (string[0].equals("python")) {
                arr = python;
                length = pythonLength;
            } else {
                arr = language;
                length = info.length;
            }
            
            // 2) 직군, 경력, 푸드, 점수 검사
            int count = 0;
            for (int x = 0; x < length; x++) {
                // 직군
                if (string[2].equals("frontend")) {
                    if (position[arr[x]] != 1) 
                        break;
                } else if (string[2].equals("backend")) {
                    if (position[arr[x]] != 2) 
                        break;
                } else {
                }
                
                // 경력
                if (string[4].equals("junior")) {
                    if (career[arr[x]] != 1) 
                        break;
                } else if (string[4].equals("senior")) {
                    if (career[arr[x]] != 2) 
                        break;
                } else {
                }
                
                // 푸드
                if (string[6].equals("chicken")) {
                    if (food[arr[x]] != 1) 
                        break;
                } else if (string[6].equals("pizza")) {
                    if (food[arr[x]] != 2) 
                        break;
                } else {
                }
                
                // 점수
                if (Integer.parseInt(string[7]) <= score[arr[x]]) {
                    count++;
                } else {
                    break;
                }
                
            }
            answer[i] = count;
        }
        
        return answer;
    }
}
