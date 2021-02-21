var express = require('express');

var app = express();


/*
## 제한사항
- m은 1 이상 1,000 이하인 자연수입니다.
- 배열 v의 길이는 1 이상 100,000 이하입니다.
- 배열 v의 원소는 1 이상 m 이하인 자연수입니다.
*/

// m : 가로길이, v : 블록의 길이가 순서대로 들어있는 배열 
// 쌓인 블록의 층 수!
function solution(m, v) {
    var level = 0;
    
    var board = [0];
    
    
    // 알고리즘 시작
    // 숫자가 m일 경우 꽉 찬걸로 간주 내림차순으로 색인 시작!
    for (let i = 0; i < v.length; i++) {
        let floor = level;

        for (var x = level; x >= 0; x--) {

            if (board[x] + v[i] <= m) {
                floor = x;
            } else {
                break;
            }
        }

        board[floor] += v[i];

        level = Math.max(floor , level);

    }

    return level;
}

app.get('/', (req,res) => {
    // res.send(answer[0] + " " + answer[1]);
});

app.listen(3000, function() {
    m = 4, v = [2, 3, 1]
    console.log(solution(m, v)); // 2

    m = 4, v = [3, 2, 3, 1]
    console.log(solution(m, v)); // 3
});