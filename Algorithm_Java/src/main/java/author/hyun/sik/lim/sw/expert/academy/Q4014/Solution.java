package author.hyun.sik.lim.sw.expert.academy.Q4014;

import java.util.Scanner;

public class Solution {
    // 활주로 건설
    // N이 주어짐
    // N*N 칸에는 각 높이가 주어짐
    // 활주로는 높이가 동일한 구간에서 건설 가능
    // 높이가 다른 구간은 활주로가 끊어짐 따라서 경사로를 설치해야함
    // 경사로 길이 X, 높이 : 1
    // 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int X = sc.nextInt();
            
            int[][] boards = new int[N][N];
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    boards[y][x] = sc.nextInt();
                }
            }
            
            System.out.println("#" + test_case + " " + solution(N,X,boards));
        }
    }

    private static int solution(int N, int X, int[][] boards) {
        // TODO Auto-generated method stub
        int answer = 0;
        
        for (int y = 0; y < N; y++) {
            boolean isLength = true; // 세로의 대해 활주로 건설 가능시
            
            // 오른쪽에 대해서 검사용 (왼쪽은 이미 처리된 상태기 때문에) - 중복 방지
            boolean[] visited = new boolean[N];
            for (int x = 0; x < N-1; x++) {
                // 가로의 대하여
                if (isLength) {
                    if (boards[y][x] == boards[y][x+1]) {
                        continue;
                    }
                    // 왼쪽
                    else if (boards[y][x] + 1 == boards[y][x+1]) {
                        for (int i = x; i >= x - X + 1; i--) {
                            if (i >= 0 && !visited[i] && boards[y][x] == boards[y][i]) {
                                visited[i] = true;
                            } else {
                                isLength = false;
                                break;
                            }
                        }
                        if (!isLength)
                            break;
                            
                    } else if (boards[y][x] - 1 == boards[y][x+1]) {// 오른쪽
//                            if (boards[y][x] - 1 == boards[y][x+1]) {}
                        int count = 0;
                        for (int i = x +1; i <= x + X; i++) {
                            if (i < N && !visited[i] && boards[y][x+1] == boards[y][i]) {
                                visited[i] = true;
                                count++;
                            } else {
                                isLength = false;
                                break;
                            }
                        }
                        x += count-1;
                        if (!isLength)
                            break;
                    } else {
                        isLength = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (isLength) answer++;
        }
        
        for (int y = 0; y < N; y++) {
            boolean isWidth = true; // 가로의 대해 활주로 건설 가능시
            
            // 오른쪽에 대해서 검사용 (왼쪽은 이미 처리된 상태기 때문에) - 중복 방지
            boolean[] visited = new boolean[N];
            for (int x = 0; x < N-1; x++) {
                // 세로의 대하여
                if (isWidth) {
                    if (boards[x][y] == boards[x+1][y]) {
                        continue;
                        
                        // 왼쪽
                    } else if (boards[x][y] + 1 == boards[x+1][y]) {
                        for (int i = x; i >= x - X + 1; i--) {
                            if (i >= 0 && !visited[i] && boards[x][y] == boards[i][y]) {
                                visited[i] = true;
                            } else {
                                isWidth = false;
                                break;
                            }
                        }
                        if (!isWidth)
                            break;
                        
                    } else if (boards[x][y] - 1 == boards[x+1][y]) {
                        // 오른쪽
//                            if (boards[x][y] - 1 == boards[x+1][y]) {}
                        int count = 0;
                        for (int i = x+1; i <= x + X; i++) {
                            if (i < N && !visited[i] && boards[x+1][y] == boards[i][y]) {
                                visited[i] = true;
                                count++;
                            } else {
                                isWidth = false;
                                break;
                            }
                        }
                        x += count-1;
                        if (!isWidth)
                            break;
                        
                    } else {
                        isWidth = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (isWidth) answer++;
        }
        return answer;
    }

}
