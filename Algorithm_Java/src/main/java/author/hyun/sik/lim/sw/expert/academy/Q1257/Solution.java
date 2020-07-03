package author.hyun.sik.lim.sw.expert.academy.Q1257;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        for (int test = 1; test <= T; test++) {
            int K = Integer.parseInt(br.readLine());
            String str = br.readLine();
            bw.write("#"+test+" "+solution(K, str)+"\n");
        }
        
        br.close();
        bw.close();
    }

    private static String solution(int K, String str) {
        // TODO Auto-generated method stub
        Set<String> set = new HashSet<>();
        
        int N = str.length();
        
        // 토큰 수대로 구하기
        for (int token = 1; token <= N; token++) {
            
            int start = 0;
            for (int i = token; i <= N; i++) {
                set.add(str.substring(start,i));
                start++;
            }
        }
        
        //Collections.sort(set);
//        Iterator<String> iterator = set.iterator();
//        System.out.println(set);
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        
//        for (String s : list) {
//            System.out.println(s);
//        }
        
        if (K > list.size())
            return "noun";
        else
            return list.get(K-1);
    }

}
