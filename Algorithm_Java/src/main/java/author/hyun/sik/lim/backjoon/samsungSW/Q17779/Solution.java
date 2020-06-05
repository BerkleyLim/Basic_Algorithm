package author.hyun.sik.lim.backjoon.samsungSW.Q17779;

import java.util.Scanner;

// 게리맨더링을 통해 최대한 공평하게 선거구를 확정지을ㄹ고 함

// N * N 격자가 주어짐
// r행 c열 (r, c)로 나눔
// 구역을 다섯개의 선거구로 나눠야함
// 기준점 (x, y)와 경게의 길이 d1, d2를 정한다.
// 경계선과 경계선의 안에 포함되어 있는 곳은 5번 선거구
// 5번 선거구에 포함되지 않은 구역 (r,c)의 선거구 번호는 다음 기준을 따름

// 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
// 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
// 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
// 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N

// 경계선
// 1번 : (x,y), (x+1, y-1), .... (x+d1, y-d1)
// 2번 : (x,y), (x+1, x+1), .... (x+d2, y+d2)
// 3번 : (x+d1, y-d1), (x+d1+1, y-d1+1), .... (x+d1+d2, y-d1+d2)
// 4번 : (x+d2, y+d2), (x+d2+1, y-d2-1), .... (x+d2+d1, y+d2-d1)


public class Solution {
    static int size;    // 격자 크기
    static int[][] map; // 격자
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        size = sc.nextInt();
        
        map = new int[size+1][size+1];
        
        int total = 0;
        for(int y = 1; y <= size; y++) {
            for(int x = 1; x <= size; x++) {
                map[y][x] = sc.nextInt();
                total += map[y][x];
            }
        }
        
        // 최대값 - 최소값의 결과
        int result = 999999;
        
        // 모든 조건 검사 수행 후 실시!
        // 1 <= d1, 1 <= d2, 
        // 1 <= x <= x + d1 + d2 <= N
        // 1 <= y-d1 < y < y+d2 <= N
        
        for (int x = 1; x <= size - 2; x++) {
            for (int y = 2; y <= size - 1; y++) {
                for (int d1 = 1; d1 <= size; d1++) {
                    for (int d2 = 1; d2 <= size; d2++) {
                        if (isValid(x, y, d1, d2)) {
                            
                            int[] area = new int[6];
                            
                            int[][] location = new int[size+2][size+2];
                            
                            // 기준점 설정
                            int tempY = y;
                            int tempX = x;
                            
                            // 5의 자리 설정
                            // 지역 1 나누기
                            for (int i = 0; i < d1; i++) {
                                location[tempY][tempX] = 5;
                                tempY--;
                                tempX++;
                            }
                            
                            // 지역 2 나누기
                            for (int i = 0; i < d2; i++) {
                                location[tempY][tempX] = 5;
                                tempY++;
                                tempX++;
                            }
                            
                            // 지역 4 나누기
                            for (int i = 0; i < d1; i++) {
                                location[tempY][tempX] = 5;
                                tempY++;
                                tempX--;
                            }
                            
                            // 지역 3 나누기
                            for (int i = 0; i < d2; i++) {
                                location[tempY][tempX] = 5;
                                tempY--;
                                tempX--;
                            }
                            
                            
                            
                            // 여기서부터 지역구 인구수 구하기
                            
                            // 지역 1
                            for (int r = 1; r <= y - 1; r++) {
                                for (int c = 1; c <= x + d1; c++) {
                                    if (location[r][c] == 5)
                                        break;
                                    area[1] += map[r][c];
                                }
                            }
                            
                            // 지역 2
                            for (int r = 1; r <= y - d1 + d2; r++) {
                                for (int c = size; c > x + d1; c--) {
                                    if (location[r][c] == 5)
                                        break;
                                    area[2] += map[r][c];
                                }
                            }
                            
                            // 지역 3
                            for (int r = y; r <= size; r++) {
                                for (int c = 1; c < x + d2; c++) {
                                    if (location[r][c] == 5)
                                        break;
                                    area[3] += map[r][c];
                                }
                            }
                            
                            // 지역 4
                            for (int r = y - d1 + d2 + 1; r <= size; r++) {
                                for (int c = size; c >= x + d2; c--) {
                                    if (location[r][c] == 5)
                                        break;
                                    area[4] += map[r][c];
                                }
                            }
                            
                            // 지역 5
                            area[5] = total;
                            for (int i = 1; i <= 4; i++)
                                area[5] -= area[i];
                            
                            int max = area[1];
                            int min = area[1];
                            
                            for (int i = 2; i < area.length; i++) {
                                max = Math.max(max, area[i]);
                                min = Math.min(min, area[i]);
                            }

                            if (result > max - min) result = max - min;
                            
                        } else {
                            //break;
                        }
                    }
                }
            }
        }
        // 알고리즘 종료
        System.out.println(result);
    }

    public static boolean isValid(int x, int y, int d1, int d2) {
        if ((x + d1 + d2 <= size) && (y + d2 <= size) && (y >= d1)) return true;
        return false;
    }
}
