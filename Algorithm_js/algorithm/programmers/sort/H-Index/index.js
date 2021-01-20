var express = require('express');

var app = express();

// 프로그래머스 참조 : 가장 큰 수
// https://programmers.co.kr/learn/courses/30/lessons/42747


// H-Index : 과학자의 생산성과 영향력을 나타내는 지표
// h값 구하기
// n편 중, h번 이상 인용된 논문이 h편 이상, 나머지 논문이 h번 이하 인용
// h의 최댓값은?
// 

// h index = max min(f(i),i), f(i)는 i번째 논문의 인용 횟수
function solution(citations) {
    var answer = 0;
    return answer;
}

// var app = express();


// app.get('/', function (req, res) {
//     res.send('hello world!');
// });

// 결과 값 : [5, 6, 3]
app.get('/', (req,res) => {
    var citations = [3, 0, 6, 1, 5];
    res.send(solution(number1)); // 3
});

// app.listen : 두개의 argument를 받음, 첫번째 : port번호, 두번째 : 함수
app.listen(3000, function() {
    console.log('Server On!');
});

// 이후 node test.js 하면 됨