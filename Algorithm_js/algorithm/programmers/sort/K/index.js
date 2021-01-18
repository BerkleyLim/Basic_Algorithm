var express = require('express');

var app = express();

// 프로그래머스 참조 : K번째 수
// https://programmers.co.kr/learn/courses/30/lessons/42748?language=javascript


// array 길이 : 1 ~ 100
// 각 원소 : 1 ~ 100
// commands 길이 : 1 ~ 50
// commands의 각 원소 길이 : 3

function solution (array, commands) {
    var answer = [];

    for(var i=0; i<commands.length;i++){

        // slice(시작점, 끝점).sort(오름차순 정렬) : 자르고 정렬 함수
        var list = array.slice(commands[i][0]-1, 
            commands[i][1]).sort((a,b)=>{return a-b});
        
        // n번째 숫자 push
        answer.push(list[commands[i][2]-1]);
    }

    return answer;
}

// var app = express();


// app.get('/', function (req, res) {
//     res.send('hello world!');
// });

var array = [1, 5, 2, 6, 3, 7, 4];
var commands = [[2, 5, 3], [4, 4, 1], [1, 7, 3]];

// 결과 값 : [5, 6, 3]
app.get('/', (req,res) => {
    res.send(solution(array, commands));
});

// app.listen : 두개의 argument를 받음, 첫번째 : port번호, 두번째 : 함수
app.listen(3000, function() {
    console.log('Server On!');
});

// 이후 node test.js 하면 됨