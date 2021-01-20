var express = require('express');

var app = express();

// 사칙연산
// N = 1 ~ 9
// N으로만 사칙연산하여 number값을 만든다.
// N을 몇번 이용한 최솟값만 추출하여라!
// number = 1 ~ 32000
// 괄호 + 사칙연산만 가능 (나누기에서 나머지는 무시)
// 최솟값이 8보다 클 시 -1로 return


// DF 문제이지만, BFS로 응용해서 문제 풀기
// 
app.get('/', (req,res) => {
    res.send(solution());
});

app.listen(3000, function() {
    console.log();
});