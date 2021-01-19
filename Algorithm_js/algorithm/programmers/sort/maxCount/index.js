var express = require('express');

var app = express();

// 프로그래머스 참조 : 가장 큰 수
// https://programmers.co.kr/learn/courses/30/lessons/42746

// 0 또는 양수가 주어짐,
// 정수를 이어 붙여 만들 수 있는 가장 큰수?

// 제한 사항
// numbers 길이 : 1 ~ 100000
// numbers 원소 : 0 ~ 1000
// 문자열로 바꿔서 return

function solution(numbers) {
    // numbers.map(c=>c+'') : 각 숫자들을 문자로 변환(1 => '1')
    // sort((a,b) => (b+a) - (a+b)) : 문자로 변환된 숫자를 연결하여 비교정렬
    //              ( '3', '30' => ('303')-('330'))
    // join('') : 문자열 합치기
    // numbers 배열이 0으로만 구성되어 있을 경우 '0'만 출력
    var answer = numbers.map(c=> c + '').
    				sort((a,b) => (b+a) - (a+b)).join('');
    
    return answer[0]==='0'? '0' : answer;
}

// var app = express();


// app.get('/', function (req, res) {
//     res.send('hello world!');
// });

// 결과 값 : [5, 6, 3]
app.get('/', (req,res) => {
    var number1 = [6, 10, 2];
    var number2 = [3, 30, 34, 5, 9];
    res.send(solution(number1) + " " + solution(number2)); // "6210"
});

// app.listen : 두개의 argument를 받음, 첫번째 : port번호, 두번째 : 함수
app.listen(3000, function() {
    console.log('Server On!');
});

// 이후 node test.js 하면 됨