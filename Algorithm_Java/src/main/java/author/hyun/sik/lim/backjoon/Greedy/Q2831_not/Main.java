package author.hyun.sik.lim.backjoon.Greedy.Q2831_not;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// Greedy Algorithm 유형
// 댄스 파티
// 남자 N명 여자 N명이 주어지고, 각 한쌍의 커플로 구성하여 춤을 춘다.
// 조건은 남자 및 여자가 선호하는 유형을 주고 모든 사람과 출 수 있는 것.

// 남자가 선호하는 여자 조건
// 1) 자신보다 키가 큰 여자
// 2) 자신보다 키가 작은 여자

// 여자가 선호하는 남자 조건
// 1) 자신보다 키가 큰 남자
// 2) 자신보다 키가 작은 남자

// N은 100,000 이하

// 입력 조건
// 1) N 값
// 2) 남자가 여자를 선호하는 키 범위 2개의 정수 (음수일 경우 자기보다 작은 사람)
// 3) 여자가 남자를 선호하는 키 범위 2개의 정수 (음수일 경우 자기보다 작은 사람)

// 출력조건
// 여러가지 경우의 수 중에서 조건이 일정한 쌍의 최대 갯수 출력
public class Main {
    static class Person{
        int tail;   // 키를 표현
        boolean prefer; // true : 자기 보다 키큰 사람 선호, false : 자기보다 키 작은 사람 선호
        int size;
        
        public Person(int tail, boolean prefer) {
            this.tail = tail;
            this.prefer = prefer;
        }
        
        public void count() {
            size++;
        }
    }
    
    static LinkedList<Person> man;
    static LinkedList<Person> woman;
    
    static int N;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        System.out.println(solution());
        
    }

    private static int solution() {
        // TODO Auto-generated method stub
        int count = 0;
        
        for (int i = 0; i < N; i++) {
            System.out.println(man.get(i).tail + " " + man.get(i).prefer);
            System.out.println(woman.get(i).tail + " " + woman.get(i).prefer);
            System.out.println();
        }
        
        return 0;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        // 남자 정보 입력
        st = new StringTokenizer(br.readLine());
        man = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (value < 0) {
                man.add(new Person(-value,false));
            } else {
                man.add(new Person(value,true));
            }
            
        }
        
        // 여자 정보 입력
        st = new StringTokenizer(br.readLine());
        woman = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (value < 0) {
                woman.add(new Person(-value,false));
            } else {
                woman.add(new Person(value,true));
            }
        }
        
        br.close();
    }

}
