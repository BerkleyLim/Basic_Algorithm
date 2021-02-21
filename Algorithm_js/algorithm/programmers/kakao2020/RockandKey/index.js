var express = require('express');

var app = express();

/*
key	                                lock	                            result
[[0, 0, 0], [1, 0, 0], [0, 1, 1]]	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]	true
*/

function solution(key, lock) {
    var answer = true;
    return answer;
}

app.get('/', (req) => {
    req.send();
})

app.listen(3000, () => {
    var key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]];
    var lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]];
    // result : true
    console.log();
})
