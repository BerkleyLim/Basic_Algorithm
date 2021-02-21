var express = require('express');

var app = express();

// N : 5 ~ 100
// build_frame : (세로)행 길이 : 1 ~ 1000 / (가로)열 길이 : 4
// build_frame 원소 : (x, y, a, b)
// (x, y) : 기둥, 보를 설치 or 삭제할 교차점의 좌표 (가로, 세로)
// a : 설치 or 삭제할 구조물의 종류 (0 : 기둥, 1 : 보)
// b : 구조물을 설치할 지, 삭제할지 나타냄 (0 : 삭제, 1 : 설치)
// 바닥에 보 설치 못함
// 벽면을 벗어난 기둥, 보 설치 못함

// return 값 : [x, y, a]
// (x,y) :  기둥 or 보의 교차점 좌표
// 기둥 or 보 : 교차점 좌표 기준으로 오른쪽 or 위쪽 방향으로 설치
// 구조물 : 오른쪽 방향. 기둥 : 윗쪽 방향
// x, y 좌표가 같을 시 기둥이 보보다 앞에 온다.

// 보 삭제
function boardRemove(map,x,y) {
    if (y === 0)
        return;
    if (map[x][y-1] == 1 || map[x+1][y] == 1 || map[x+1][y] == 2 || map[x-1][y] == 2 ) {
        map[x][y] = 0;
    }
}

// 보 삽입
function boardInsert(map,x,y) {
    if (y === 0)
        return;
    if (map[x][y-1] == 1 || map[x+1][y] == 1 || map[x+1][y] == 2 || map[x-1][y] == 2 ) {
        map[x][y] = 2;
    }
}

// 기둥 삭제
function pillarRemove(map,x,y) {
    if (y == 0 || map[x][y-1] == 1 || map[x][y] == 1 || map[x][y] == 2 || map[x-1][y] == 2) {
        map[x][y] = 0;
    }
}

// 기둥 삽입
function pillarInsert(map,x,y) {
    if (y == 0 || map[x][y-1] == 1 || map[x][y] == 1 || map[x][y] == 2 || map[x-1][y] == 2) {
        map[x][y] = 1;
    }
}


// 설치 조건 
// 1) 기둥 : 바닥 or 밑에 기둥 존재, 현재 위치에 보가 존재, 왼쪽 위치에 보가 존재
// 2) 보 : 밑에 기둥, 오른쪽 밑 기둥 존재, 양옆에 보 존재
// 삭제 조건
// 1) 기둥 : 기둥 설치 조건과 동일
// 2) 보 : 보 설치 조건과 동일
function solution(n, build_frame) {
    var answer = [[]];

    // map으로 기둥인지 보인지 여부 : 기둥 : 1, 보 2 (map에서의 기둥과 보는 build_frame[i][2]와 전혀 다름)
    var map = [[]];

    for (i in build_frame.length) {
        // 1) 기둥 or 보 삭제 : 0
        // 2) 기둥 or 보 삽입 : 1
        if (build_frame[i][3] == 0) {
            // 1) 기둥 : 0
            // 2) 보 : 1
            if (build_frame[i][2] == 0) {
                pillarRemove(map,build_frame[i][0], build_frame[i][1]);
            } else {
                boardRemove(map,build_frame[i][0], build_frame[i][1]);
            }
        } else {
            // 1) 기둥 : 0
            // 2) 보 : 1
            if (build_frame[i][2] == 0) {
                pillarInsert(map,build_frame[i][0], build_frame[i][1]);
            } else {
                boardInsert(map,build_frame[i][0], build_frame[i][1]);
            }
        }
    }

    return answer;
}

app.get('/', (req) => {
    req.send();
});

app.listen(3000, () => {
    // return : [[1,0,0],[1,1,1],[2,1,0],[2,2,1],[3,2,1],[4,2,1],[5,0,0],[5,1,0]]
    n = 5, build_frame = [[1,0,0,1],[1,1,1,1],[2,1,0,1],[2,2,1,1],[5,0,0,1],[5,1,0,1],[4,2,1,1],[3,2,1,1]];
    console.log(solution(n, build_frame));

    // return : [[0,0,0],[0,1,1],[1,1,1],[2,1,1],[3,1,1],[4,0,0]]
    n = 5, build_frame = [[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]];
    console.log(solution(n, build_frame));
})