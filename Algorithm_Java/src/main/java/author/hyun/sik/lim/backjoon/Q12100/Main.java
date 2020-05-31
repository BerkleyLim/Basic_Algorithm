package author.hyun.sik.lim.backjoon.Q12100;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Stack; 
import java.util.StringTokenizer; 

// 2048 게임
// 출처 : https://heekim0719.tistory.com/370
public class Main { 
    static int n; 
    static int max=0; 
    public static void dfs(int cnt, int[][] map) { 
        if(cnt==5) {
            return; 
        } 
        
        int[][] map2 = new int[n][n]; 
        
        //상 
        for(int i=0;i<n;i++) {
            Stack<Integer> s = new Stack<>();
            for(int j=n-1;j>=0;j--) {
                if(map[j][i]==0) 
                    continue;
                s.add(map[j][i]); 
            } 
            
            int idx = 0;
            while(!s.isEmpty()) {
                int t = s.pop();
                if(s.size()>0 && t==s.peek()) {
                    map2[idx][i]=2*t;
                    s.pop();
                    if(max<2*t) max=2*t;
                } else {
                    map2[idx][i]=t;
                    
                    if(max<t) max=t;
                }
                
                idx++;
            }
        } 
        
        dfs(cnt+1,map2); 
        
        //하
        map2 = new int[n][n];
        
        for(int i=0;i<n;i++) {
            Stack<Integer> s = new Stack<>();
            for(int j=0;j<n;j++) {
                if(map[j][i]==0)
                    continue;
                s.add(map[j][i]);
            }
            
            int idx = n-1; 
            while(!s.isEmpty()) {
                int t = s.pop();
                if(s.size()>0 && t==s.peek()) {
                    map2[idx][i]=2*t;
                    s.pop();
                    if(max<2*t)
                        max=2*t;
                } else {
                    map2[idx][i]=t;
                    if(max<t)
                        max=t;
                }
                
                idx--;
            }
        }
        
        dfs(cnt+1,map2);
        
        //좌
        map2 = new int[n][n];
        for(int i=0;i<n;i++) {
            Stack<Integer> s = new Stack<>();
            for(int j=n-1;j>=0;j--) {
                if(map[i][j]==0)
                    continue;
                s.add(map[i][j]);
            }
            
            int idx = 0;
            while(!s.isEmpty()) {
                int t = s.pop();
                if(s.size()>0 && t==s.peek()) {
                    map2[i][idx]=2*t;
                    s.pop();
                    
                    if(max<2*t)
                        max=2*t;
                } else {
                    map2[i][idx]=t;
                    
                    if(max<t) max=t;
                }
                
                idx++;
            }
        }
        dfs(cnt+1,map2);
        
        //우
        map2 = new int[n][n];
        for(int i=0;i<n;i++) {
            Stack<Integer> s = new Stack<>();
            
            for(int j=0;j<n;j++) {
                if(map[i][j]==0)
                    continue;
                s.add(map[i][j]);
            }
            
            int idx = n-1;
            while(!s.isEmpty()) {
                int t = s.pop();
                if(s.size()>0 && t==s.peek()) {
                    map2[i][idx]=2*t;
                    s.pop();
                    if(max<2*t)
                        max=2*t;
                } else {
                    map2[i][idx]=t;
                    if(max<t)
                        max=t;
                }
                
                idx--;
            }
        }
        
        dfs(cnt+1,map2);
    }
    
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),"");
        n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int idx = 0;
            while(st.hasMoreTokens()) {
                map[i][idx] = Integer.parseInt(st.nextToken());
                idx+=1;
            }
        }
        dfs(0,map);
        System.out.println(max);
    }
}
